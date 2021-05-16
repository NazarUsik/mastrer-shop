package com.nixsolutions.usik.model.service;

import com.nixsolutions.usik.model.entities.Purchase;
import com.nixsolutions.usik.model.repository.PurchaseRepository;
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
public class PurchaseService implements IService<Purchase> {
    @NonNull
    @Getter
    CrudRepository<Purchase, Long> repository;

    public List<Purchase> findAllByClientId(long clientId) {
        return ((PurchaseRepository) repository).findAllByClientId(clientId);
    }

    public List<Purchase> findAllByProductId(long productId) {
        return ((PurchaseRepository) repository).findAllByProductId(productId);
    }
}
