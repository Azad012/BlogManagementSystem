package com.azad.BlogManagementSystem.service;

import com.azad.BlogManagementSystem.dto.PostRequest;
import com.azad.BlogManagementSystem.dto.PostResponse;
import com.azad.BlogManagementSystem.entity.Post;
import com.azad.BlogManagementSystem.entity.User;
import com.azad.BlogManagementSystem.exception.ResourceNotFoundException;
import com.azad.BlogManagementSystem.exception.UnauthorizedActionException; // Added
import com.azad.BlogManagementSystem.repository.PostRepository;
import com.azad.BlogManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor; // Use Lombok to reduce boilerplate
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Use Spring's Transactional

import java.util.List;

@Service
@RequiredArgsConstructor // Automatically creates constructor for final fields
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponse createPost(PostRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(user);

        return mapToResponse(postRepository.save(post));
    }

    @Transactional
    public PostResponse updatePost(Long id, PostRequest request, String username) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        if (!post.getAuthor().getUsername().equals(username)) {
            throw new UnauthorizedActionException("You are not authorized to edit this post");
        }

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        return mapToResponse(postRepository.save(post));
    }

    @Transactional
    public void deletePost(Long id, String username) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        if (!post.getAuthor().getUsername().equals(username)) {
            throw new UnauthorizedActionException("You are not authorized to delete this post");
        }
        postRepository.delete(post);
    }

    // Fix: Added Transactional to prevent LazyInitializationException in mapToResponse
    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        return mapToResponse(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPostsByUsername(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }

        return postRepository.findByAuthorUsernameOrderByCreatedAtDesc(username)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private PostResponse mapToResponse(Post post) {
        PostResponse res = new PostResponse();
        res.setId(post.getId());
        res.setTitle(post.getTitle());
        res.setContent(post.getContent());
        res.setLikeCount(post.getLikes() != null ? post.getLikes().size() : 0);
        res.setCommentCount(post.getComments() != null ? post.getComments().size():0);
        // This line requires an active Session/Transaction
        res.setAuthorName(post.getAuthor().getUsername());
        res.setCreatedAt(post.getCreatedAt());
        return res;
    }
}