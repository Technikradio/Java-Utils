package org.technikradio.cf;

import java.io.PrintStream;
import java.util.Hashtable;

public class Environment {
	
	private Stack stack;
	private ElementStack es;
	private Api api;
	private PrintStream os;
	private boolean debugMode = false;
	
	private Environment(Stack parentStack, ElementStack stackToExecute, Api api, PrintStream ost){
		stack = parentStack;
		es = stackToExecute;
		this.api = api;
		os = ost;
	}
	
	/**
	 * This creates a new instance of an execution environment
	 * @param code The code to load
	 * @param api The (maybe function enriched) API to use
	 */
	public Environment(String code, Api api){
		if(isDebugMode()){
			for(String s : clear(code)){ //Init stack
				String c = "";
				for(byte b : s.getBytes()){
					c.concat(Byte.toString(b)).concat(" ");
				}
				System.out.println(s + " : " + c);
			}
		}
		es = new ElementStack(clear(code));
		stack = new Stack();
		this.api = api;
		if(api.getOutPutStream() == null){
			os = System.out;
			api.setOutPutStream(os);
		}
		else
			os = api.getOutPutStream();
		api.setWords(new Hashtable<String, ElementStack>());
	}
	
	private String[] clear(String code) {
		
		code.replaceAll("\n|  ", " ");
		return code.split(" ");
	}

	public void startExecution(){
		api.setMainWords(es);
		while(es.hasMoreElements()){
			if(!doWord(es.nextElement()))
				break;
		}
	}
	
	public boolean doWord(String word){
		if(api.contains(word)){
			if(!api.execute(word, stack)){
				throwError(2);
				return false;
			}
		} else if(api.getWords().contains(word)){
			Environment e = new Environment(stack, api.getWords().get(word).clone(), api, os);
			e.startExecution();
		} else {
			try{
				stack.push(Double.parseDouble(word));
			} catch (NumberFormatException e){
				if(isDebugMode())
					System.out.println("Bad word:".concat(word));
				throwError(1);
				return false;
			} catch (Exception e){
				throwError(3);
				return false;
			}
		}
		return true;
	}
	
	public void setOutputStream(PrintStream ps){
		os = ps;
		api.setOutPutStream(ps);
	}

	private void throwError(int errorCode) {
		os.println("Couldn´t execute. An Error accourd. Position: " + Integer.toString(es.getPointer()));
		os.print("Errorcode: ");
		os.print(errorCode);
		os.print(' ');
		switch(errorCode){
		case 1:os.print("NaN");break;
		case 2:os.print("Error on command execution");break;
		case 3:os.print("Unknown error (Windows perhaps)");break;
		}
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
}
