package com.nixsolutions.usik.validators;

import com.nixsolutions.usik.exeptions.ResourceNotFoundException;
import com.nixsolutions.usik.model.entities.ReviewProduct;
import com.nixsolutions.usik.model.service.IService;
import com.nixsolutions.usik.model.service.ReviewProductService;
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
public class ReviewProductValidator implements EntityValidator<ReviewProduct> {
    @NonNull
    @Getter
    IService<ReviewProduct> service;

    @Override
    public ResponseEntity<?> validateForSave(ReviewProduct reviewProduct) {
        try {
            ((ReviewProductService) service).findByClientIdAndProductId(reviewProduct.getClientId(), reviewProduct.getProductId());
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (ResourceNotFoundException ex) {
            return null;
        }
    }
}
