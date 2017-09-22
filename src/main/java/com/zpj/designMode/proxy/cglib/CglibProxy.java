package com.zpj.designMode.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/***
 * @author Perkins Zhu
 * @date 2017年3月13日 上午9:02:54
 */
public class CglibProxy implements MethodInterceptor {
	private Object targetObject;

	// 这里的目标类型为Object，则可以接受任意一种参数作为被代理类，实现了动态代理
	public Object getInstance(Object target) {
		this.targetObject = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(this);
		//注意该处代理的创建过程
		Object proxyObj = enhancer.create();
		return proxyObj;// 返回代理对象
	}

	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		Object obj = null;
		System.out.println("doSomething---------start");
		obj = method.invoke(targetObject, args);
		System.out.println("doSomething---------end");
		return obj;
	}

}
