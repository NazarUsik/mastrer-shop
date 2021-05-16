package com.nixsolutions.usik.controller;

import com.nixsolutions.usik.constants.UrlConstants;
import com.nixsolutions.usik.model.entities.ProductType;
import com.nixsolutions.usik.model.service.IServiceWithName;
import com.nixsolutions.usik.validators.EntityValidator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Log4j2
@RestController
@RequestMapping(value = UrlConstants.PRODUCT_TYPE)
public class ProductTypeController implements EntityControllerWithName<ProductType> {
    @NonNull
    @Getter
    IServiceWithName<ProductType> service;
    @NonNull
    @Getter
    EntityValidator<ProductType> validator;

    @Override
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductType entity) {
        return create(entity, log);
    }

    @Override
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProductType entity) {
        return update(entity, log);
    }

    @Override
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return delete(id, log);
    }
}
