package com.nixsolutions.usik.model.service;

import com.nixsolutions.usik.constants.EntityConstants;
import com.nixsolutions.usik.constants.FieldConstants;
import com.nixsolutions.usik.exeptions.ResourceNotFoundException;
import com.nixsolutions.usik.model.entities.IEntity;
import com.nixsolutions.usik.model.repository.IRepositoryWithName;
import org.springframework.stereotype.Service;

@Service
public interface IServiceWithName<E extends IEntity> extends IService<E> {
    default E findByName(String name) {
        return getRepositoryWithName().findByNameIgnoreCase(name).orElseThrow(
                () -> new ResourceNotFoundException(
                        EntityConstants.getClassNameOfGeneric(this.getClass()),
                        FieldConstants.NAME, name));
    }

    IRepositoryWithName<E> getRepositoryWithName();
}
