package com.nixsolutions.usik.model.repository;

import com.nixsolutions.usik.model.entities.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PhotoRepository extends CrudRepository<Photo, Long>, IRepository<Photo> {
    Optional<Photo> findByFileName(@Param("fileName") String fileName);
}
