package me.azna.dataguru.jvm.week03;
/**
 * -XX:+TraceClassLoading
 * @author yulei.ma
 *
 */
public class TraceClassLoad1 {

	public static void main(String[] args) {
		System.out.println("Class Load Trace");
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
