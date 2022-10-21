package com.brian.demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class RateLimiter {

	private int capacity;

	private AtomicInteger currentCapacity;

	private AtomicLong lastUpdatedTs;

	private int refreshRate;

	public RateLimiter(int capacity, int refreshRate) {
		this.capacity = capacity;
		this.currentCapacity = new AtomicInteger(capacity);
		this.lastUpdatedTs = new AtomicLong(System.currentTimeMillis());
		this.refreshRate = refreshRate;
	}

	public boolean rateLimit() {
		addCapacity();
		lastUpdatedTs.getAndSet(System.currentTimeMillis());
		if (currentCapacity.get() > 0) {
			currentCapacity.decrementAndGet();
			return true;
		}
		return false;
	}

	private void addCapacity() {
		Long currentTs = System.currentTimeMillis();
		int addCap = (int) (((currentTs - lastUpdatedTs.get()) / 1000) * refreshRate);
		currentCapacity.getAndSet(Math.min(currentCapacity.get() + addCap, capacity));
	}
}
