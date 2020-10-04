package com.java.helloThreads.javatpoint.multithreading.basicThreads;

public class TestDaemonThread
{
	public static void main(String[] args)
	{
		Runnable task = () -> {
			Thread t = Thread.currentThread();
			System.out.println("Thread name:" + t.getName() + " is daemon: " + t.isDaemon());
			try {
				t.sleep(1000);
			}
			catch (InterruptedException e) {}
			if(t.isDaemon())
				try {
					t.sleep(5000);
				}
				catch (InterruptedException e) {}
		};

		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);
		Thread t3 = new Thread(task);

		t1.setDaemon(true);

		t1.start();

		t2.start();


		t3.start(); //already running thread

		//t3.setDaemon(true); //throw an exception if a running thread is marked daemon

		while (t2.isAlive())
		{
			try {
				Thread.sleep(5000);
			}
			catch (InterruptedException e) {}
		}

		System.out.println(t2.getName() + " alive: " + t2.isAlive());
		//if(!t2.isAlive()) t2.start(); still throws an exception. A thread can not be restarted EVER

	}
}
