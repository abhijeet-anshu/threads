package com.java.helloThreads.docbasics;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestBoundedBuffer
{


	static void log(Exception e)
	{
		if(e==null)
			System.out.println("empty exception");
		else
			System.out.println("Exception: " + e + "\tMsg: " + e.getMessage() + "\t Cause:" + e.getCause());
	}

	public static void main(String[] args)
	{
		//test the bound buffer generated

		final int BoundBufferCapacity = 500;
		final int MaxThreadSize = 150;


		final int arrSize = 10000;
		final int offset = new Random().nextInt(arrSize);
		int[] pivot = new int[arrSize];
		int[] data = new int[arrSize];
		for(int i=0; i<arrSize; i++)
			pivot[i] = i;
		for(int i=0; i<arrSize; i++)
			data[i] = i+offset;

		//randomize the array
		shuffleArray(pivot);


		//create 200 tasks
		// 100 to put in bounded buffer
		//100 to retrieve from bounded buffer

		final BoundedBuffer queue = new BoundedBuffer(BoundBufferCapacity);
		//final List<Integer> myList = new ArrayList(arrSize);
		int outputArray[] = new int[arrSize];
		Arrays.fill(outputArray, -1);

		Runnable[] tasks = new Runnable[arrSize*2];

		for(int i=0; i<arrSize; i++) //100
			tasks[i] = PUT(data[pivot[i]], queue);


		for(int i=arrSize; i<tasks.length; i++)//another 100
			tasks[i] = GET(i-arrSize, queue, outputArray);

		shuffleTasks(tasks);


		//create pool
		ExecutorService threadPool = Executors.newFixedThreadPool(MaxThreadSize);
		for(Runnable r: tasks)
			threadPool.submit(r);

		threadPool.shutdown();


		while(!threadPool.isTerminated())
		{
			try { Thread.sleep(50);} catch (InterruptedException e) {log(e);}

			int someCount = 0;
			for(int elem: outputArray)  someCount += elem==-1?0:1;
			System.out.println("..current count" + someCount);

		}

		Arrays.sort(outputArray);

		for(int elem: outputArray)  System.out.print(elem + " ");

	}

	static Runnable GET(final int index, final BoundedBuffer b, int[] outputArray)
	{
		return () ->
		{
			Thread.currentThread().setName(index + " get->");
			try
			{
				outputArray[index] = b.get();
			}
			catch (InterruptedException e) {
				print("abort " + Thread.currentThread().getName());log(e); return;
			}
		};
	}

	static void shuffleTasks(Runnable[] arr)
	{
		Random r = new Random();
		for(int i=0; i<arr.length; i++)
		{
			int swapIndex = r.nextInt(arr.length);
			Runnable temp = arr[i];
			arr[i] = arr[swapIndex];
			arr[swapIndex] = temp;
		}
	}

	static void shuffleArray(int[] arr)
	{
		Random r = new Random();
		for(int i=0; i<arr.length; i++)
		{
			int swapIndex = r.nextInt(arr.length);
			int temp = arr[i];
			arr[i] = arr[swapIndex];
			arr[swapIndex] = temp;
		}
	}

	static Runnable PUT(final int value, final BoundedBuffer b)
	{
		return () ->
		{
			Thread.currentThread().setName("<-put " + value);
			try
			{
				b.put(value);
			} catch (InterruptedException e)
			{
				print("abort " + Thread.currentThread().getName());
				log(e);
				return;
			}
		};
	}

	static void print(String msg)
	{
		System.out.println(msg);
	}




}
