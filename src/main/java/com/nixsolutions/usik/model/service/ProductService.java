package com.nixsolutions.usik.model.service;

import com.nixsolutions.usik.constants.EntityConstants;
import com.nixsolutions.usik.constants.FieldConstants;
import com.nixsolutions.usik.exeptions.ResourceNotFoundException;
import com.nixsolutions.usik.model.entities.Product;
import com.nixsolutions.usik.model.repository.IRepositoryWithName;
import com.nixsolutions.usik.model.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
@Transactional
public class ProductService implements IServiceWithName<Product> {
    @NonNull
    Repository<Product, Long> repository;

    @Override
    public CrudRepository<Product, Long> getRepository() {
        return (CrudRepository<Product, Long>) repository;
    }

    @Override
    public IRepositoryWithName<Product> getRepositoryWithName() {
        return (IRepositoryWithName<Product>) repository;
    }

    public List<Product> findAllByShopId(long shopId) {
        return ((ProductRepository) repository).findAllByShopId(shopId);
    }

    public Product findByAlias(String alias) {
        return ((ProductRepository) repository).findByAlias(alias).orElseThrow(
                () -> new ResourceNotFoundException(
                        EntityConstants.PRODUCT, FieldConstants.ALIAS, alias));
    }

    public List<Product> search(String keyword) {
        return ((ProductRepository) repository).findByNameContainsOrAliasContains(keyword, keyword);
    }
}
