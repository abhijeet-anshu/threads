package com.java.helloThreads.javatpoint.multithreading;

public class TestShutDownHook
{
	public static void main(String[] args)
	{
		Runnable firstTask =  () -> {
			final String threadName = "sleepingTask........" + Thread.currentThread().getName();
			Thread.currentThread().setName(threadName);
			System.out.println( "init...task  " + threadName  +
							" ----------->\n------------>\n" +
							"\n----------->am a sleeping thread  "   );
			Runtime r=Runtime.getRuntime();
			r.addShutdownHook(new Thread( ()  ->
			{
				System.out.println("hook from...." + threadName);
				try{Thread.sleep(100000000);} catch (InterruptedException e) {}
			}));
			try{Thread.sleep(100000000);} catch (InterruptedException e) {} //true daemon
		};
		System.out.println("main thread...iniitating sleeping thread");
		new Thread(firstTask).start();
		Runtime r=Runtime.getRuntime();
		r.addShutdownHook(new Thread( ()  -> System.out.println("hook from..main.." + Thread.currentThread().getName()) ));
		try{Thread.sleep(100000000);}
		catch (InterruptedException e) {}
		System.out.println("main thread...end main thread");
	}
}
