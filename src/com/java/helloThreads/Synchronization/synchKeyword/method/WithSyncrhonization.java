package com.java.helloThreads.Synchronization.synchKeyword.method;

class Table2 {
	synchronized void printTable(int num)
	{
		for(int i=1; i<=9; i++)
			System.out.println(num+" * " + i + " = " + (num*i));
	}
}

public class WithSyncrhonization
{
	public static void main(String[] args)
	{
		Table2 t = new Table2();
		new Thread(()->t.printTable(5)).start();
		new Thread(()->t.printTable(17)).start();
	}
}

