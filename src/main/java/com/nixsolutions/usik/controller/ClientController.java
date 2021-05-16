package com.nixsolutions.usik.controller;

import com.nixsolutions.usik.constants.FieldConstants;
import com.nixsolutions.usik.constants.LoggerConstants;
import com.nixsolutions.usik.constants.UrlConstants;
import com.nixsolutions.usik.model.entities.Client;
import com.nixsolutions.usik.model.service.ClientService;
import com.nixsolutions.usik.model.service.IService;
import com.nixsolutions.usik.utils.ControllerUtils;
import com.nixsolutions.usik.validators.EntityValidator;
import lombok.AccessLevel;
import lombok.val;
import lombok.Getter;
import lombok.NonNull;
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

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Log4j2
@RestController
@RequestMapping(value = UrlConstants.CLIENT)
public class ClientController implements EntityController<Client> {
    @NonNull //TODO final field, dell all notNull
    @Getter //TODO class level
    IService<Client> service;
    @NonNull
    @Getter
    EntityValidator<Client> validator;

    @Override
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Client entity) {
        return create(entity, log);
    }

    @Override
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Client entity) {
        return update(entity, log);
    }

    @Override
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return delete(id, log);
    }

    @GetMapping(value = "{email}")
    public ResponseEntity<Client> read(@PathVariable String email) {
        log.info(LoggerConstants.GET_MAPPING_WITH_PARAMETER, FieldConstants.EMAIL + "=" + email);
        val client = ((ClientService) service).findByEmail(email);
        return ControllerUtils.returnEntityOrNotFound(client);
    }
}
