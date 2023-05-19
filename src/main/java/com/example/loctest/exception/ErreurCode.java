package com.example.loctest.exception;

public enum ErreurCode {

    ENTITY_NOT_FOUND("ERR001", "L'entité n'a pas été trouvée."),
    INVALID_ENTITY("ERR002", "L'entité est invalide."),
    INVALID_OPERATION("ERR003", "L'opération est invalide.");

    private final String code;
    private final String message;

    ErreurCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
