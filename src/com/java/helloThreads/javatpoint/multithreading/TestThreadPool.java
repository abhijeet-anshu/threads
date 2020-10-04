package com.java.helloThreads.javatpoint.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerThread implements Runnable
{

	private String msg;
	WorkerThread(String msg) {this.msg = msg;}

	@Override
	public void run()
	{
		Thread cThread = Thread.currentThread();
		String tName = cThread.getName();
		if(!tName.contains("Mohan"))
			cThread.setName("Mohan's thread " + msg);
		System.out.println("Hello, init from thread:"+cThread.getName() + ", msg=" + msg);
		processMsg();
		System.out.println("Hello, final end from thread:"+cThread.getName());
	}

	private void processMsg()
	{

		Thread cThread = Thread.currentThread();

		for(int i=0; i<4; i++)
		{
			try
			{
				cThread.sleep(500);
			} catch (InterruptedException e) {}
			System.out.println(cThread.getName() + " .... crunching...." + msg);

		}
		System.out.println(cThread.getName() + ".........my output till now ......."  + msg);
	}

}

public class TestThreadPool
{
	public static void main(String[] args)
	{
		ExecutorService pool = Executors.newFixedThreadPool(5);//creating a pool of 5 threads

		for(int i=0; i<20; i++)
		{
			pool.execute(new WorkerThread("--" + i+" ------"));
		}


		System.out.println("########Initiating pool shutdown ###########");
		pool.shutdown(); // thread implementation need to handle the interruption
		while(!pool.isTerminated())
		{

		}
		System.out.println("########Voila all finished ###########");

	}
}
