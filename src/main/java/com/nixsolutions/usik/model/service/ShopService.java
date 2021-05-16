package com.nixsolutions.usik.model.service;

import com.nixsolutions.usik.constants.EntityConstants;
import com.nixsolutions.usik.constants.FieldConstants;
import com.nixsolutions.usik.exeptions.ResourceNotFoundException;
import com.nixsolutions.usik.model.entities.Shop;
import com.nixsolutions.usik.model.repository.IRepositoryWithName;
import com.nixsolutions.usik.model.repository.ShopRepository;
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
public class ShopService implements IServiceWithName<Shop> {
    @NonNull
    Repository<Shop, Long> repository;

    @Override
    public CrudRepository<Shop, Long> getRepository() {
        return (CrudRepository<Shop, Long>) repository;
    }

    @Override
    public IRepositoryWithName<Shop> getRepositoryWithName() {
        return (IRepositoryWithName<Shop>) repository;
    }

    public Shop findByClientId(long clientId) {
        return ((ShopRepository) repository).findByClientId(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        EntityConstants.SHOP, FieldConstants.CLIENT_ID, clientId));
    }

    public List<Shop> search(String keyword) {
        return ((ShopRepository) repository)
                .findByNameContainsOrAliasContains(keyword, keyword);
    }
}
