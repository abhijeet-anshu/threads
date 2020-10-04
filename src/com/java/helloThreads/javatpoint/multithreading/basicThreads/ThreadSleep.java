package com.java.helloThreads.javatpoint.multithreading.basicThreads;

public class ThreadSleep extends Thread
{
	@Override
	public void run()
	{
		for(int i=0; i<5; i++)
		{
			System.out.println(i);
			try
			{
				sleep(500);
			} catch (InterruptedException e) {}
		}
	}


	public static void main(String[] args)
	{
		new ThreadSleep().start();
		new ThreadSleep().start();
		new ThreadSleep().start();
	}

}
