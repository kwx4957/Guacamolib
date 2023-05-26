package com.example.demo.domain.topic.dto;

import com.example.demo.domain.comment.dto.CommentResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TopicPageResponse {
    private int statusCode;
    private List<?> data = new ArrayList<>();
    @Builder
    public TopicPageResponse(int statusCode, List<?> data) {
        this.statusCode = statusCode;
        this.data = data;
    }

}
