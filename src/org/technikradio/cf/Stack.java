package org.technikradio.cf;

import java.util.ArrayDeque;

public class Stack {
	private ArrayDeque<Value> values;
	
	public Stack(){
		values = new ArrayDeque<Value>();
	}
	
	public void push(Double value){
		values.add(new Value(value));
	}
	
	public double pull(){
		if(values.size() == 0)
			return 0.0d;
		return values.pollLast().getValue();
	}
	
	public double get(){
		if(values.size() == 0)
			return 0.0d;
		return values.getLast().getValue();
	}
	
	public void duplicate(){
		push(get());
	}
}
