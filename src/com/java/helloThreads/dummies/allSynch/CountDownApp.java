package com.java.helloThreads.dummies.allSynch;

import java.util.ArrayList;

public class CountDownApp
{
	public static void main(String[] args)
	{
		CountDownClock clock = new CountDownClock(20);
		ArrayList<LaunchEvent> events = new ArrayList<LaunchEvent>();
		events.add(new LaunchEvent(16, "Flood the pad!", clock));

		events.add(new LaunchEvent(10, "Start engines!", clock));

		events.add(new LaunchEvent(0, "Liftoff!", clock));

		clock.start();

		for (LaunchEvent e : events)
			e.start();
	}
}
