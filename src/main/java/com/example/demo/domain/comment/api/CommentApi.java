package com.example.demo.domain.comment.api;

import com.example.demo.domain.comment.application.CommentService;
import com.example.demo.domain.comment.dto.CommentRequest;
import com.example.demo.domain.topic.exception.TopicNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class CommentApi {
    private final CommentService commentService;

    public CommentApi(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/topics/{topicId}/comments")
    public ResponseEntity<?> getListComments(@PathVariable long topicId, Pageable pageable){
        return ResponseEntity.ok(commentService.getListComment(topicId, pageable));
    }
    @PostMapping("/topics/{topicId}/comments")
    public ResponseEntity<?> createComment(@PathVariable long topicId,
                                        @RequestBody @Valid CommentRequest commentRequest){
        long commentId = commentService.createComment(topicId,commentRequest);
        return ResponseEntity.ok(convertToMap("createdCommentId",commentId));
    }
    @DeleteMapping("/topics/{topicId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable long topicId,
                       @PathVariable long commentId,
                       @RequestBody Map<String,String> passwordMap){
        commentService.deleteComment(topicId, commentId, passwordMap.get("password"));
        return ResponseEntity.ok(convertToMap("deletedCommentId",commentId));
    }
    private Map<String,Long> convertToMap(String name, long topicId){
        return Map.of(name,topicId);
    }


}
