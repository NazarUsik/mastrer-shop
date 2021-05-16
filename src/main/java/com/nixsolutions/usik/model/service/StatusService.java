package com.nixsolutions.usik.model.service;

import com.nixsolutions.usik.model.entities.Status;
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
public class StatusService implements IServiceWithName<Status> {
    @NonNull
    Repository<Status, Long> repository;

    @Override
    public CrudRepository<Status, Long> getRepository() {
        return (CrudRepository<Status, Long>) repository;
    }

    @Override
    public IRepositoryWithName<Status> getRepositoryWithName() {
        return (IRepositoryWithName<Status>) repository;
    }
}
