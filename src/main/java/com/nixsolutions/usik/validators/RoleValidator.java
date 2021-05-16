package com.nixsolutions.usik.validators;

import com.nixsolutions.usik.model.entities.Role;
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
public class RoleValidator implements EntityValidatorWithName<Role> {

    @NonNull
    @Getter
    IServiceWithName<Role> service;

    @Override
    public ResponseEntity<?> validateForSave(Role role) {
        return validateForSave(role.getName());
    }
}
