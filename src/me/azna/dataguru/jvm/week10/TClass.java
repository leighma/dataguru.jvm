package me.azna.dataguru.jvm.week10;

public class TClass extends SuperClass implements IInterface,IInterface2 {
	private boolean flag = false;
	public static final String CONSTANTS = "constants";

	@Override
	public int iMethod(int a) {
		return a + 1;
	}

	boolean isFlag() {
		return flag;
	}

	void setFlag(boolean flag) {
		this.flag = flag;
	}

	static String getConstants() {
		return CONSTANTS;
	}

	@Override
	public void iiMethod() {
		System.out.println("iiM");
		
	}

}
