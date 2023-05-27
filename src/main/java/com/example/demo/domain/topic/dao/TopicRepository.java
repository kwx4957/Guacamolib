package com.example.demo.domain.topic.dao;

import com.example.demo.domain.topic.domain.Topic;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public interface TopicRepository extends JpaRepository<Topic,Long>,
                                        PagingAndSortingRepository<Topic,Long>,
                                        JpaSpecificationExecutor<Topic> {
    Page<Topic> findAll(Pageable pageable);
    Page<Topic> findAllByCreatedAtAfter(Pageable pageable, LocalDateTime localDateTime);
}
