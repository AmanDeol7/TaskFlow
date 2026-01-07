package com.demo.taskflow.common;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ApiError {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}
