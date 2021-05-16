package com.nixsolutions.usik.model.service;

import com.nixsolutions.usik.model.entities.Role;
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
public class RoleService implements IServiceWithName<Role> {
    @NonNull
    Repository<Role, Long> repository;

    @Override
    public CrudRepository<Role, Long> getRepository() {
        return (CrudRepository<Role, Long>) repository;
    }

    @Override
    public IRepositoryWithName<Role> getRepositoryWithName() {
        return (IRepositoryWithName<Role>) repository;
    }
}
