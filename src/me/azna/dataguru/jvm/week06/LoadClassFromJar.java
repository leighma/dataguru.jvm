package me.azna.dataguru.jvm.week06;

import java.net.URL;

public class LoadClassFromJar {

	public static void main(String[] args) throws Exception {
		String className = "me.azna.dataguru.jvm.week06.Worker";
		URL[] urls = new URL[] { new URL(
				"file:C:/Users/yulei.ma/Desktop/worker-1.0.1.jar") };

		MyJarClassLoader clJar = new MyJarClassLoader(urls, className);
		Class<?> c = clJar.loadClass(className);

		System.out.println(c);

	}

}
