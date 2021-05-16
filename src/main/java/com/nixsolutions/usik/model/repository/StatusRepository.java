package com.nixsolutions.usik.model.repository;

import com.nixsolutions.usik.model.entities.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StatusRepository extends CrudRepository<Status, Long>, IRepositoryWithName<Status> {
}
