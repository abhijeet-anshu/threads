package com.java.helloThreads.javatpoint.multithreading;

/*
public static int MIN_PRIORITY 1
public static int NORM_PRIORITY 5
public static int MAX_PRIORITY 10

 */

public class TestThreadPriority
{
	public static void main(String[] args)
	{
		Runnable task = () -> {
			Thread t = Thread.currentThread();
			System.out.println("Thread:" + t.getName() + " Priority:" + t.getPriority());
		};

		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);

		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.MAX_PRIORITY);

		t1.start();
		t2.start();



	}
}
