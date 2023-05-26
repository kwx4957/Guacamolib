package com.example.demo.domain.topic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Option {
    private String content;
    private long count;

    public Option(String content, long count) {
        this.content = content;
        this.count = count;
    }
}
