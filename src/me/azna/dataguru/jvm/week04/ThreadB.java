package me.azna.dataguru.jvm.week04;

import java.util.ArrayList;
import java.util.List;

public class ThreadB extends Thread {
	private List<byte[]> list = new ArrayList<byte[]>();

	@Override
	public void run() {
		while (true) {
			int n = 1000;
			for (int i = 0; i < n; i++)
				list.add(new byte[1024*1024]);
			if (list.size() >= n) {				
				System.out.println("prepare collect...,size = " + list.size());	
//				list.clear();
				for(int j =0;j<n/3;j++){
					list.remove(j);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.gc();
			}
			
		}

	}

}
