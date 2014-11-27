package me.azna.dataguru.jvm.week03;

/**
 * 观察给定参数jvm运行情况 -Xms5M -Xmx20M
 * 
 * @author yulei.ma
 *
 */
public class HeapParam20141020_2 {
	private static void paramXmx() {
		System.out.print("Xmx=");
		System.out.println(getMSize(Runtime.getRuntime().maxMemory()) + "M");
	}

	private static void paramFreemem() {
		System.out.print("free mem=");
		System.out.println(getMSize(Runtime.getRuntime().freeMemory()) + "M");
	}

	private static void paramTotalmem() {
		System.out.print("total mem=");
		System.out.println(getMSize(Runtime.getRuntime().totalMemory()) + "M");
	}

	private static double getMSize(long bytes) {
		return bytes / 1024.0 / 1024;
	}

	private static void fpMemory(int a) {
		byte[] b = new byte[a * 1024 * 1024];
		System.out.println("分配了" + a + "M空间给数组");
	}
	private static void gc(){
		System.gc();
		System.out.println("回收内存");
	}

	public static void main(String[] args) {
		fpMemory(1);
		fpMemory(4);
		gc();
		paramXmx();
		paramFreemem();
		paramTotalmem();

	}

}
