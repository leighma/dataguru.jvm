package me.azna.dataguru.jvm.week09;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicThread implements Runnable {
	private AtomicInteger acount;
	

	public AtomicThread(AtomicInteger acount) {
		super();
		this.acount = acount;
	}


	@Override
	public void run() {
		int v = acount.incrementAndGet();
		while (v < T3.M) {
			v = acount.incrementAndGet();
		}
//		System.out.print("M:" + v +" ");

	}

}
