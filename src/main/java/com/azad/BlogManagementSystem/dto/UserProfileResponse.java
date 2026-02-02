package com.azad.BlogManagementSystem.dto;

import com.azad.BlogManagementSystem.dto.PostResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {
    private Long id;
    private String username;
    private String email;
    private String role;
    private long totalPosts;
    private List<PostResponse> posts; // Added list of posts
}