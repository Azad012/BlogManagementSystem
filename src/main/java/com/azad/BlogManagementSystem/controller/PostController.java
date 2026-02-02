package com.azad.BlogManagementSystem.controller;

import com.azad.BlogManagementSystem.dto.PostRequest;
import com.azad.BlogManagementSystem.dto.PostResponse;
import com.azad.BlogManagementSystem.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:5173")
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }



    @PostMapping
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostRequest request, Principal principal) {
        return new ResponseEntity<>(postService.createPost(request, principal.getName()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @Valid @RequestBody PostRequest request, Principal principal) {
        return ResponseEntity.ok(postService.updatePost(id, request, principal.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id, Principal principal) {
        postService.deletePost(id, principal.getName());
        return ResponseEntity.ok("Post deleted successfully");
    }
    // Endpoint: GET /api/posts/user/azad_java
    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable String username) {
        List<PostResponse> posts = postService.getAllPostsByUsername(username);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/my-posts")
    public ResponseEntity<List<PostResponse>> getMyPosts(Principal principal) {
        // principal.getName() automatically gets the username from the JWT
        return ResponseEntity.ok(postService.getAllPostsByUsername(principal.getName()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }
}
