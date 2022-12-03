package com.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entities.ListJobs;

@Repository
public interface ListJobRepository extends JpaRepository<ListJobs, Integer>{
    @Query(value = "SELECT A FROM ListJobs A where (A.name like %:search% )")
    Page<ListJobs> paginationListJob(Pageable pageable, @Param("search") String search);
    // @Query(value = "DELETE FROM ListProfiles WHERE listProfileId.users.id =:userId and listProfileId.jobs.id =:jobId")
    // @Modifying
    // int DelSong(int userId, int jobId);
    // @Query(value = "INSERT INTO `t2cmp3db`.`play_list_songs` (`play_list_id`, `songs_id`) VALUES (:playlistId, :songId)", nativeQuery = true)
    // @Modifying
    // int AddSong(int playlistId, int songId);
}
