package service.impl;

import service.itf.HelloWorldItf;

public class HelloWorldImpl implements HelloWorldItf{

	@Override
	public String sayHello(String hello) {
		return "hello " + hello.toUpperCase() + "!";
	}

}
