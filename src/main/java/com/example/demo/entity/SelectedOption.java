package com.example.demo.entity;

import com.example.demo.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "SelectedOption")
@NoArgsConstructor
public class SelectedOption extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long firstOption;
    private long secondOption;
    private long topicId; //1to1
}
