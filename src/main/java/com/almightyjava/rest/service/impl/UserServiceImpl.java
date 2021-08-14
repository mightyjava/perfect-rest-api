package com.almightyjava.rest.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almightyjava.rest.domain.User;
import com.almightyjava.rest.exception.DataException;
import com.almightyjava.rest.repository.UserRepository;
import com.almightyjava.rest.service.UserService;
import com.almightyjava.rest.utils.MethodUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByEmailAddress(String emailAddress) {
		return userRepository.findByEmailAddress(emailAddress);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(UUID uuid) {
		log.info("UserServiceImpl : findById");
		Optional<User> user = userRepository.findById(uuid);
		if (!user.isPresent()) {
			throw new DataException("User UUID is invalid");
		}
		return user;
	}

	@Override
	public User save(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User update(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public String deleteById(UUID uuid) {
		log.info("UserServiceImpl : deleteById");
		Optional<User> user = userRepository.findById(uuid);
		if (user.isPresent()) {
			userRepository.delete(user.get());
			return MethodUtils.convertStringToJSON("message", "User Deleted Successfully.");
		}
		throw new DataException("User UUID is invalid");
	}

}
