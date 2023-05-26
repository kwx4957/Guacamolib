package com.example.demo.domain.comment.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentPageResponse {
    private int statusCode;
    private List<CommentResponse> data = new ArrayList<>();

    @Builder
    public CommentPageResponse(int statusCode, List<CommentResponse> data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}
