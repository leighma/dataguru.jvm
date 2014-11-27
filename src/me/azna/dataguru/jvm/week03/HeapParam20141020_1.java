package me.azna.dataguru.jvm.week03;

/**
 * 观察给定参数jvm运行情况 -Xms5M -Xmx20M
 * 
 * @author yulei.ma
 *
 */
public class HeapParam20141020_1 {
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

	public static void main(String[] args) {
		paramXmx();
		paramFreemem();
		paramTotalmem();

	}

}
