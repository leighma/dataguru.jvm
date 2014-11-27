package me.azna.dataguru.jvm.week07;

public class MainTestDeadThread {

	public static void main(String[] args) {
		Obj a = new Obj("A");
		Obj b = new Obj("B");
		Obj c = new Obj("C");
		Obj d = new Obj("D");
		run(a, b);
		run(b, c);
		run(c, d);
		run(d, a);

	}

	public static void run(Obj objSelf, Obj objForward) {
		new Thread(new DThread(objSelf, objForward)).start();
	}

}
