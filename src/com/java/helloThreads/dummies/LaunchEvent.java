package com.java.helloThreads.dummies;

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

	/**
	 *
	 * @param reduceDelay in seconds
	 * @param myTask a task that runs on scheduled time
	 */
	LaunchEvent(int reduceDelay, Runnable myTask, TimeMonitor clock)
	{
		myDelay = (maxDelay - reduceDelay)*1000;
		this.myTask = myTask;
		this.clock = clock;
	}

	@Override
	public void run()
	{
		try {
			Thread.sleep(myDelay);
		} catch (InterruptedException e) {
			return; //can't launch before mentioned time. log and exit
		}
		while(clock.getTime()>maxDelay-myDelay)
			try{
				//broken
				Thread.sleep(500);
				System.out.println("time fetched:"+ clock.getTime());
			}catch (InterruptedException e) {
				return; //can't launch before mentioned time. log and exit
			}
		new Thread(myTask).start();
	}

}
