package com.java.helloThreads.dummies;

import java.util.Random;

public class CountDownApp
{
	public static void main(String[] args)
	{
		ClockDownCounter timer = new ClockDownCounter(20);
		timer.start();
	}
}
