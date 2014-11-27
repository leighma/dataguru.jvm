package me.azna.dataguru.jvm.week06;

import java.lang.reflect.Method;

public class T1 {

	public static void main(String[] args) throws Exception {
		while (true) {
			HotswapClassLoaderOld cl = new HotswapClassLoaderOld(Foo.class
					.getResource("/").getPath(),
					new String[] { "me.azna.dataguru.jvm.week06.Foo" });
			Class<?> cls = cl.loadClass("me.azna.dataguru.jvm.week06.Foo");
			Object foo = cls.newInstance();
			Method m = foo.getClass().getMethod("sayHello", new Class[] {});
			m.invoke(foo, new Object[] {});
			Thread.sleep(2000);
		}

	}

}
