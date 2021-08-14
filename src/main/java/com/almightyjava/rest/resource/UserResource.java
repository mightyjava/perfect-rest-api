package com.almightyjava.rest.resource;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.almightyjava.rest.data.UserData;

public interface UserResource extends IQueryResource<UserData> {

	@PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> authenticate(@RequestBody UserData userData);

	@PostMapping(value = "/changepassword/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> changePassword(@PathVariable UUID userUuid, @RequestBody String newPassword);

	@PostMapping(value = "/forgotpassword", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> forgotYourPassword(@RequestBody String emailAddress);
}
