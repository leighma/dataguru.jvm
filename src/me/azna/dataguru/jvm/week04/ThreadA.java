package me.azna.dataguru.jvm.week04;

/**
 * 验证GC产生的全局停顿，线程输出时间间隔
 * 
 * @author yulei.ma
 *
 */
public class ThreadA extends Thread {
	public long lastTime = System.currentTimeMillis();

	@Override
	public void run() {
		while (true) {	
			long currentTime = System.currentTimeMillis();
			long t = currentTime - lastTime;
			lastTime = currentTime;			
			System.out.println("time:" + t);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
