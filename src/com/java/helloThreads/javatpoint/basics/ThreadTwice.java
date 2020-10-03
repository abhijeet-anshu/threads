package com.java.helloThreads.javatpoint.basics;

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
