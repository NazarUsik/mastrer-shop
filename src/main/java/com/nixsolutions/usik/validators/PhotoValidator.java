package com.nixsolutions.usik.validators;

import com.nixsolutions.usik.exeptions.ResourceNotFoundException;
import com.nixsolutions.usik.model.entities.Photo;
import com.nixsolutions.usik.model.service.IService;
import com.nixsolutions.usik.model.service.PhotoService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Component
public class PhotoValidator implements EntityValidator<Photo> {

    @NonNull
    @Getter
    IService<Photo> service;

    @Override
    public ResponseEntity<?> validateForSave(Photo photo) {
        try {
            ((PhotoService) service).findByFileName(photo.getFileName());
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (ResourceNotFoundException ex) {
            return null;
        }
    }
}
