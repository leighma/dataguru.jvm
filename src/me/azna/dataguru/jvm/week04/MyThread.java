package me.azna.dataguru.jvm.week04;

import java.util.HashMap;

public class MyThread extends Thread {
	HashMap<Long, byte[]> map = new HashMap<Long, byte[]>();

	@Override
	public void run(){
		try{
			while(true){
				if(map.size()*512/1024/1024>=450){
					System.out.println("=====准备清理=====:"+map.size());
					map.clear();
				}
				
				for(int i=0;i<1024;i++){
					map.put(System.nanoTime(), new byte[512]);
				}
				Thread.sleep(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
