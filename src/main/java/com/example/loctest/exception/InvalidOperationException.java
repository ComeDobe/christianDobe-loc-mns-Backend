package com.example.loctest.exception;

public class InvalidOperationException extends RuntimeException {

    private final ErreurCode erreurCode;

    public InvalidOperationException(ErreurCode erreurCode) {
        super(erreurCode.getMessage());
        this.erreurCode = erreurCode;
    }

    public ErreurCode getErreurCode() {
        return erreurCode;
    }
}
