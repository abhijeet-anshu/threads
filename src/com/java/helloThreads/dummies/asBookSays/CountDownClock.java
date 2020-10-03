package com.java.helloThreads.dummies.asBookSays;

import java.util.ArrayList;

public class CountDownClock extends Thread
										        implements TimeMonitor, Observable
{
	private int t;
	int observerIndex;
	ArrayList<LaunchEvent> observers;
	public CountDownClock(int start)
	{
		this.t = start;
		observers = new ArrayList();
		observerIndex = 0;
	}
	@Override
	public void run()
	{
		for (; t >= 0; t--)
		{
			//need if condition for right thread
			//object.delay

			System.out.println("T minus " + t);

			if(observers.get(observerIndex).start==t)
				notifyObserver();

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

	@Override
	public void addObserver(LaunchEvent event)
	{
		observers.add(event);
	}

	@Override
	public void notifyObserver()
	{
		synchronized(observers.get(observerIndex))
		{
			observers.get(observerIndex++).notify();
		}
	}

}
