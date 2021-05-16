package com.nixsolutions.usik.exeptions;

public class GoogleServiceException extends RuntimeException {
    public GoogleServiceException(String message) {
        super(message);
    }

    public GoogleServiceException(Throwable cause) {
        super(cause);
    }

    public GoogleServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
