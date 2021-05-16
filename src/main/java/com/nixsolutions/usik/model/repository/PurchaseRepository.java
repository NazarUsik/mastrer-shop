package com.nixsolutions.usik.model.repository;

import com.nixsolutions.usik.model.entities.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends CrudRepository<Purchase, Long>, IRepository<Purchase> {
    List<Purchase> findAllByClientId(@Param("clientId") long clientId);

    List<Purchase> findAllByProductId(@Param("productId") long productId);
}
