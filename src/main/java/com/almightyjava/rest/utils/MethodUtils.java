package com.almightyjava.rest.utils;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class MethodUtils {
	private static Random random = new SecureRandom();

	private MethodUtils() {
	}

	private static Stream<Character> getRandomAlphabets(int count, boolean upperCase) {
		IntStream characters = null;
		if (upperCase) {
			characters = random.ints(count, 65, 90);
		} else {
			characters = random.ints(count, 97, 122);
		}
		return characters.mapToObj(data -> (char) data);
	}

	private static Stream<Character> getRandomSpecialChars(int count) {
		IntStream specialChars = random.ints(count, 33, 45);
		return specialChars.mapToObj(data -> (char) data);
	}

	private static Stream<Character> getRandomNumbers(int count) {
		IntStream numbers = random.ints(count, 48, 57);
		return numbers.mapToObj(data -> (char) data);
	}

	public static String generateRandomPassword() {
		Stream<Character> pwdStream = Stream.concat(getRandomNumbers(2), Stream.concat(getRandomSpecialChars(2),
				Stream.concat(getRandomAlphabets(2, true), getRandomAlphabets(4, false))));
		List<Character> charList = pwdStream.collect(Collectors.toList());
		Collections.shuffle(charList);
		String password = charList.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
				.toString();
		System.out.println(password);
		return new BCryptPasswordEncoder().encode(password);
	}

	public static String convertStringToJSON(String key, String value) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	public static String prepareErrorJSON(HttpStatus status, Exception ex) {
		JSONObject errorJSON = new JSONObject();
		try {
			errorJSON.put("status", status.value());
			errorJSON.put("error", status.getReasonPhrase());
			errorJSON.put("message", ex.getMessage().split(":")[0]);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return errorJSON.toString();
	}
}
