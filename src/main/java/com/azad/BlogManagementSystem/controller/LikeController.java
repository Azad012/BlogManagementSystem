package com.azad.BlogManagementSystem.controller;

import com.azad.BlogManagementSystem.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<String> toggleLike(@PathVariable Long postId, Principal principal) {
        return ResponseEntity.ok(likeService.toggleLike(postId, principal.getName()));
    }
}
