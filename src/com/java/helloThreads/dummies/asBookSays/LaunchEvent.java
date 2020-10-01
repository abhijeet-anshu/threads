package com.java.helloThreads.dummies.asBookSays;

/**
 * This class launches an event after a given delay
 * It accepts the event as constructor parameter, and executes it after being initiated.
 */
public class LaunchEvent implements Runnable
{
	static final int maxDelay = 20; //20s
	int myDelay;
	Runnable myTask;
	TimeMonitor clock;

	private int start;
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
		while (!eventDone)
		{
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e) {}
		}
		if (tm.getTime() <= start)
		{
			System.out.println(this.message);
			eventDone = true;
		}
	}

}
