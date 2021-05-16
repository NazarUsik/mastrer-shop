package com.nixsolutions.usik.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.nixsolutions.usik.exeptions.GoogleServiceException;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GoogleDriveUtils {

    private final String APPLICATION_NAME = "Google Drive API Java Quickstart";
    private final String CLIENT_SECRET_FILE_NAME = "credentials.json";

    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private final java.io.File CREDENTIALS_FOLDER =
            new java.io.File(System.getProperty("user.home"), "credentials");

    private final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);

    private final FileDataStoreFactory DATA_STORE_FACTORY;

    private final HttpTransport HTTP_TRANSPORT;

    private Drive _driveService;

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(CREDENTIALS_FOLDER);
        } catch (Exception ex) {
            throw new GoogleServiceException(ex);
        }
    }

    @SneakyThrows
    private Credential getCredentials() {
        java.io.File clientSecretFilePath = new java.io.File(CREDENTIALS_FOLDER, CLIENT_SECRET_FILE_NAME);

        if (!clientSecretFilePath.exists()) {
            throw new FileNotFoundException("Please copy " +
                    CLIENT_SECRET_FILE_NAME + " to folder: "
                    + CREDENTIALS_FOLDER.getAbsolutePath());
        }

        InputStream in = new FileInputStream(clientSecretFilePath);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
                clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    @SneakyThrows
    public Drive getDriveService() {
        if (_driveService != null) {
            return _driveService;
        }
        _driveService = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials())
                .setApplicationName(APPLICATION_NAME).build();
        return _driveService;
    }

}