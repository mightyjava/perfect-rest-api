package com.almightyjava.rest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almightyjava.rest.domain.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {

	public Role findByRoleName(String roleName);
}
