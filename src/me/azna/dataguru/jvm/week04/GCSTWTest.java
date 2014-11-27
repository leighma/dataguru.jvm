package me.azna.dataguru.jvm.week04;

public class GCSTWTest {

	public static void main(String[] args) {
		ThreadA threadA = new ThreadA();
		threadA.start();
		ThreadB threadB = new ThreadB();
		threadB.start();
	}

}
