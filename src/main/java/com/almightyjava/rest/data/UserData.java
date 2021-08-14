package com.almightyjava.rest.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.almightyjava.rest.domain.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
public class UserData {
	private UUID userUuid;
	private String emailAddress;
	private String password;

	public static List<UserData> parseUsers(List<User> users) {
		log.info("UserData : parseUsers");
		List<UserData> userDatas = new ArrayList<>();
		users.forEach(user -> {
			UserData userData = new UserData();
			BeanUtils.copyProperties(user, userData);
			userDatas.add(userData);
		});
		return userDatas;
	}

	public static UserData parseUser(User user) {
		log.info("UserData : parseUser");
		UserData userData = new UserData();
		BeanUtils.copyProperties(user, userData);
		return userData;
	}
}
