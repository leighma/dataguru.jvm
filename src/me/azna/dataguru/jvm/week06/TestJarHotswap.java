package me.azna.dataguru.jvm.week06;

import java.lang.reflect.Method;
import java.net.URL;

public class TestJarHotswap {

	public static void main(String[] args) throws Exception {
		String jarFilePath = "C:/Users/yulei.ma/Desktop/worker-1.0.1.jar";
		String className = "me.azna.dataguru.jvm.week06.Worker";
		String methodName = "doit";
		while (true) {
			URL[] urls = new URL[] { new URL("file:" + jarFilePath) };

			MyJarClassLoader clJar = new MyJarClassLoader(urls, className);
			Class<?> cls = clJar.loadClass(className);
			Object obj = cls.newInstance();
			Method method = obj.getClass()
					.getMethod(methodName, new Class[] {});
			method.invoke(obj, new Object[] {});
			Thread.sleep(2000);
		}

	}

}
