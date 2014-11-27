package me.azna.dataguru.jvm.week07;

public class DThread implements Runnable {
	private Obj self;
	private Obj forward;

	public DThread(Obj self, Obj forward) {
		super();
		this.self = self;
		this.forward = forward;
	}

	@Override
	public void run() {
		synchronized (self) {
			System.out.println("i'm " + self.getName());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (forward) {
				System.out.println(self.getName() + " forward "
						+ forward.getName());
			}
		}

	}

}
