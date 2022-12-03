package com.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
    @Query(value = "SELECT A FROM Company A where (A.name like %:search% ) ")
    Page<Company> paginationCompany(Pageable pageable,@Param("search") String search);
}
