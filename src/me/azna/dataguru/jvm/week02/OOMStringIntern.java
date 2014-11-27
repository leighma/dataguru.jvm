package me.azna.dataguru.jvm.week02;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用String的Intern方法造成常量池溢出
 * 
 * @author yulei.ma
 *
 */
public class OOMStringIntern {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}
	}

}
