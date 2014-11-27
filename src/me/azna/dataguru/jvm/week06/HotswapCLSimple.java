package me.azna.dataguru.jvm.week06;

public class HotswapCLSimple extends ClassLoader {

	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = null; // 查找或生成Java类的字节代码
		return defineClass(name, b, 0, b.length);
	}

}
