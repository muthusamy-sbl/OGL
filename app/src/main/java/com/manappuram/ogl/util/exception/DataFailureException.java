package com.manappuram.ogl.util.exception;

/**
 * This exception is raised when the server returns invalid request and the request id is null
 */

public class DataFailureException extends OGLException {

    public int code = 0;
    public String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataFailureException(String code) {
        this.code = Integer.valueOf(code);
    }

}
