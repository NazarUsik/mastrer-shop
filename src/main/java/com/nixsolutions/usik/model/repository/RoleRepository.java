package com.nixsolutions.usik.model.repository;

import com.nixsolutions.usik.model.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long>, IRepositoryWithName<Role> {
}
