package me.azna.dataguru.jvm.week02;

public class StackOF02 {
	private int count = 0;

	public void recursion(Long l1, Long l2, Long l3) {
		count++;
		recursion(l1, l2, l3);
	}

	public static void main(String[] args) {
		StackOF02 sof = new StackOF02();
		try {
			sof.recursion(2L, 3L, 9L);
		} catch (Throwable e) {
			System.out.println("recursion count : " + sof.count);
			e.printStackTrace();
		}
	}

}
