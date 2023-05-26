package com.example.demo.domain.comment.dto;

import com.example.demo.domain.comment.domain.Comment;
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
    private String content;
    private LocalDateTime createAt;

    public static CommentResponse of(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createAt(comment.getCreatedAt())
                .build();
    }
}
