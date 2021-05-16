package com.nixsolutions.usik.model.repository;

import com.nixsolutions.usik.model.entities.IEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoRepositoryBean
public interface IRepository<E extends IEntity> extends org.springframework.data.repository.Repository<E, Long> {
    List<E> findAll();
}
