package com.nixsolutions.usik.model.repository;

import com.nixsolutions.usik.model.entities.ReviewProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewProductRepository extends CrudRepository<ReviewProduct, Long>, IRepository<ReviewProduct> {
    List<ReviewProduct> findAllByClientId(@Param("clientId") long clientId);

    List<ReviewProduct> findAllByProductId(@Param("productId") long productId);

    Optional<ReviewProduct> findByClientIdAndProductId(
            @Param("clientId") long clientId,
            @Param("productId") long productId);
}
