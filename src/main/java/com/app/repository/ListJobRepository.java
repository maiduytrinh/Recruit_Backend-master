package com.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entities.ListJobs;

@Repository
public interface ListJobRepository extends JpaRepository<ListJobs, Integer>{
    @Query(value = "SELECT A FROM ListJobs A where A.name like %:search%  and A.dateExpiration > CURDATE()")
    Page<ListJobs> paginationListJob(Pageable pageable, @Param("search") String search);
    @Query(value = "SELECT A FROM ListJobs A where A.codeAddress = :code AND A.status = 1 AND A.dateExpiration > CURDATE()")
    List<ListJobs> getListJobByCodeAddress(@Param("code") Integer code);
    @Query(value = "SELECT A FROM ListJobs A where A.job.id = :idJob and A.dateExpiration > CURDATE() AND A.status = 1")
    List<ListJobs> getListJobByIdJob(@Param("idJob") Integer idJob);
    @Query(value = "SELECT A FROM ListJobs A where (A.dateExpiration > CURDATE() AND A.status = 1) ORDER BY A.dateCreated DESC")
    List<ListJobs> getListJobNew();
    @Query(value = "SELECT A FROM ListJobs A where ((A.dateCreated BETWEEN :dateStart AND :dateEnd)  AND (A.dateExpiration > CURDATE()) AND (A.status = 1)) ORDER BY A.dateCreated DESC")
    List<ListJobs> fillterDate(@Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd);
    @Query(value = "SELECT A FROM ListJobs A where ((A.salary BETWEEN :salaryStart AND :salaryEnd)  AND (A.dateExpiration > CURDATE()) AND (A.status = 1)) ORDER BY A.dateCreated DESC")
    List<ListJobs> fillterSalary(@Param("salaryStart") Long salaryStart, @Param("salaryEnd") Long salaryEnd);
}
