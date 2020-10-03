package com.java.helloThreads.javatpoint.basics.common;

public class ThreadCommonTask
{
	public static void loop1to5()
	{
		System.out.println("---------Start..Thread Name:" + Thread.currentThread().getName() + "-------------");
		for (int i = 0; i < 5; i++)
		{
			System.out.println(i);
			try
			{
				Thread.sleep(500);
			} catch (InterruptedException e) {}
		}
		System.out.println("---------End...Thread:" + Thread.currentThread().getName() + "-------------");
	}
}
