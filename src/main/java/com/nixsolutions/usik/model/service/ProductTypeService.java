package com.nixsolutions.usik.model.service;

import com.nixsolutions.usik.model.entities.ProductType;
import com.nixsolutions.usik.model.repository.IRepositoryWithName;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
@Transactional
public class ProductTypeService implements IServiceWithName<ProductType> {
    @NonNull
    Repository<ProductType, Long> repository;
    
    @Override
    public IRepositoryWithName<ProductType> getRepositoryWithName() {
        return (IRepositoryWithName<ProductType>) repository;
    }

    @Override
    public CrudRepository<ProductType, Long> getRepository() {
        return (CrudRepository<ProductType, Long>) repository;
    }
}
