package com.brian.demo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RateLimiterTest {

	public UserRateLimiter userRateLimiter = new UserRateLimiter();

	// simulate 5 requests and trigger the rate limit, and accept the further
	// request after 1 second
	@Test
	public void testUserRateLimiter() throws InterruptedException {
		int id = 1;
		for (int i = 0; i < 5; i++) {
			assertTrue(userRateLimiter.accessApp(id));
		}
		assertFalse(userRateLimiter.accessApp(id));
		Thread.sleep(1000);
		assertTrue(userRateLimiter.accessApp(id));
	}

}
