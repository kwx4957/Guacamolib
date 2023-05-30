package com.example.demo.domain.comment.domain;

import com.example.demo.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Table(name = "Comment")
@NoArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String password;
    @ColumnDefault("0")
    private Long indexComment;
    private Long topicId; // N to 1

    @Builder
    public Comment(String content, String password, long topicId, long index) {
        this.indexComment = index;
        this.content = content;
        this.password = password;
        this.topicId = topicId;
    }
}
