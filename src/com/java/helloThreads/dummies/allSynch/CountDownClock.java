package com.java.helloThreads.dummies.allSynch;

import java.util.ArrayList;

public class CountDownClock extends Thread
										        implements TimeMonitor
{
	private int t;
	public CountDownClock(int start)
	{
		this.t = start;
//
	}
	@Override
	public void run()
	{
		for (; t >= 0; t--)
		{

			//need if condition for right thread
			//object.delay

			System.out.println("T minus " + t);

			synchronized (this)
			{
				notifyAll();
			}

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
