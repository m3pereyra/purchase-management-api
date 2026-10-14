package com.marcelo.purchase.exception;

public record ApiError(
        int status,
        String error,
        String message
) {
}