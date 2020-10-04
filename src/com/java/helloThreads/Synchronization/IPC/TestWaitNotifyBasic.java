package com.java.helloThreads.Synchronization.IPC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntFunction;


class CustomerAccount
{

	private int balance;
	int getBalance() { return balance;}
	CustomerAccount() {balance=0;}
	boolean deductBalance(final int amount)
	{
		System.out.println("> invoked to deduct :" + amount);
		synchronized (this)
		{
			while (balance <= amount)
			{
				try {wait(); } catch (InterruptedException e) {TestWaitNotifyBasic.logException(e);}
				System.out.println("> hey I was notified for cutting amount:" + amount);
			}
			balance-= amount;
		}
		System.out.println("> deducted:" + amount);
		return true;
	}
	boolean addBalance(final int amount)
	{
		 try {Thread.sleep(3000);} catch (InterruptedException e) {{TestWaitNotifyBasic.logException(e);}}
		//https://stackoverflow.com/questions/64193485/why-is-the-code-just-after-wait-method-not-invoked
		synchronized (this)
		{
			balance += amount;
			notifyAll();
		}
		System.out.println("> balance added: " + amount);
		return true;
	}
}

public class TestWaitNotifyBasic
{

	public static void main(String[] args)
	{
		final CustomerAccount chuck = new CustomerAccount();

		IntFunction<Runnable> addBalance = (amount) -> { return () -> chuck.addBalance(amount);};
		IntFunction<Runnable> deductBalance = (amount) -> { return () -> chuck.deductBalance(amount);};

		ExecutorService threadPool = Executors.newFixedThreadPool(100);

		//balance deduction
		threadPool.execute(deductBalance.apply(1000) );
		threadPool.execute(deductBalance.apply(50) );
		threadPool.execute(deductBalance.apply(5000));

		//balance addition
		threadPool.execute(addBalance.apply(900));
		threadPool.execute(addBalance.apply(40));
		threadPool.execute(addBalance.apply(80));
		threadPool.execute(addBalance.apply(8000));

		threadPool.shutdown();
		while (!threadPool.isTerminated())
		{
			try {Thread.sleep(300);} catch (InterruptedException e) {{logException(e);}}
		}
		System.out.println("----------------------" );
		System.out.println("thread operations finished, final balance : " + chuck.getBalance());
		int actualBalance = (900+40+80+8000)-(1000+50+5000);
		System.out.println("Validity check " + actualBalance);
	}

	static void logException(InterruptedException e)
	{
		System.out.println("###########interruptedexception#########" + e);
	}

}
