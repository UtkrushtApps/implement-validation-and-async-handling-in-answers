package com.teamboard.user.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private List<String> messages;
    private String path;
}
