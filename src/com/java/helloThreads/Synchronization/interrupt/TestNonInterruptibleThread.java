package com.java.helloThreads.Synchronization.interrupt;

import java.util.Random;

public class TestNonInterruptibleThread
{
	static void log(Exception e)
	{
		if(e==null)
			System.out.println("exception empty");
		else
			System.out.println("Exception:"+e+"\tError_Message:"+e.getMessage());
	}

	public static void main(String[] args)
	{
		Runnable r = () -> {
			Random random = new Random();
			whileLabel:
			while(true) {
				System.out.println("\n\n\nProcessing ....");
				try {
					Thread.sleep(5000); }
				catch (InterruptedException e) {log(e); System.out.println("I am a stupid thread, i ignore interruption");
					System.out.println("will consume more memory.... kill me I am psycho");
				}
				if(Thread.interrupted())
				{
					if(random.nextInt(100)>80)
					{
						System.out.println("ok...now I end");
						break whileLabel;
					}
					System.out.println("..I am a stupid thread, i ignore interruption");
					System.out.println("..will consume more memory.... kill me I am psycho");
				}
			}
		};

		Thread t = new Thread(r);
		t.start();
		System.out.println("thread alive" + t.isAlive());
		while(true) {
			try { Thread.sleep(5000); } catch (InterruptedException e) {log(e); }
			t.interrupt();
			System.out.println("thread state" + t.getState());
			System.out.println("thread alive" + t.isAlive());
			if(!t.isAlive()) break;
			}


	}

}
