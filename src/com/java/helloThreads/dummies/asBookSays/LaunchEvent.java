package com.java.helloThreads.dummies.asBookSays;

/**
 * This class launches an event after a given delay
 * It accepts the event as constructor parameter, and executes it after being initiated.
 */
public class LaunchEvent extends Thread
{
	static final int maxDelay = 20; //20s
	int myDelay;
	Runnable myTask;
	TimeMonitor clock;

	int start;
	private String message;
	TimeMonitor tm;

	public LaunchEvent(int start, String message,
										 TimeMonitor monitor)
	{
		this.start = start;
		this.message = message;
		this.tm = monitor;
	}


	@Override
	public void run()
	{
		boolean eventDone = false;
		synchronized (this)
		{
			while (tm.getTime() > start)
			{
				try
				{
					wait();
				} catch (InterruptedException e)
				{
				}
			}
		}

		System.out.println(this.message);
		//exit thread
	}

}
