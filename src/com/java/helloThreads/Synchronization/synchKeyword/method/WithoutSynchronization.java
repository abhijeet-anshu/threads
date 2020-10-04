package com.java.helloThreads.Synchronization.synchKeyword.method;

class Table {
	void printTable(int num)
	{
		for(int i=1; i<=9; i++)
			System.out.println(num+" * " + i + " = " + (num*i));
	}
}

public class WithoutSynchronization
{
	public static void main(String[] args)
	{
		Table t = new Table();
		new Thread(()->t.printTable(5)).start();
		new Thread(()->t.printTable(17)).start();
	}
}
