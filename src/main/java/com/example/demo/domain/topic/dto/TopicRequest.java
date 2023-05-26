package com.example.demo.domain.topic.dto;

import com.example.demo.domain.topic.domain.Topic;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TopicRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String firstOption;
    @NotBlank
    private String secondOption;
    @NotBlank
    private String password;

    public Topic toEntity() {
        return Topic.builder()
                .title(this.title)
                .content(this.content)
                .firstOption(this.firstOption)
                .secondOption(this.secondOption)
                .password(this.password)
                .build();

    }
}
