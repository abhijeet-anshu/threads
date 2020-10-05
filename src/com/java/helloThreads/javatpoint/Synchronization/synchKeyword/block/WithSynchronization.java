package com.java.helloThreads.javatpoint.Synchronization.synchKeyword.block;

class Table {
	void printTable(int num)
	{
		synchronized (this)
		{
			for(int i=1; i<=9; i++)
				System.out.println(num+" * " + i + " = " + (num*i));
		}

	}
}

public class WithSynchronization
{
	public static void main(String[] args)
	{
		Table t = new Table();
		new Thread(()->t.printTable(5)).start();
		new Thread(()->t.printTable(17)).start();
	}
}
