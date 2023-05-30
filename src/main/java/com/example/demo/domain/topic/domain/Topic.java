package com.example.demo.domain.topic.domain;

import com.example.demo.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Table(name = "Topic")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Topic extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String firstOption;
    private String secondOption;
    private String password;
    @ColumnDefault("0")
    private Long views;
    @ColumnDefault("0")
    private Long commentCounts;
    @ColumnDefault("0")
    private Long commentIndex;
    @OneToOne(cascade = CascadeType.ALL)
    private SelectedOption selectedOption;

    @Builder
    public Topic(String title, String content, String firstOption,
                 String secondOption, String password) {
        this.title = title;
        this.content = content;
        this.firstOption = firstOption;
        this.secondOption = secondOption;
        this.password = password;
        this.selectedOption = new SelectedOption();
    }


    public long sumCommentCountAndSelectedOptions(){
        return this.getCommentCounts() + this.getSelectedOption().getTotalOptionCount();
    }
    public void addViews(){
        this.views++;
    }
    public void increaseCommentAndIndex() {
        this.commentCounts++;
        this.commentIndex++;
    }
    public void decreaseComment() {
        this.commentCounts--;
    }
}
