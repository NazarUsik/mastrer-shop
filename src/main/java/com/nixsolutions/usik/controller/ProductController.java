package com.nixsolutions.usik.controller;

import com.nixsolutions.usik.constants.FieldConstants;
import com.nixsolutions.usik.constants.LoggerConstants;
import com.nixsolutions.usik.constants.UrlConstants;
import com.nixsolutions.usik.model.entities.Product;
import com.nixsolutions.usik.model.service.ProductService;
import com.nixsolutions.usik.utils.ControllerUtils;
import com.nixsolutions.usik.validators.ProductValidator;
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
@RequestMapping(value = UrlConstants.PRODUCT)
public class ProductController implements EntityController<Product> {
    @NonNull
    @Getter
    ProductService service;
    @NonNull
    @Getter
    ProductValidator validator;

    @Override
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product entity) {
        return create(entity, log);
    }

    @Override
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Product entity) {
        return update(entity, log);
    }

    @Override
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return delete(id, log);
    }

    @GetMapping(value = "/shop/{id}")
    public ResponseEntity<List<Product>> readAllByShopId(@PathVariable long id) {
        log.info(LoggerConstants.GET_MAPPING_WITH_PARAMETER, FieldConstants.SHOP_ID + "=" + id);
        val products = service.findAllByShopId(id);
        return ControllerUtils.returnEntityOrNotFound(products);
    }

    @GetMapping(value = "/search/{keyword}")
    public ResponseEntity<List<Product>> search(@PathVariable String keyword) {
        log.info(LoggerConstants.SEARCH_MAPPING, keyword);
        val products = service.search(keyword);
        return ControllerUtils.returnEntityOrNotFound(products);
    }
}
