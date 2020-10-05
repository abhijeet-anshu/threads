package com.java.helloThreads.javatpoint.Synchronization.Deadlock;

public class TestDeadlock
{
	static void lSleep(long milli){
		try {Thread.sleep(milli); }
		catch (InterruptedException e) {}
	}

	static Runnable task(Object resource1, Object resource2, long sleepTime)
	{
		return () -> {
			synchronized (resource1)
			{
				System.out.println("aquired resource:" + resource1);
				lSleep(sleepTime);
				System.out.println("waiting for " + resource2);
				synchronized (resource2) //poor implementation.. no room for interruption
				{
					System.out.println("aquired resource:" + resource2);
					System.out.println("..............doing my work");
				}
			}
		};
	}

	public static void main(String[] args)
	{
		final String resource1 = "abhijeet";
		final String resource2 = "baranwal";

		new Thread(task(resource1, resource2, 1000)).start();
		new Thread(task(resource2, resource1, 1000)).start();

	}
}
