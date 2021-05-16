package com.nixsolutions.usik.controller;

import com.nixsolutions.usik.constants.EntityConstants;
import com.nixsolutions.usik.constants.LoggerConstants;
import com.nixsolutions.usik.model.entities.IEntity;
import com.nixsolutions.usik.model.service.IService;
import com.nixsolutions.usik.utils.ControllerUtils;
import com.nixsolutions.usik.validators.EntityValidator;
import lombok.val;
import lombok.var;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface EntityController<E extends IEntity> {
    @GetMapping
    default ResponseEntity<List<E>> readAll() {
        val list = getService().findAll();
        return ControllerUtils.returnEntityOrNotFound(list);
    }

    @GetMapping(value = "{id}")
    default ResponseEntity<?> read(@PathVariable long id) {
        val entity = getService().findById(id);
        return ControllerUtils.returnEntityOrNotFound(entity);
    }
    
    default ResponseEntity<?> create(E entity, Logger logger) {
        logger.info(LoggerConstants.POST_MAPPING);
        try {
            var response = getValidator().validateForSave(entity);
            if (response != null) {
                return response;
            }
            getService().save(entity);
            logger.info(LoggerConstants.CREATE_NEW_ENTITY, EntityConstants.getClassNameOfGeneric(this.getClass()), entity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    default ResponseEntity<?> update(E entity, Logger logger) {
        logger.info(LoggerConstants.PUT_MAPPING);
        try {
            getService().save(entity);
            logger.info(LoggerConstants.UPDATE_ENTITY, EntityConstants.getClassNameOfGeneric(this.getClass()), entity.getId(), entity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    default ResponseEntity<?> delete(long id, Logger logger) {
        logger.info(LoggerConstants.DELETE_MAPPING);
        try {
            var response = getValidator().validateForDelete(id);
            if (response != null) {
                return response;
            }
            getService().delete(id);
            logger.info(LoggerConstants.DELETE_ENTITY, EntityConstants.getClassNameOfGeneric(this.getClass()), id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    ResponseEntity<?> create(E entity);

    ResponseEntity<?> update(E entity);

    ResponseEntity<?> delete(long id);

    IService<E> getService();

    EntityValidator<E> getValidator();
}
