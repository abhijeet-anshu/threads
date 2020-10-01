package com.java.helloThreads.dummies;

class ClockDownCounter extends Thread
{
	private int timerLimit;
	ClockDownCounter(int timerLimit)
	{
		this.timerLimit = timerLimit;
	}
	@Override
	public void run()
	{
		for(int i=timerLimit; i>=0; i--)
		{
			System.out.println("T minus " + i);
			try
			{
				sleep(1000);
			} catch (InterruptedException e)
			{
				System.out.println("i was called for interruption");
				//interrupt();
			}
		}
	}
}
