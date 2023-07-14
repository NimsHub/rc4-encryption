package org.nimshub.exceptions;

import java.io.Serial;

/**
 * Runtime exception for Invalid Encryption Keys
 */
public class InvalidEncryptionKeyException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -2412232436238451574L;
    public InvalidEncryptionKeyException(String message) {
        super(message);
    }
}
