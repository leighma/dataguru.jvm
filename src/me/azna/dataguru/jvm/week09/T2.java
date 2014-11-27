package me.azna.dataguru.jvm.week09;

public class T2 {
	private int count = 1;
	private int TARGET_COUNT = 1000000;

	public static void main(String[] args) {
		T2 o = new T2();
		long startTime = System.currentTimeMillis();
		o.singleThread();
		long spend = System.currentTimeMillis() - startTime;
		System.out.println("spend:" + spend);

	}

	public void singleThread() {
		while (count < TARGET_COUNT) {
			count++;
		}

	}

}
