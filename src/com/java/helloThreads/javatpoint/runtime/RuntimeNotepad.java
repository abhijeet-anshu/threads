package com.java.helloThreads.javatpoint.runtime;

public class RuntimeNotepad
{
	public static void main(String args[])throws Exception{
		Runtime.getRuntime().exec("notepad");//will open a new notepad
	}
}
