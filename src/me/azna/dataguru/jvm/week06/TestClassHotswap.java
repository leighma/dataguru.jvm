package me.azna.dataguru.jvm.week06;

import java.lang.reflect.Method;

public class TestClassHotswap {

	public static void main(String[] args) throws Exception {
		String className = "me.azna.dataguru.jvm.week06.Worker";
		String methodName = "doit";
		while (true) {
			HotswapClassLoader cl = new HotswapClassLoader(Worker.class
					.getResource("/").getPath(), className);
			Class<?> cls = cl.loadClass(className);
			Object obj = cls.newInstance();
			Method method = obj.getClass()
					.getMethod(methodName, new Class[] {});
			method.invoke(obj, new Object[] {});

			Thread.sleep(2000);
		}

	}

}
