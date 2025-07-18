package com.panneer.controller;

import com.panneer.entity.Like;
import com.panneer.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public void likePost(@RequestParam Long userId, @RequestParam String postId) {
        likeService.likePost(userId, postId);
    }

    @DeleteMapping
    public void unlikePost(@RequestParam Long userId, @RequestParam String postId) {
        likeService.unlikePost(userId, postId);
    }

    @GetMapping("/{postId}")
    public List<Like> getLikes(@PathVariable String postId) {
        return likeService.getLikesForPost(postId);
    }
}