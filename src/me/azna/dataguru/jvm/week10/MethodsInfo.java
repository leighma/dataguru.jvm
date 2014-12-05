package me.azna.dataguru.jvm.week10;

public class MethodsInfo {
	private Method[] methods;

	public int getMethodsCount() {
		return methods.length;
	}

	public Method[] getMethods() {
		return methods;
	}

	public void setMethods(Method[] methods) {
		this.methods = methods;
	}

	class Method {
		int accessFlag;
		int name;
		int descriptor;
		int[] attributes;
	}

}
