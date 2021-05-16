package com.nixsolutions.usik.controller;

import com.nixsolutions.usik.constants.LoggerConstants;
import com.nixsolutions.usik.constants.UrlConstants;
import com.nixsolutions.usik.exeptions.ResourceNotFoundException;
import com.nixsolutions.usik.model.entities.Photo;
import com.nixsolutions.usik.model.service.IService;
import com.nixsolutions.usik.service.GoogleDriveService;
import com.nixsolutions.usik.validators.EntityValidator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Log4j2
@RestController
@RequestMapping(value = UrlConstants.PHOTO)
public class PhotoController implements EntityController<Photo> {
    @NonNull
    @Getter
    IService<Photo> service;
    @NonNull
    @Getter
    EntityValidator<Photo> validator;
    @NonNull
    GoogleDriveService driveService;

    @Override
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Photo entity) {
        //TODO create new file in googledrive
        return create(entity, log);
    }

    @Override
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Photo entity) {
        //TODO update into google drive
        return update(entity, log);
    }

    @Override
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        //TODO del into google drive
        return delete(id, log);
    }

    @Override
    @GetMapping(value = "{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    ResponseEntity<byte[]> read(@PathVariable long id) {
        log.info(LoggerConstants.GET_MAPPING_WITH_PARAMETER, id);
        try {
            val photo = service.findById(id);
            try (val stream = (ByteArrayOutputStream) driveService
                    .getFileById(photo.getGoogleFileId())) {
                return new ResponseEntity<>(stream.toByteArray(), HttpStatus.OK);
            } catch (IOException exception) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
