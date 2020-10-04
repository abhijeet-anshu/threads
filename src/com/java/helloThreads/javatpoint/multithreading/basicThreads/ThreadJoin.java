package com.java.helloThreads.javatpoint.multithreading.basicThreads;

import com.java.helloThreads.javatpoint.multithreading.common.ThreadCommonTask;

public class ThreadJoin
{




	public static void main(String[] args)
	{
		Runnable commonTask = () -> ThreadCommonTask.loop1to5();
		Thread t1 = new Thread(commonTask);
		Thread t2 = new Thread(commonTask);
		Thread t3 = new Thread(commonTask);

		t1.start();

		try{
			t1.join();
		}
		catch (InterruptedException e) {}

		t2.start(); // this will be only invoked once
		t3.start();


	}
}
