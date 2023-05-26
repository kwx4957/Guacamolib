package com.example.demo.domain.comment.domain;

import com.example.demo.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "Comment")
@NoArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private String password;
    private long topicId; // N to 1

    @Builder
    public Comment(String content, String password, long topicId) {
        this.content = content;
        this.password = password;
        this.topicId = topicId;
    }
}
