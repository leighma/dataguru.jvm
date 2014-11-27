package me.azna.dataguru.jvm.week02;

/**
 * 栈上分配对递归层次是否有影响呢？ -Xss128K -XX:+DoEscapeAnalysis -XX:+PrintGC
 * 
 * @author yulei.ma
 *
 */
public class OnStackRecursion {
	private int count = 0;

	public void recursion() {
		count++;
		int n = 512;
		byte[] b = new byte[n];
		for (int i = 0; i < n; i++) {
			b[i] = (byte) (i - Byte.MAX_VALUE);			
		}
		 recursion();
	}

	public static void main(String[] args) {
		OnStackRecursion osr = new OnStackRecursion();
		try {
			osr.recursion();
		} catch (Throwable e) {
			System.out.println("recursion count : " + osr.count);
			e.printStackTrace();
		}
	}

}
