package com.example.demo.domain.topic.domain;

import com.example.demo.domain.topic.dto.TopicRequest;
import com.example.demo.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Table(name = "SelectedOption")
@NoArgsConstructor
@AllArgsConstructor
public class SelectedOption extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ColumnDefault("0")
    private long firstOption;
    @ColumnDefault("0")
    private long secondOption;
    public void increaseFirstOption(){
        this.firstOption++;
    }
    public void increaseSecondOption(){
        this.secondOption++;
    }

    @Override
    public String toString() {
        return "SelectedOption{" +
                "id=" + id +
                ", firstOption=" + firstOption +
                ", secondOption=" + secondOption +
                '}';
    }
}
