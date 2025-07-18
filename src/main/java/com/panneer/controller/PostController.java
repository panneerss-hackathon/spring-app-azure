package com.panneer.controller;

import com.panneer.document.Post;
import com.panneer.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Post createPost(@RequestParam Long userId,
                           @RequestParam String imageUrl,
                           @RequestParam String caption,
                           @RequestParam(required = false) List<String> hashtags) {
        return postService.createPost(userId, imageUrl, caption, hashtags);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/user/{userId}")
    public List<Post> getUserPosts(@PathVariable Long userId) {
        return postService.getPostsByUser(userId);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable String postId) {
        postService.deletePost(postId);
    }
}