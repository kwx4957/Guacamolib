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
public class TopicResponse {
    private long id;
    private String title;
    private String content;
    private Option firstOption;
    private Option secondOption;
    private LocalDateTime createAt;
    public static TopicResponse of(Topic topic){
        return TopicResponse.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .content(topic.getContent())
                .firstOption(new Option(topic.getFirstOption(),topic.getSelectedOption().getFirstOption()))
                .secondOption(new Option(topic.getSecondOption(),topic.getSelectedOption().getSecondOption()))
                .createAt(topic.getCreatedAt())
                .build();
    }

}
