package me.azna.dataguru.jvm.week04;

public class Test {

	public static void main(String[] args) {
		PrintThread printThread = new PrintThread();
		MyThread myThread = new MyThread();
		printThread.start();
		myThread.start();
	}

}
