package me.azna.dataguru.jvm.week06;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HotswapClassLoader extends ClassLoader {
	private String basedir;
	private String className;

	public HotswapClassLoader(String basedir, String className) {
		super();
		this.basedir = basedir;
		this.className = className;
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
		StringBuffer sb = new StringBuffer(basedir);
		String classname = name.replace('.', File.separatorChar) + ".class";
		sb.append(File.separator + classname);
		File classF = new File(sb.toString());
		InputStream fin;
		byte[] raw = null;
		try {
			fin = new FileInputStream(classF);
			raw = new byte[(int) classF.length()];
			fin.read(raw);
			fin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return defineClass(name, raw, 0, raw.length);
	}

}
