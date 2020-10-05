package com.java.helloThreads.javatpoint.Synchronization.Locks.ReEntrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class LockPlay extends Thread
{
	final ReentrantLock lock;
	final String threadName;
	LockPlay(ReentrantLock lock, String threadName)
	{
		this.lock = lock;
		this.threadName = threadName;
	}

	private void lockedWork()
	{
		System.out.println("@" + this.threadName + "> I have the lock now... let me do the imp stuff... ");
		try {Thread.sleep(400); } catch (InterruptedException e) {
			System.out.println("exception.." + e);
		}
		System.out.println("@" + this.threadName + "> I still have the lock now... just finishing... ");
		if(!lock.hasQueuedThreads())
		{
			do
			{

				System.out.println("@" + this.threadName + "> its my lucky day. no waiting on lock..i will crunch more work");
				try
				{
					Thread.sleep(400);
				} catch (InterruptedException e)
				{
					System.out.println("exception.." + e);
				}

			} while (!lock.hasQueuedThreads());
		}
		else
			System.out.println("@" + this.threadName + "> oops there are threads waiting... I had rather finish ");
	}

	@Override
	public void run()
	{
		Thread.currentThread().setName(this.threadName);
		System.out.println("@" + this.threadName +  "> hey am trying to get the lock.....");
		lock.lock();
		try
		{
				lockedWork();
		}
		finally
		{
			lock.unlock();
		}
	}

	public static void main(String[] args)
	{
		ReentrantLock lock = new ReentrantLock();
		Thread manoj = new LockPlay(lock, "Manoj");
		Thread anuj = new LockPlay(lock, "Anuj");
		Thread ram = new LockPlay(lock, "Ram");
		Thread shyam = new LockPlay(lock, "Shyam");
		Thread ranjit = new LockPlay(lock, "Ranjit");
		anuj.start();
		manoj.start();
		ram.start();
		ranjit.start();
		shyam.start();
	}

}
