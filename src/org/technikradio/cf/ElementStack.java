package org.technikradio.cf;

import java.util.Enumeration;

public class ElementStack implements Enumeration<String> {
	
	private String[] elements;
	private int pointer;
	
	public ElementStack(String[] items){
		elements = items;
	}

	@Override
	public boolean hasMoreElements() {
		if(pointer >= elements.length - 1)
			return false;
		return true;
	}

	@Override
	public String nextElement() {
		if(!hasMoreElements())
			return null;
		pointer++;
		return elements[pointer];
	}
	
	public void setPointer(int newPosition){
		pointer = newPosition;
	}
	
	int getPointer(){
		return pointer;
	}
	
	protected ElementStack clone(){
		ElementStack es = new ElementStack(elements);
		es.setPointer(getPointer());
		return es;
	}

}
