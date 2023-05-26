package com.example.demo.domain.comment.api;

import com.example.demo.domain.comment.application.CommentService;
import com.example.demo.domain.comment.dto.CommentRequest;
import com.example.demo.domain.topic.exception.TopicNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.example.demo.global.common.HttpStatusResponseEntity.RESPONSE_CREATED;
import static com.example.demo.global.common.HttpStatusResponseEntity.RESPONSE_NO_CONTENT;

@RestController
public class CommentApi {
    private final CommentService commentService;

    public CommentApi(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/topics/{topicId}/comments")
    public ResponseEntity<?> getListComments(@PathVariable long topicId, Pageable pageable){

        if(!commentService.existTopicById(topicId)){
            throw new TopicNotFoundException("존재하지 않는 주제에요.");
        }
        return ResponseEntity.ok(commentService.getListComment(topicId, pageable));
    }
    @PostMapping("/topics/{topicId}/comments")
    public ResponseEntity<?> createComment(@PathVariable long topicId,
                                        @RequestBody @Valid CommentRequest commentRequest){

        if(!commentService.existTopicById(topicId)){
            throw new TopicNotFoundException("존재하지 않는 주제에요.");
        }

        commentService.createComment(topicId,commentRequest);
        return RESPONSE_CREATED;
    }
    @DeleteMapping("/topics/{topicId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable long topicId,
                       @PathVariable long commentId,
                       @RequestBody Map<String,String> passwordMap){

        if(!commentService.existTopicById(topicId)){
            throw new TopicNotFoundException("존재하지 않는 주제에요.");
        }

        commentService.deleteComment(commentId, passwordMap.get("password"));
        return RESPONSE_NO_CONTENT;
    }

}
