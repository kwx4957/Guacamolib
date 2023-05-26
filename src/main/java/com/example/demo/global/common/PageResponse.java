package com.example.demo.global.common;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageResponse {
    private int statusCode;
    private List<?> data = new ArrayList<>();
    @Builder
    public PageResponse(int statusCode, List<?> data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}
