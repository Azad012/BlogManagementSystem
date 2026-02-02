package com.azad.BlogManagementSystem.controller;

import com.azad.BlogManagementSystem.dto.CommentRequest;
import com.azad.BlogManagementSystem.dto.CommentResponse;
import com.azad.BlogManagementSystem.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<String> addComment(@PathVariable Long postId, @Valid @RequestBody CommentRequest request, Principal principal) {
        commentService.addComment(postId, request, principal.getName());
        return ResponseEntity.ok("Comment added");
    }

    @DeleteMapping("comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, Principal principal) {
        commentService.deleteComment(commentId, principal.getName());
        return ResponseEntity.ok("Comment deleted");
    }
    // GET all comments for a specific post
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getAllCommentsByPostId(postId));
    }

    // PUT to update a comment
    @PutMapping("comments/{commentId}")
    public ResponseEntity<CommentResponse> editComment(
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequest request,
            Principal principal) {
        return ResponseEntity.ok(commentService.editComment(commentId, request, principal.getName()));
    }
}
