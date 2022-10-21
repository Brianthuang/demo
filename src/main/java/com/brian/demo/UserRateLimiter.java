package com.brian.demo;

import java.util.HashMap;
import java.util.Map;

public class UserRateLimiter {

	private Map<Integer, RateLimiter> userRateLimiters;

	UserRateLimiter() {
		userRateLimiters = new HashMap<Integer, RateLimiter>();

	}

	public boolean accessApp(int id) {
		if (!userRateLimiters.containsKey(id)) {
			userRateLimiters.put(Integer.valueOf(id), new RateLimiter(5, 1));
		}

		return userRateLimiters.get(id).rateLimit();
	}

}
