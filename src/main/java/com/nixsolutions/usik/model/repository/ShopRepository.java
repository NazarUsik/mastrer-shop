package com.nixsolutions.usik.model.repository;

import com.nixsolutions.usik.model.entities.Shop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends CrudRepository<Shop, Long>, IRepositoryWithName<Shop> {
    @Query("SELECT s FROM Shop s JOIN Client c on s.id = c.shopId WHERE c.id = :clientId")
    Optional<Shop> findByClientId(@Param("clientId") long clientId);
    
    List<Shop> findByNameContainsOrAliasContains(@Param("name") String name, @Param("alias") String alias);
}
