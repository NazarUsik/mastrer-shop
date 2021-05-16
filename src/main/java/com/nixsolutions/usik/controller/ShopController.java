package com.nixsolutions.usik.controller;

import com.nixsolutions.usik.constants.FieldConstants;
import com.nixsolutions.usik.constants.LoggerConstants;
import com.nixsolutions.usik.constants.UrlConstants;
import com.nixsolutions.usik.model.entities.Shop;
import com.nixsolutions.usik.model.service.ShopService;
import com.nixsolutions.usik.utils.ControllerUtils;
import com.nixsolutions.usik.validators.ShopValidator;
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
@RequestMapping(value = UrlConstants.SHOP)
public class ShopController implements EntityController<Shop> {
    @NonNull
    @Getter
    ShopService service;
    @NonNull
    @Getter
    ShopValidator validator;

    @Override
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Shop entity) {
        return create(entity, log);
    }

    @Override
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Shop entity) {
        return update(entity, log);
    }

    @Override
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return delete(id, log);
    }

    @GetMapping(value = "/client/{id}")
    public ResponseEntity<Shop> readByClientId(@PathVariable long id) {
        log.info(LoggerConstants.GET_MAPPING_WITH_PARAMETER, FieldConstants.CLIENT_ID + "=" + id);
        val shop = service.findByClientId(id);
        return ControllerUtils.returnEntityOrNotFound(shop);
    }

    @GetMapping(value = "/search/{keyword}")
    public ResponseEntity<List<Shop>> search(@PathVariable String keyword) {
        log.info(LoggerConstants.SEARCH_MAPPING, keyword);
        val shops = service.search(keyword);
        return ControllerUtils.returnEntityOrNotFound(shops);
    }
}
