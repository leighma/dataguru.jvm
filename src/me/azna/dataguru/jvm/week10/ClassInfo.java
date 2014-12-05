package me.azna.dataguru.jvm.week10;

import me.azna.dataguru.jvm.week10.FieldsInfo.Field;
import me.azna.dataguru.jvm.week10.MethodsInfo.Method;

class ClassInfo {
	private int accessFlags;
	private int attributesCount;
	private ConstantPoolInfo constantPool;
	private FieldsInfo fields = new FieldsInfo();
	private int[] interfaces;
	private int magicNumber;
	private int majorVersion;
	private MethodsInfo methods = new MethodsInfo();
	private int minorVersion;
	private int superClass;
	private int thisClass;

	public int getAccessFlags() {
		return accessFlags;
	}

	public int getAttributesCount() {
		return attributesCount;
	}

	public ConstantPoolInfo getConstantPool() {
		return constantPool;
	}

	public int getConstantPoolCount() {
		return constantPool.getCount();
	}

	public Field[] getFields() {
		return fields.getFields();
	}

	public int getFieldsCount() {
		return fields.getFilesCount();
	}

	public FieldsInfo getFieldsInfo() {
		return fields;
	}

	public int[] getInterfaces() {
		return interfaces;
	}

	public int getInterfacesCount() {
		return interfaces.length;
	}

	public int getMagicNumber() {
		return magicNumber;
	}

	public int getMajorVersion() {
		return majorVersion;
	}

	public Method[] getMethods() {
		return methods.getMethods();
	}

	public int getMethodsCount() {
		return methods.getMethodsCount();
	}

	public MethodsInfo getMethodsInfo() {
		return this.methods;
	}

	public int getMinorVersion() {
		return minorVersion;
	}

	public int getSuperClass() {
		return superClass;
	}

	public int getThisClass() {
		return thisClass;
	}

	public void setAccessFlags(int accessFlags) {
		this.accessFlags = accessFlags;
	}

	public void setAttributesCount(int attributesCount) {
		this.attributesCount = attributesCount;
	}

	public void setConstantPool(ConstantPoolInfo constantPoolInfo) {
		this.constantPool = constantPoolInfo;
	}

	public void setFields(Field[] fields) {
		this.fields.setFields(fields);
	}

	public void setInterfaces(int[] interfaces) {
		this.interfaces = interfaces;
	}

	public void setMagicNumber(int magicNumber) {
		this.magicNumber = magicNumber;
	}

	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}

	public void setMethods(Method[] methods) {
		this.methods.setMethods(methods);
	}

	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

	public void setSuperClass(int superClass) {
		this.superClass = superClass;
	}

	public void setThisClass(int thisClass) {
		this.thisClass = thisClass;
	}

}
