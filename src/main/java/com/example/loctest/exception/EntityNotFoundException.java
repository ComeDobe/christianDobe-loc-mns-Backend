package com.example.loctest.exception;

public class EntityNotFoundException extends RuntimeException {

    private final ErreurCode erreurCode;

    public EntityNotFoundException(ErreurCode erreurCode) {
        super(erreurCode.getMessage());
        this.erreurCode = erreurCode;
    }

    public ErreurCode getErreurCode() {
        return erreurCode;
    }
}
