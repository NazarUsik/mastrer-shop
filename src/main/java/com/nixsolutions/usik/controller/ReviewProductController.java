package com.nixsolutions.usik.controller;

import com.nixsolutions.usik.constants.FieldConstants;
import com.nixsolutions.usik.constants.LoggerConstants;
import com.nixsolutions.usik.constants.UrlConstants;
import com.nixsolutions.usik.model.entities.ReviewProduct;
import com.nixsolutions.usik.model.service.IService;
import com.nixsolutions.usik.model.service.ReviewProductService;
import com.nixsolutions.usik.utils.ControllerUtils;
import com.nixsolutions.usik.validators.EntityValidator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Log4j2
@RestController
@RequestMapping(value = UrlConstants.REVIEW_PRODUCT)
public class ReviewProductController implements EntityController<ReviewProduct> {
    @NonNull
    @Getter
    IService<ReviewProduct> service;
    @NonNull
    @Getter
    EntityValidator<ReviewProduct> validator;

    @Override
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReviewProduct entity) {
        return create(entity, log);
    }

    @Override
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ReviewProduct entity) {
        return update(entity, log);
    }

    @Override
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return delete(id, log);
    }


    @GetMapping(value = "/client/{id}")
    public ResponseEntity<List<ReviewProduct>> readAllByClientId(@PathVariable long id) {
        log.info(LoggerConstants.GET_MAPPING_WITH_PARAMETER, FieldConstants.CLIENT_ID + "=" + id);
        val reviewProduct = ((ReviewProductService) service).findAllByClientId(id);
        return ControllerUtils.returnEntityOrNotFound(reviewProduct);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<List<ReviewProduct>> readAllByProductId(@PathVariable Long id) {
        log.info(LoggerConstants.GET_MAPPING_WITH_PARAMETER, FieldConstants.PRODUCT_ID + "=" + id);
        val reviewProduct = ((ReviewProductService) service).findAllByProductId(id);
        return ControllerUtils.returnEntityOrNotFound(reviewProduct);
    }
}
