package com.example.demo.domain.topic.domain;

import com.example.demo.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "Topic")
@NoArgsConstructor
public class Topic extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String topic;
    private String content;
    private String firstOption;
    private String secondOption;
    private String password;
    private long views;
    private long commentCounts;
}
