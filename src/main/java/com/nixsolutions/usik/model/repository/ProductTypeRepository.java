package com.nixsolutions.usik.model.repository;

import com.nixsolutions.usik.model.entities.ProductType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductTypeRepository extends CrudRepository<ProductType, Long>, IRepositoryWithName<ProductType> {
}
