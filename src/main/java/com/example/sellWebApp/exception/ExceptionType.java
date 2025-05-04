package com.example.sellWebApp.exception;

public enum ExceptionType {
    LOGIN_ATTEMPT("login.attempt"),
    ENTITY_NOT_FOUND("not.found"),
    DUPLICATE_ENTITY("duplicate"),
    ENTITY_EXCEPTION("exception"),
    ENTITY_INVALID("invalid"),
    PARAM_NOT_FOUND("param.not.found"),
    TOKEN_EXPIRE("token.expire");


    String value;

    ExceptionType(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}
