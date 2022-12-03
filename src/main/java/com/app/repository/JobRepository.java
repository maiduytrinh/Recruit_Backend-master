package com.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entities.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer>{
    @Query(value = "SELECT A FROM Job A where (A.name like %:search% ) ")
    Page<Job> paginationJob(org.springframework.data.domain.Pageable pageable, @Param("search") String search);

}
