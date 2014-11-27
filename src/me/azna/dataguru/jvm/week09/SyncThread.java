package me.azna.dataguru.jvm.week09;

public class SyncThread implements Runnable {
	private T3 t;

	public SyncThread(T3 t) {
		super();
		this.t = t;
	}

	@Override
	public void run() {
		t.initCount();
		int v = t.inc();
		while (v < T3.M) {
			v = t.inc();
		}
//		System.out.print("M:" + v + " ");
	}

}
