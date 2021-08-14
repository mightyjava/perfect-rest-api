package com.almightyjava.rest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almightyjava.rest.domain.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	User findByEmailAddress(String emailAddress);
}
