package com.nixsolutions.usik.utils;

import com.nixsolutions.usik.model.entities.IEntity;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@UtilityClass
public class ControllerUtils {
    public <E extends IEntity> ResponseEntity<E> returnEntityOrNotFound(E entity) {
        return entity != null
                ? new ResponseEntity<>(entity, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public <E extends IEntity> ResponseEntity<List<E>> returnEntityOrNotFound(List<E> entities) {
        return !entities.isEmpty()
                ? new ResponseEntity<>(entities, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
