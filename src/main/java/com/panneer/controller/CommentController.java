package com.panneer.controller;

import com.panneer.entity.Comment;
import com.panneer.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Comment addComment(@RequestParam Long userId,
                              @RequestParam String postId,
                              @RequestParam String text) {
        return commentService.addComment(userId, postId, text);
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getComments(@PathVariable String postId) {
        return commentService.getCommentsForPost(postId);
    }

    @GetMapping("/user/{userId}")
    public List<Comment> getUserComments(@PathVariable Long userId) {
        return commentService.getCommentsByUser(userId);
    }
}