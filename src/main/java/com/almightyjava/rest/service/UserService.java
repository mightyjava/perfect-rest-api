package com.almightyjava.rest.service;

import com.almightyjava.rest.domain.User;

public interface UserService extends IService<User> {
	User findByEmailAddress(String emailAddress);
}
