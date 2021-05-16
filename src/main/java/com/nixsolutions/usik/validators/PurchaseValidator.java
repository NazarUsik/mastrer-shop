package com.nixsolutions.usik.validators;

import com.nixsolutions.usik.model.entities.Purchase;
import com.nixsolutions.usik.model.service.PurchaseService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Component
public class PurchaseValidator implements EntityValidator<Purchase> {
    @NonNull
    @Getter
    PurchaseService service;
}
