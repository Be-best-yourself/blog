package com.zfw.runnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorTest {

	static ExecutorService newFixedThreadPool=Executors.newFixedThreadPool(100);
	public static void main(String[] args) {
	}
	public static void doUpLoad(Runnable runnable){
		newFixedThreadPool.execute(runnable);
	}
}
