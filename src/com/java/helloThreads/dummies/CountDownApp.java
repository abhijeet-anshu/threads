package com.java.helloThreads.dummies;

import java.util.Random;

public class CountDownApp
{
	static Runnable getTask(String msg)
	{
		return () -> System.out.println(msg);
	}
	public static void main(String[] args)
	{

		//initialize my launch tasks
		Runnable floodLight = new LaunchEvent(16,  getTask("begin the flood light"));
		Runnable ignition =   new LaunchEvent(6,   getTask("start the enginess!!"));
		Runnable liftOff =    new LaunchEvent(0,   getTask("|| lift Off ||"));

		new ClockDownCounter(LaunchEvent.maxDelay).start();
		new Thread(floodLight).start();
		new Thread(ignition).start();
		new Thread(liftOff).start();

	}
}
