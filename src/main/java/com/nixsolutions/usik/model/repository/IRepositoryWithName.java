package com.nixsolutions.usik.model.repository;

import com.nixsolutions.usik.model.entities.IEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface IRepositoryWithName<E extends IEntity> extends IRepository<E> {
    Optional<E> findByNameIgnoreCase(String name);
}
