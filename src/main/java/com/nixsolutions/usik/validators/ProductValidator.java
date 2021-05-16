package com.nixsolutions.usik.validators;

import com.nixsolutions.usik.model.entities.Product;
import com.nixsolutions.usik.model.service.IService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Component
public class ProductValidator implements EntityValidator<Product> {
    @NonNull
    @Getter
    IService<Product> service;
}
