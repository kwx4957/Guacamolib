package com.example.demo.repository;

import com.example.demo.entity.SelectedOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedOptionRepository extends JpaRepository<SelectedOption,Long> {
}
