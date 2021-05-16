package com.nixsolutions.usik.model.repository;

import com.nixsolutions.usik.model.entities.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long>, IRepository<Client> {
    Optional<Client> findByEmail(@Param("email") String email);
}
