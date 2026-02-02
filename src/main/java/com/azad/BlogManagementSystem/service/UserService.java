package com.azad.BlogManagementSystem.service;

import com.azad.BlogManagementSystem.dto.PostResponse;
import com.azad.BlogManagementSystem.dto.UserProfileResponse;
import com.azad.BlogManagementSystem.entity.Post;
import com.azad.BlogManagementSystem.entity.User;
import com.azad.BlogManagementSystem.exception.ResourceNotFoundException;
import com.azad.BlogManagementSystem.repository.PostRepository;
import com.azad.BlogManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
// FIX 1: Correct import for readOnly support
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // FIX 2: Spring's @Transactional ensures the session stays open for the Stream and Mapping
    @Transactional(readOnly = true)
    public UserProfileResponse getUserProfile(String username) {
        // 1. Find User or throw 404
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        // 2. Fetch User's Posts
        List<Post> userPosts = postRepository.findByAuthorUsernameOrderByCreatedAtDesc(username);

        // 3. Map Posts to PostResponse DTOs
        List<PostResponse> postResponses = userPosts.stream()
                .map(this::mapToPostResponse)
                .toList();

        // 4. Build and return final Profile DTO
        return UserProfileResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                // FIX 3: Convert Enum to String if your DTO expects a String
                .role(user.getRole())
                .totalPosts(postResponses.size())
                .posts(postResponses)
                .build();
    }

    private PostResponse mapToPostResponse(Post post) {
        PostResponse res = new PostResponse();
        res.setId(post.getId());
        res.setTitle(post.getTitle());
        res.setContent(post.getContent());
        res.setLikeCount(post.getLikes() != null ? post.getLikes().size() : 0);
        res.setCommentCount(post.getComments() != null ? post.getComments().size():0);
        // This works because @Transactional is active
        res.setAuthorName(post.getAuthor().getUsername());
        res.setCreatedAt(post.getCreatedAt());
        return res;
    }
}