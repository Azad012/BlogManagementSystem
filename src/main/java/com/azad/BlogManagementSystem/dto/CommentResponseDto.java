package com.azad.BlogManagementSystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDto {
    private Long id;
    private String comment;
    private String username;
    private LocalDateTime createAt;

}
