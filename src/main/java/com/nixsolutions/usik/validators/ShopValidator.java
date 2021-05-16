package com.nixsolutions.usik.validators;

import com.nixsolutions.usik.model.entities.Shop;
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
public class ShopValidator implements EntityValidatorWithName<Shop> {
    @NonNull
    @Getter
    IServiceWithName<Shop> service;

    @Override
    public ResponseEntity<?> validateForSave(Shop shop) {
        return validateForSave(shop.getName());
    }
}
