package com.java.helloThreads.javatpoint.basics;

public class TestCallRun
{
	public static void main(String[] args)
	{
		System.out.println("thread name"+ Thread.currentThread().getName());
		Thread t = new Thread( () -> System.out.println("running: " + "thread name"+ Thread.currentThread().getName()));
		t.run(); //invoked in the same instance
		t.run();
		t.run(); //no exception
		t.start(); //no exception

		Thread t2 = new Thread( () -> System.out.println("am new object, am running " + "thread name"+ Thread.currentThread().getName()));
		t2.run();

	}



}
