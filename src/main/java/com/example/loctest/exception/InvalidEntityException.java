package com.example.loctest.exception;

public class InvalidEntityException extends RuntimeException {

    private final ErreurCode erreurCode;

    public InvalidEntityException(ErreurCode erreurCode) {
        super(erreurCode.getMessage());
        this.erreurCode = erreurCode;
    }

    public ErreurCode getErreurCode() {
        return erreurCode;
    }
}
