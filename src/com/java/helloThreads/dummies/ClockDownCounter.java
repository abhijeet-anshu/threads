package com.java.helloThreads.dummies;

class ClockDownCounter extends Thread
				               implements TimeMonitor
{
	private int timerLimit;
	private int currentTime;
	/**
	 * @param timerLimit in Seconds
	 */
	ClockDownCounter(int timerLimit)
	{
		this.timerLimit = timerLimit;
		currentTime = timerLimit;
	}
	@Override
	public void run()
	{
		for(int currentTime=timerLimit; currentTime>=0; currentTime--)
		{
			System.out.println("T minus " + currentTime);
			try
			{
				sleep(1000);
			} catch (InterruptedException e)
			{
				System.out.println("counter interrupted");
			}
		}
	}
	@Override
	public int getTime()
	{
		return currentTime;
	}

}
