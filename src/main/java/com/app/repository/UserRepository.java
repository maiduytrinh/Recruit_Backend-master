package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query("select u from Users u where u.email =:email")
    Optional<Users> loadUser(@Param("email") String email);
}
