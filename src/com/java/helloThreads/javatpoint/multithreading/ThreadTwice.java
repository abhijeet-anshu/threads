package com.java.helloThreads.javatpoint.multithreading;

public class ThreadTwice
{
	public static void main(String[] args)
	{
		Thread t = new Thread( () -> {
			System.out.println("hey running now");
		});

		t.start();
		t.start(); //throws exception

	}
}
