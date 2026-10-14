package com.marcelo.purchase.exception;

public class DuplicateTaxIdException extends RuntimeException {

    public DuplicateTaxIdException(String message) {
        super(message);
    }
}