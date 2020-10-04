package com.java.helloThreads.javatpoint.runtime;

public class RuntimeShutDown
{
	public static void main(String args[])throws Exception{
		Runtime.getRuntime().exec("shutdown -s -t 0");
	}
}