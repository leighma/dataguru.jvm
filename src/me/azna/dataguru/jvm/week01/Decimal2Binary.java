package me.azna.dataguru.jvm.week01;

/**
 * 十进制转二进制
 * 
 * <p>
 * 未表示符号，只支持正数
 * 
 * @author yulei.ma
 * 
 */
public class Decimal2Binary {

	/**
	 * 整数部分转二进制
	 * <p>
	 * 除2取余，商继续除，除到商为0
	 * <p>
	 * 结果最多32位
	 * 
	 * @param integer
	 * @return
	 */
	public static int[] integer2Binary(int integer) {
		int[] binary = new int[32];
		int i = 0;
		while (integer > 0 && i >= 0) {
			binary[31 - i] = integer % 2;
			integer = integer / 2;
			i++;
		}
		return binary;
	}

	/**
	 * 小数部分转二进制
	 * <p>
	 * 乘2取整，取整数部分，小数部分继续乘
	 * <p>
	 * 结果最多32位
	 * 
	 * @param decimal
	 * @return
	 */
	public static int[] decimal2Binary(float decimal) {
		int[] binary = new int[32];
		int i = 0;
		float a = 0;
		while (decimal > 0 && i < 31) {
			a = decimal * 2;
			binary[i] = (int) a;
			decimal = a - (int) a;
			i++;
		}
		return binary;
	}

	public static void show(int[] binary) {
		for (int i = 0; i < binary.length; i++) {
//			if (i != 0 && i % 4 == 0 && i != 31) {
//				System.out.print(" ");
//			}
			System.out.print(binary[i]);
		}
	}

	public static void main(String[] args) {
		int in = 100;//整数部分
		float de = 0.2f;//小数部分
		Decimal2Binary.show(Decimal2Binary.integer2Binary(in));
		System.out.print(".");
		Decimal2Binary.show(Decimal2Binary.decimal2Binary(de));
	}

}
