package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.ListProfiles;

@Transactional
@Repository
public interface ListProfileReponsitory extends JpaRepository<ListProfiles, Integer>{
    @Query(value = "DELETE FROM ListProfiles WHERE listProfileId.users.id =:userId and listProfileId.jobs.id =:jobId")
    @Modifying
    int DelListProfile(int userId, int jobId);
    @Query(value = "INSERT INTO `recruit`.`list_profile` (`user_id`, `list_job_id`) VALUES (:playlistId, :songId)", nativeQuery = true)
    @Modifying
    int AddListProfile(int playlistId, int songId);
}
