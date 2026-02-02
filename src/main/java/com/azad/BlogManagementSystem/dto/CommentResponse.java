package com.azad.BlogManagementSystem.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentResponse {
    private Long id;
    private String text;
    private String authorName;
    private LocalDateTime createdAt;
}
