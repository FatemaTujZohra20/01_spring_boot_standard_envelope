package com.standard_envelope01.standard_envelope.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

/**
 * The Global Envelope.
 * @param <T> The type of the actual data we are sending back.
 */
@Builder
public record ApiResponse<T>(
        String message,
        boolean success,
        
        // If data is null (like in a delete operation),
        // Jackson will hide this field from the JSON entirely.
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T data
) {
    // Static helper for successful responses with data
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .message(message)
                .success(true)
                .data(data)
                .build();
    }
    
    // Static helper for success messages without data (e.g. "User Deleted")
    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .message(message)
                .success(true)
                .build();
    }
}
