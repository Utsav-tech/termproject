package com.daneeats.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daneeats.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
