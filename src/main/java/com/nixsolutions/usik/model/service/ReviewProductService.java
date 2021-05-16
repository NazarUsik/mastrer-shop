package com.nixsolutions.usik.model.service;

import com.nixsolutions.usik.constants.EntityConstants;
import com.nixsolutions.usik.constants.FieldConstants;
import com.nixsolutions.usik.exeptions.ResourceNotFoundException;
import com.nixsolutions.usik.model.entities.ReviewProduct;
import com.nixsolutions.usik.model.repository.ReviewProductRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
@Transactional
public class ReviewProductService implements IService<ReviewProduct> {
    @NonNull
    @Getter
    CrudRepository<ReviewProduct, Long> repository;

    public List<ReviewProduct> findAllByClientId(long clientId) {
        return ((ReviewProductRepository) repository).findAllByClientId(clientId);
    }

    public List<ReviewProduct> findAllByProductId(long productId) {
        return ((ReviewProductRepository) repository).findAllByProductId(productId);
    }

    public ReviewProduct findByClientIdAndProductId(long clientId, long productId) {
        return ((ReviewProductRepository) repository).findByClientIdAndProductId(clientId, productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        EntityConstants.REVIEW_PRODUCT,
                        FieldConstants.CLIENT_ID + " AND " + FieldConstants.PRODUCT_ID,
                        clientId + ", " + productId));
    }
}
