package com.almightyjava.rest.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.almightyjava.rest.domain.User;
import com.almightyjava.rest.exception.DataException;
import com.almightyjava.rest.repository.UserRepository;
import com.almightyjava.rest.utils.ConstantUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws DataException {
		log.info("UserDetailsServiceImpl : loadUserByUsername");
		User user = userRepository.findByEmailAddress(email);
		if (user == null) {
			throw new DataException(email + " not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmailAddress(), user.getPassword(),
				getGrantedAuthority(user));
	}

	private Collection<GrantedAuthority> getGrantedAuthority(User user) {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if (user.getRole().getRoleName().equalsIgnoreCase(ConstantUtils.ADMIN.toString())) {
			authorities.add(new SimpleGrantedAuthority(ConstantUtils.ADMIN.toString()));
		}
		authorities.add(new SimpleGrantedAuthority(ConstantUtils.USER.toString()));
		return authorities;
	}

}