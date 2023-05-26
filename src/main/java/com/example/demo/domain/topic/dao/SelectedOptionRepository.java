package com.example.demo.domain.topic.dao;

import com.example.demo.domain.topic.domain.SelectedOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedOptionRepository extends JpaRepository<SelectedOption,Long> {
}
