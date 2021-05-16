package com.nixsolutions.usik.validators;

import com.nixsolutions.usik.exeptions.ResourceNotFoundException;
import com.nixsolutions.usik.model.entities.IEntity;
import com.nixsolutions.usik.model.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface EntityValidator<E extends IEntity> {
    IService<E> getService();

    default ResponseEntity<?> validateForSave(E entity) {
        return null;
    }

    default ResponseEntity<?> validateForDelete(long id) {
        try {
            getService().findById(id);
            return null;
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}
