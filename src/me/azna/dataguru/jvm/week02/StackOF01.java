package me.azna.dataguru.jvm.week02;

public class StackOF01 {
	private int count = 0;

	public void recursion() {
		count++;
		recursion();
	}

	public static void main(String[] args) {
		StackOF01 sof = new StackOF01();
		try {
			sof.recursion();
		} catch (Throwable e) {
			System.out.println("recursion count : " + sof.count);
			e.printStackTrace();
		}
	}

}
