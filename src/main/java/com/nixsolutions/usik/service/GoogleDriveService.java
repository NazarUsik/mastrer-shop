package com.nixsolutions.usik.service;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.nixsolutions.usik.utils.GoogleDriveUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;

@Component
public class GoogleDriveService {

    private File _createFile(String parentFolderId, String fileName,
                             AbstractInputStreamContent content) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();

        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        fileMetadata.setParents(Collections.singletonList(parentFolderId));

        return driveService.files().create(fileMetadata, content)
                .setFields("id, webContentLink, webViewLink, parents").execute();
    }

    public File createFile(String parentFolderId, String contentType,
                           String fileName, byte[] data) throws IOException {
        return _createFile(parentFolderId, fileName, new ByteArrayContent(contentType, data));
    }

    public File createFile(String parentFolderId, String contentType,
                           String fileName, java.io.File file) throws IOException {
        return _createFile(parentFolderId, fileName, new FileContent(contentType, file));
    }

    public File createFile(String parentFolderId, String contentType,
                           String fileName, InputStream is) throws IOException {
        return _createFile(parentFolderId, fileName, new InputStreamContent(contentType, is));
    }

    public OutputStream getFileById(String fileId) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();
        OutputStream os = new ByteArrayOutputStream();
        driveService.files().get(fileId).executeMediaAndDownloadTo(os);
        return os;
    }

}
