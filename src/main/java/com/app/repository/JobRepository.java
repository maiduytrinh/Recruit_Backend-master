package com.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entities.Job;

@Repository
@Transactional
public interface JobRepository extends JpaRepository<Job, Integer>{
    @Query(value = "SELECT A FROM Job A where (A.name like %:search% ) ")
    Page<Job> paginationJob(org.springframework.data.domain.Pageable pageable, @Param("search") String search);

    @Query(value = "UPDATE Job J SET J.countJob = :count WHERE J.id = :id")
    @Modifying
    void UpdateCountJob(@Param("count") Integer count, @Param("id") Integer id);

    @Query(value = "SELECT A FROM Job A ORDER BY A.countJob DESC LIMIT 2")
    List<Job> getJobHot();
}
