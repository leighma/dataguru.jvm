package me.azna.dataguru.jvm.week06;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import sun.misc.Resource;
import sun.misc.URLClassPath;

public class MyJarClassLoader extends URLClassLoader {
	private String className;
	URLClassPath ucp;

	public MyJarClassLoader(URL[] urls, String className) {
		super(urls);
		this.className = className;
		ucp = new URLClassPath(urls);

	}

	@Override
	protected synchronized Class<?> loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		Class<?> cls = null;
		cls = findLoadedClass(name);
		if (cls == null && name.equals(className)) {
			cls = findClass(name);
		} else {
			cls = getSystemClassLoader().loadClass(name);
		}
		if (cls == null)
			throw new ClassNotFoundException(name);
		return cls;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String path = name.replace('.', '/').concat(".class");
		Resource res = ucp.getResource(path, false);
		byte[] raw = null;
		try {
			raw = res.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return defineClass(name, raw, 0, raw.length);

	}

}
