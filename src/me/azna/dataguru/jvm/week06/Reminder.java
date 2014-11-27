package me.azna.dataguru.jvm.week06;

import java.util.Timer;
import java.util.TimerTask;

public class Reminder {
	Timer timer;

	public Reminder(int seconds) {
		timer = new Timer();
		timer.schedule(new RemindTask(), seconds * 1000);
	}

	class RemindTask extends TimerTask {
		public void run() {
			// 每次都创建出一个新的类加载器

			try {
				System.out.println("Time's up!");
				timer.cancel();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String args[]) {
		new Reminder(2);
	}
}
