package com.zfw.testse;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest implements Runnable {

	@Override
	public void run() {
		System.out.println(1);
	}
	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
		scheduledExecutorService.schedule(new ScheduledExecutorTest(), 5, TimeUnit.SECONDS);
	}
}
