package com.java.helloThreads.javatpoint.multithreading;

import com.java.helloThreads.javatpoint.multithreading.common.ThreadCommonTask;

public class TestCallRun2
{

	public static void main(String[] args)
	{
		Thread t1 = new Thread(() -> ThreadCommonTask.loop1to5());
		Thread t2 = new Thread(() -> ThreadCommonTask.loop1to5());
		t1.run();
		t2.run();

		t1.start(); //inits new thread
		t2.start(); //inits new thread execution
		System.out.println("---------End...Main Thread");
	}
}
