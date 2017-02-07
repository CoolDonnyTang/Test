package com.tyr.note.test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBean {
	protected static AbstractApplicationContext aac;
	static {
		aac = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
}
