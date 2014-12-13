package org.technikradio.cf;

import java.util.Scanner;

public class Terminal {
	
	private static boolean running = true;
	private static Environment currentEnvironment;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Api a = Api.getNewInstance();
		a.addFunction(new Function(){

			@Override
			public boolean execute(Stack valueStack, Api parent) {
				running = false;
				return true;
			}}, "exit");
		a.addFunction(new Function(){

			@Override
			public boolean execute(Stack valueStack, Api parent) {
				currentEnvironment.setDebugMode(!currentEnvironment.isDebugMode());
				return true;
			}}, "switchDebug");
		System.out.println("CF interpreter v1.0. Enter 'exit' to exit. Start your input with a 0 to init the stack.");
		while(running){
			Environment en = new Environment(s.nextLine(), a);
			currentEnvironment = en;
			en.startExecution();
		}
		s.close();
	}

}
