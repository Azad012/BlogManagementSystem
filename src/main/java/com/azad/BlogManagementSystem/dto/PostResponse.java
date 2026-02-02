package com.azad.BlogManagementSystem.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String authorName;
    private LocalDateTime createdAt;
    private long commentCount;
    private long likeCount;
}
