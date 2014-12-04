package me.azna.dataguru.jvm.week10;

class ClassGeneralInfo {
	private int minorVersion;
	private int majorVersion;
	private int constantPoolCount;
	private int accessFlags;
	private int thisClass;
	private int superClass;
	private int interfacesCount;
	private int fieldsCount;
	private int methodsCount;
	private int attributesCount;

	public int getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

	public int getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}

	public int getConstantPoolCount() {
		return constantPoolCount;
	}

	public void setConstantPoolCount(int constantPoolCount) {
		this.constantPoolCount = constantPoolCount;
	}

	public int getAccessFlags() {
		return accessFlags;
	}

	public void setAccessFlags(int accessFlags) {
		this.accessFlags = accessFlags;
	}

	public int getThisClass() {
		return thisClass;
	}

	public void setThisClass(int thisClass) {
		this.thisClass = thisClass;
	}

	public int getSuperClass() {
		return superClass;
	}

	public void setSuperClass(int superClass) {
		this.superClass = superClass;
	}

	public int getInterfacesCount() {
		return interfacesCount;
	}

	public void setInterfacesCount(int interfacesCount) {
		this.interfacesCount = interfacesCount;
	}

	public int getFieldsCount() {
		return fieldsCount;
	}

	public void setFieldsCount(int fieldsCount) {
		this.fieldsCount = fieldsCount;
	}

	public int getMethodsCount() {
		return methodsCount;
	}

	public void setMethodsCount(int methodsCount) {
		this.methodsCount = methodsCount;
	}

	public int getAttributesCount() {
		return attributesCount;
	}

	public void setAttributesCount(int attributesCount) {
		this.attributesCount = attributesCount;
	}

}
