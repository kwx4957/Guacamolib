package com.example.demo.domain.comment.dto;

import com.example.demo.domain.comment.domain.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private long id;
    private long topicId;
    @JsonProperty(value="index")
    private long commentIndex;
    private String content;
    private LocalDateTime createAt;

    public static CommentResponse of(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .topicId(comment.getTopicId())
                .commentIndex(comment.getTopicId()) //????????
                .content(comment.getContent())
                .createAt(comment.getCreatedAt())
                .build();
    }
}
