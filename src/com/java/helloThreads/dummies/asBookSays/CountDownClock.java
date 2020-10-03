package com.java.helloThreads.dummies.asBookSays;

public class CountDownClock extends Thread
										        implements TimeMonitor
{
	private int t;
	public CountDownClock(int start)
	{
		this.t = start;
	}
	@Override
	public void run()
	{
		for (; t >= 0; t--)
		{
			System.out.println("T minus " + t);
			try
			{
				Thread.sleep(1000); // thread is processing som
			}
			catch (InterruptedException e)
			{}
		}
	}
	//broken
	@Override
	public int getTime()
	{
		return t;
	}

}
