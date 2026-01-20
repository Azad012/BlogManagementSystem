package com.azad.BlogManagementSystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String username;
    private Long likesCount;
    private Long commentsCount;
    private LocalDateTime createAt;
}
