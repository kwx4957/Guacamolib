package com.example.demo.domain.comment.dto;


import com.example.demo.domain.comment.domain.Comment;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentRequest {
    @NotBlank
    private String content;
    @NotBlank
    private String password;

    public Comment toEntity(long topicId){
        return Comment.builder()
                .content(this.content)
                .password(this.password)
                .topicId(topicId)
                .build();
    }
}
