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
    private long id;
    private String title;
    private String content;
    private String firstOption;
    private String secondOption;
    private String password;
    @ColumnDefault("0")
    private long views;
    @ColumnDefault("0")
    private long commentCounts;
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

    public void addViews(){
        this.views++;
    }
    public long sumCommentCountAndSelectedOptions(){
        return this.getCommentCounts() + this.getSelectedOption().getTotalOptionCount();
    }

}
