package com.example.demo.domain.topic.dao;

import com.example.demo.domain.topic.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TopicRepository extends JpaRepository<Topic,Long>, PagingAndSortingRepository<Topic,Long> {
    Page<Topic> findAll(Pageable pageable);
}
