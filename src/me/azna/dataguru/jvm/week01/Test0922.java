package me.azna.dataguru.jvm.week01;

public class Test0922 {

	public static void main(String[] args) {
		float f = 100.2f;
//		System.out.println(Float.toHexString(f));
		
		System.out.println(Integer.toBinaryString(Float.floatToIntBits(f)));
		int a = Float.floatToIntBits(f);
		a = - 99;
		for(int i=0;i<32;i++){
			int t=(a & 0x80000000>>>i)>>>(31-i);
			System.out.print(t);
		}

		System.out.println();
		
		
		
	}

}
