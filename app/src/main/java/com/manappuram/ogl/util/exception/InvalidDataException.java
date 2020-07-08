package com.manappuram.ogl.util.exception;

public class InvalidDataException extends OGLException{
    public int code = 0;
    public String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InvalidDataException(String code) {
        this.code = Integer.valueOf(code);
    }
}
