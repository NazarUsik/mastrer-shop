package com.nixsolutions.usik.validators;

import com.nixsolutions.usik.exeptions.ResourceNotFoundException;
import com.nixsolutions.usik.model.entities.Client;
import com.nixsolutions.usik.model.service.ClientService;
import com.nixsolutions.usik.model.service.IService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Component
public class ClientValidator implements EntityValidator<Client> {

    @NonNull
    @Getter
    IService<Client> service;

    @Override
    public ResponseEntity<?> validateForSave(Client client) {
        try {
            ((ClientService) service).findByEmail(client.getEmail());
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (ResourceNotFoundException ex) {
            return null;
        }
    }
}
