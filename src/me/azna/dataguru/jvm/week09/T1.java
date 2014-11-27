package me.azna.dataguru.jvm.week09;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class T1 {
	private static int MAX_THREADS = 3;	
	private int count = 0;
	private int TARGET_COUNT = 1000000;

	private synchronized int inc() {
		return ++count;
	}

	
	public class SyncThread implements Runnable {
		protected String name;
		protected long startTime;
		T1 out;

		public SyncThread(T1 out, long startTime) {
			this.out = out;
			this.startTime = startTime;
		}

		@Override
		public void run() {
			int v = out.inc();
			while (v < TARGET_COUNT) {
				v = out.inc();

			}

			System.out.println("spend:"
					+ (System.currentTimeMillis() - startTime));
			System.out.println("v=" + v);

		}

	}

	private AtomicInteger acount = new AtomicInteger(0);

	public class AtomicThread implements Runnable {
		protected String name;
		protected long startTime;

		public AtomicThread(long startTime) {
			super();
			this.startTime = startTime;
		}

		@Override
		public void run() {
			int v = acount.incrementAndGet();
			while (v < TARGET_COUNT) {
				v = acount.incrementAndGet();
			}
			System.out.println("spend:"
					+ (System.currentTimeMillis() - startTime));
			System.out.println("v=" + v);

		}

	}

	public void testAtomic() throws InterruptedException {
		ExecutorService threads = Executors.newFixedThreadPool(MAX_THREADS);
		long startTime = System.currentTimeMillis();
		AtomicThread atomic = new AtomicThread(startTime);
		for (int i = 0; i < MAX_THREADS; i++) {
			threads.submit(atomic);

		}
		Thread.sleep(10000);		
	}

	public void testSync() throws InterruptedException {
		ExecutorService threads = Executors.newFixedThreadPool(MAX_THREADS);
		long startTime = System.currentTimeMillis();
		SyncThread sync = new SyncThread(this, startTime);
		for (int i = 0; i < MAX_THREADS; i++) {
			threads.submit(sync);
		}
		Thread.sleep(10000);
	}
	
	public void run(int contThreads,Runnable thread){
		ExecutorService threads = Executors.newFixedThreadPool(contThreads);
		for (int i = 0; i < contThreads; i++) {
			threads.submit(thread);
		}
	}

}
