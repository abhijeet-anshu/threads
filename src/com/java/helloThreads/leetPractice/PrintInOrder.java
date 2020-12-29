package com.java.helloThreads.leetPractice;

import java.util.function.Consumer;



	class PrintInOrder
	{
		private static class Foo
		{
			private volatile int n;

			public Foo()
			{
				n = 1;
			}

			synchronized void waitTurn(int myId)
			{
				while (n != myId)
				{
					try
					{
						wait();
					} catch (InterruptedException ex)
					{
						//ignore
					}
				}
			}

			synchronized void markDone(int myId)
			{
				if (myId != n) throw new AssertionError("Invalid state :" + myId + "...." + n);
				n++;
				notifyAll();
			}


			public void first(Runnable printFirst) throws InterruptedException
			{
				waitTurn(1);
				// printFirst.run() outputs "first". Do not change or remove this line.
				printFirst.run();
				markDone(1);
			}

			public void second(Runnable printSecond) throws InterruptedException
			{
				waitTurn(2);
				// printSecond.run() outputs "second". Do not change or remove this line.
				printSecond.run();
				markDone(2);
			}

			public void third(Runnable printThird) throws InterruptedException
			{
				waitTurn(3);
				// printThird.run() outputs "third". Do not change or remove this line.
				printThird.run();
				markDone(3);
			}


		}

		@FunctionalInterface
		interface FI<T>
		{
			void myMethod(Runnable r) throws InterruptedException;
		}

		static void FIExecutor(FI<Foo> inst, Runnable r)
		{
			new Thread(() ->
			{
				try
				{
					inst.myMethod(r);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}).run();
		}

		static void executor() throws InterruptedException
		{

			Consumer<String> printer = (s) -> System.out.println(s);
			Runnable firstPrinter = () -> printer.accept("first");
			Runnable secondPrinter = () -> printer.accept("second");
			Runnable thirdPrinter = () -> printer.accept("third");

			Foo someInst = new Foo();


			Thread t1 = new Thread(() -> FIExecutor(someInst::first, firstPrinter));
			Thread t2 = new Thread(() -> FIExecutor(someInst::second, secondPrinter));
			Thread t3 = new Thread(() -> FIExecutor(someInst::third, thirdPrinter));

			t3.start();
			Thread.sleep(2000);
			t2.start();
			Thread.sleep(2000);
			t1.start();
		}

		public static void main(String[] args) throws InterruptedException
		{
			executor();
		}
	}
