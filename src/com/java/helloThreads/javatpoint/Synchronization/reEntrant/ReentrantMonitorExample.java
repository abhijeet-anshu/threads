package com.java.helloThreads.javatpoint.Synchronization.reEntrant;

public class ReentrantMonitorExample
{
	public static void main(String[] args)
	{

		Runnable r = () ->
						new Object()
						{
							public synchronized void m()
							{
								n(); //m already holds monitor of this class, but now invokes for n.
								System.out.println("i do some wrap up");
							}

							public synchronized void n()
							{
								System.out.println("i do some core work");
							}
						}.m();

		new Thread(r).start();
	}

}
