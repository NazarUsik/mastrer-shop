package com.nixsolutions.usik.validators;

import com.nixsolutions.usik.model.entities.Status;
import com.nixsolutions.usik.model.service.IServiceWithName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Component
public class StatusValidator implements EntityValidatorWithName<Status> {

    @NonNull
    @Getter
    IServiceWithName<Status> service;

    @Override
    public ResponseEntity<?> validateForSave(Status productType) {
        return validateForSave(productType.getName());
    }
}
