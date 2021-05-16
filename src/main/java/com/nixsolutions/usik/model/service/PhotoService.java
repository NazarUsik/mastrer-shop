package com.nixsolutions.usik.model.service;

import com.nixsolutions.usik.constants.EntityConstants;
import com.nixsolutions.usik.constants.FieldConstants;
import com.nixsolutions.usik.exeptions.ResourceNotFoundException;
import com.nixsolutions.usik.model.entities.Photo;
import com.nixsolutions.usik.model.repository.PhotoRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
@Transactional
public class PhotoService implements IService<Photo> {
    @NonNull
    @Getter
    CrudRepository<Photo, Long> repository;

    public Photo findByFileName(String fileName) {
        return ((PhotoRepository) repository).findByFileName(fileName)
                .orElseThrow(() -> new ResourceNotFoundException
                        (EntityConstants.PHOTO, FieldConstants.NAME, fileName));
    }
}
