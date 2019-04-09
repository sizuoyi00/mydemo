package com.jincou.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		System.out.println("======销毁监听器========");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("======进入监听器========");
		
	}

}