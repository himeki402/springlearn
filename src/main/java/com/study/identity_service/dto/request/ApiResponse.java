package com.study.identity_service.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
public class ApiResponse<T> {
    private int code;
    private String message;
    private T result;
}
