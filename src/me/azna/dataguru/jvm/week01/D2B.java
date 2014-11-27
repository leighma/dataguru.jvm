package me.azna.dataguru.jvm.week01;

/**
 * 十进制转二进制 整数部分，除2、模运算取余数，一直到商为0 小数部分 符号部分
 * 负数部分
 * @author yulei.ma
 * 
 */
public class D2B {

	/**
	 * 整数二进制
	 * 
	 * @param zh
	 * @return
	 */
	public static int[] zh(int zh) {
		int[] binary = new int[32];
		int i = 0;
		while (zh > 0) {
			binary[31 - i] = zh % 2;
			zh = zh / 2;
			i++;
		}
		return binary;
	}

	/**
	 * 小数二进制
	 * @param xi
	 * @return
	 */
	public static int[] xi(float xi) {
		int[] binary = new int[32];
		int i = 0;
		float a = 0;
		while (xi > 0) {
			a = xi * 2;
			binary[i] = (int) a;
			xi = a - (int) a;
			i++;
		}
		return binary;
	}

	public static void show(int[] binary) {
		for (int i = 0; i < binary.length; i++) {
			if (i != 0 && i % 4 == 0 && i != 31) {
				System.out.print(" ");
			}
			System.out.print(binary[i]);
		}
	}

	public static void main(String[] args) {
//		D2B.show(D2B.xi(0.75f));	
//		int a = 0b11;
		float f = 2e+1f;
		double d = 22e3;
		System.out.println(d);
		

	}

}
