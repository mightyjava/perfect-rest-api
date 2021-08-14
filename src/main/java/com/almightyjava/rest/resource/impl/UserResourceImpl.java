package com.almightyjava.rest.resource.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.almightyjava.rest.config.JwtTokenProvider;
import com.almightyjava.rest.data.UserData;
import com.almightyjava.rest.domain.Employee;
import com.almightyjava.rest.domain.User;
import com.almightyjava.rest.exception.DataException;
import com.almightyjava.rest.resource.UserResource;
import com.almightyjava.rest.service.EmployeeService;
import com.almightyjava.rest.service.UserService;
import com.almightyjava.rest.utils.MethodUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserResourceImpl implements UserResource {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@Override
	public ResponseEntity<String> authenticate(UserData userData) {
		log.info("EmployeeResourceImpl : authenticate");
		JSONObject jsonObject = new JSONObject();
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userData.getEmailAddress(), userData.getPassword()));
			if (authentication.isAuthenticated()) {
				String emailAddress = userData.getEmailAddress();
				jsonObject.put("name", authentication.getName());
				jsonObject.put("authorities", authentication.getAuthorities());
				jsonObject.put("token", tokenProvider.createToken(emailAddress,
						userService.findByEmailAddress(emailAddress).getRole()));
				return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
			}
		} catch (JSONException exception) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(MethodUtils.convertStringToJSON("exception", exception.getMessage()));
		}
		return null;
	}

	@Override
	public ResponseEntity<String> changePassword(UUID employeeUuid, String newPassword) {
		Optional<Employee> employee = employeeService.findById(employeeUuid);
		if (employee.isPresent()) {
			User user = employee.get().getUser();
			user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
			userService.update(user);
			return ResponseEntity.status(HttpStatus.OK)
					.body(MethodUtils.convertStringToJSON("message", "Password Changed Succesfully"));
		}
		throw new DataException("Employee Uuid is not valid.");
	}

	@Override
	public ResponseEntity<String> forgotYourPassword(String emailAddress) {
		Optional<User> employee = Optional.ofNullable(userService.findByEmailAddress(emailAddress));
		if (employee.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(MethodUtils.convertStringToJSON("message", "Password sent to your email"));
		}
		throw new DataException("Email Address is not valid.");
	}

	@Override
	public ResponseEntity<List<UserData>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(UserData.parseUsers(userService.findAll()));
	}

	@Override
	public ResponseEntity<UserData> findById(UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(UserData.parseUser(userService.findById(id).get()));
	}

}
