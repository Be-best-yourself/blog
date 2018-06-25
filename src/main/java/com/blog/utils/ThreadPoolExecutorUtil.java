package com.blog.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorUtil {
	static ExecutorService newFixedThreadPool=Executors.newFixedThreadPool(100);
	public static void doUpLoad(Runnable runnable){
		newFixedThreadPool.execute(runnable);
	}
}
