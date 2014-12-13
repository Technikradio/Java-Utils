package org.technikradio.cf;

public interface Function {
	
	/**
	 * This interface is desinged to make java code exessable from cf
	 * @param valueStack the stack of the current executing chunk of code
	 * @return true if the execution of the command was successfull
	 */
	public boolean execute(Stack valueStack, Api parent);
}
