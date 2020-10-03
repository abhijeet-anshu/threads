package com.java.helloThreads.dummies.asBookSays;

public interface Observable
{
	void addObserver(LaunchEvent event);
	void notifyObserver();
}
