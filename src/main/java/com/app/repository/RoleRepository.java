package com.app.repository;

import org.springframework.stereotype.Repository;

import com.app.entities.Roles;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer>{
    
}
