package com.java.helloThreads.javatpoint.Synchronization.synchKeyword.Static;


public class WithSynchronization
{
	synchronized static void printTable(int num)
	{
		System.out.println("thread that has lock will print: table of " + num);
			for(int i=1; i<=9; i++)
				System.out.println(num+" * " + i + " = " + (num*i));
	}
	public static void main(String[] args)
	{

		new Thread(()->printTable(5)).start();
		new Thread(()->printTable(17)).start();
		new Thread(()->printTable(21)).start();
		new Thread(()->printTable(2)).start();
		new Thread(()->printTable(4)).start();
		new Thread(()->printTable(9)).start();
	}
}
