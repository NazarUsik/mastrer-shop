package com.nixsolutions.usik.controller;

import com.nixsolutions.usik.model.entities.IEntity;
import com.nixsolutions.usik.model.service.IServiceWithName;
import com.nixsolutions.usik.utils.ControllerUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface EntityControllerWithName<E extends IEntity> extends EntityController<E> {
    @GetMapping(value = "/name/{name}")
    default ResponseEntity<E> read(@PathVariable String name) {
        E entity = getService().findByName(name);
        return ControllerUtils.returnEntityOrNotFound(entity);
    }

    IServiceWithName<E> getService();
}
