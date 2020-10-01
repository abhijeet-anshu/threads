package com.java.helloThreads.dummies;

class ClockDownCounter extends Thread
{
	private int timerLimit;

	/**
	 * @param timerLimit in Seconds
	 */
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
				System.out.println("counter interrupted");
			}
		}
	}
}
