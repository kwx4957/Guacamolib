package com.example.demo.domain.topic.dto;

import com.example.demo.domain.topic.domain.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicPageResponse {
    private long id;
    private String title;
    private String content;
    private Option firstOption;
    private Option secondOption;
    private long voteCount;
    private long commentCount;
    private LocalDateTime createdAt;

    public static TopicPageResponse of(Topic topic){
        return TopicPageResponse.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .content(topic.getContent())
                .firstOption(new Option(topic.getFirstOption(),topic.getSelectedOption().getFirstOption()))
                .secondOption(new Option(topic.getSecondOption(),topic.getSelectedOption().getSecondOption()))
                .voteCount(topic.getSelectedOption().getTotalOptionCount())
                .commentCount(topic.getCommentCounts())
                .createdAt(topic.getCreatedAt())
                .build();
    }

}