package com.manappuram.ogl.util.exception;

/**
 * This exception is thrown when the session is expired
 */

public class InvalidSessionException extends DataFailureException {


    public InvalidSessionException(String code) {
        super(code);
    }
}
