package com.nixsolutions.usik.model.service;

import com.nixsolutions.usik.constants.EntityConstants;
import com.nixsolutions.usik.constants.FieldConstants;
import com.nixsolutions.usik.exeptions.ResourceNotFoundException;
import com.nixsolutions.usik.model.entities.IEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService<E extends IEntity> {
    default void save(E entity) {
        getRepository().save(entity);
    }

    default void delete(long id) {
        getRepository().deleteById(id);
    }

    default List<E> findAll() {
        return (List<E>) getRepository().findAll();
    }

    default E findById(long id) {
        return getRepository().findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        EntityConstants.getClassNameOfGeneric(this.getClass()),
                        FieldConstants.ID, id));
    }

    CrudRepository<E, Long> getRepository();
}
