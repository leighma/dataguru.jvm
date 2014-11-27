package me.azna.dataguru.jvm.week02;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class OOMMethodArea {

	public static void main(String[] args) {
		while (true) {
			Enhancer enh = new Enhancer();
			enh.setSuperclass(OOM.class);
			enh.setUseCache(false);
			enh.setCallback(new MethodInterceptor() {
				@Override
				public Object intercept(Object arg0, Method arg1,
						Object[] arg2, MethodProxy arg3) throws Throwable {
					return arg3.invokeSuper(arg0, arg2);
				}
			});
			enh.create();
		}
	}

}
