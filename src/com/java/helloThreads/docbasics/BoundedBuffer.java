package com.java.helloThreads.docbasics;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Condition.html
 */
public class BoundedBuffer
{
	private final Lock lock = new ReentrantLock();
	private final Condition notFull  = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();

	private int startPointer;
	private int endPointer;
	private int count;

	private int[] _Array;


	BoundedBuffer(int capacity)
	{
		if(capacity==0) throw new IllegalArgumentException();

		_Array = new int[capacity];
		startPointer = 0;
		endPointer = 0;
		count = 0;
	}

	int get() throws InterruptedException
	{
		lock.lock();
		try
		{
			while(count==0)
			 notEmpty.await();

			int value = _Array[startPointer];
			count--;
			if(++startPointer == _Array.length)
				startPointer = 0;


			notFull.signalAll();
			return value;

		}
		finally
		{
			lock.unlock();
		}

	}

	void put(int value) throws InterruptedException
	{
		lock.lock();
		try
		{
			while(count==_Array.length)
				notFull.await();
			_Array[endPointer] = value;
			count++;
			if(++endPointer == _Array.length)
				endPointer = 0;
			notEmpty.signalAll();
		}
		finally
		{
			lock.unlock();
		}

	}

}

