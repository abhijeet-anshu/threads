package com.java.helloThreads.javatpoint.Synchronization.interrupt;

public class TestInterruptedThread
{
	static void log(Exception e)
	{
		if(e==null){
			System.out.println("empty exception");
		}
		else
		{
			System.out.println("Exception:" + e + "\tMessage:" + e.getMessage() + "\tCause:" + e.getCause());
		}
	}

	public static void main(String[] args)
	{

		Runnable myTask = () -> {
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				log(e); System.out.println("been interrupted... return");
				return;
			}
		};


		Thread t = new Thread(myTask);
		t.start();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) { log(e);}
		t.interrupt();
	}
}
