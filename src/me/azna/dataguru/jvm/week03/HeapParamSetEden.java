package me.azna.dataguru.jvm.week03;

public class HeapParamSetEden {

	public static void main(String[] args) {
		byte[] b = null;
		for(int i=0;i<10;i++){
			b=new byte[1*1024*1024];
		}

	}

}
