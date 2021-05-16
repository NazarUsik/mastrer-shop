package com.nixsolutions.usik.model.repository;

import com.nixsolutions.usik.model.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long>, IRepositoryWithName<Product> {
    Optional<Product> findByAlias(@Param("alias") String alias);
    
    List<Product> findAllByShopId(@Param("shopId") long shopId);
    
    List<Product> findByNameContainsOrAliasContains(@Param("name") String name, @Param("alias") String alias);
}
