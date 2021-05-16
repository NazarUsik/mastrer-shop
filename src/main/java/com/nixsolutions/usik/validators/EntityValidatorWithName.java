package com.nixsolutions.usik.validators;

import com.nixsolutions.usik.exeptions.ResourceNotFoundException;
import com.nixsolutions.usik.model.entities.IEntity;
import com.nixsolutions.usik.model.service.IServiceWithName;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface EntityValidatorWithName<E extends IEntity> extends EntityValidator<E> {
    IServiceWithName<E> getService();

    default ResponseEntity<E> validateForSave(String name) {
        try {
            getService().findByName(name);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (ResourceNotFoundException ex) {
            return null;
        }
    }

}
