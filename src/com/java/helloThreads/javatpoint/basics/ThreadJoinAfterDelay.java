package com.java.helloThreads.javatpoint.basics;

import com.java.helloThreads.javatpoint.basics.common.ThreadCommonTask;

public class ThreadJoinAfterDelay
{
	public static void main(String[] args)
	{
		Runnable commonTask = () -> ThreadCommonTask.loop1to5();

		Thread t1 = new Thread(commonTask);
		Thread t2 = new Thread(commonTask);
		Thread t3 = new Thread(commonTask);

		t1.start();
		try  {
			t1.join(1000);
		}
		catch (InterruptedException e) {}

		t2.start();
		t3.start();

	}
}
