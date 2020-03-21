package com.abcd.seleniumwebpagecpature;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Data
@Builder
public class ApiResponse<T> {
    HttpStatus statusCode;
    T data;
    String message;
    String error;

    public static <T> ResponseEntity<ApiResponse<T>> ok(T body) {
        final ApiResponse<T> response = ApiResponse
                .<T>builder()
                .data(body)
                .statusCode(OK)
                .build();

        return ResponseEntity
                .status(OK)
                .body(response);
    }


    public static <T> ResponseEntity<ApiResponse<T>> error(String message) {
        final ApiResponse<T> response = ApiResponse
                .<T>builder()
                .statusCode(INTERNAL_SERVER_ERROR)
                .error(message)
                .build();

        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(response);
    }


}
