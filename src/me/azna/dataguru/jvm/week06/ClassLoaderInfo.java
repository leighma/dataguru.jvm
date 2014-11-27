package me.azna.dataguru.jvm.week06;

public class ClassLoaderInfo {

	public static void main(String[] args) {
		ClassLoader cl = ClassLoaderInfo.class.getClassLoader();
		System.out.println(cl);
		ClassLoader clParent = cl.getParent();
		System.out.println(clParent);
		ClassLoader clParentParent = clParent.getParent();
		System.out.println(clParentParent);
		

	}

}
