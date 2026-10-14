
package com.marcelo.purchase.exception;

import java.util.Map;

public record ValidationApiError(
        int status,
        String error,
        String message,
        Map<String, String> fields
) {
}
