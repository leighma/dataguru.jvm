package me.azna.dataguru.jvm.week09;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class T3 {
	static int M = 1000000000;
	private int count = 1;
	void initCount(){
		count = 1;
	}

	synchronized int inc() {
		return count++;
	}

	public static void main(String[] args) throws InterruptedException {
		int[] N = { 3, 30, 300, 1000 };
		SyncThread threadS = new SyncThread(new T3());
		for (int i : N) {			
			run(i, threadS);
			Thread.sleep(5000);
		}

		AtomicThread threadA = new AtomicThread(new AtomicInteger(1));
		for (int i : N) {
			run(i, threadA);
			Thread.sleep(5000);
		}

	}

	public static void run(int contThreads, Runnable thread) {
		ExecutorService threads = Executors.newFixedThreadPool(contThreads);
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < contThreads; i++) {
			threads.submit(thread);
		}
		long spend = System.currentTimeMillis() - startTime;
		System.out.println("N:" + contThreads + " spend:" + spend);
	}

}
