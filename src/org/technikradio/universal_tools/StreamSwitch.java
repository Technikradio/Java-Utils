package org.technikradio.universal_tools;

import java.io.PrintStream;
import java.util.ArrayList;

public class StreamSwitch<Stream extends PrintStream> implements Runnable{
	
	private Thread t;
	private Stream input;
	private ArrayList<Stream> output;
	
	public StreamSwitch(Stream s){
		super();
		output = new ArrayList<Stream>();
		t = new Thread(this);
		t.setDaemon(true);
		t.setPriority(Thread.MIN_PRIORITY);
		t.setName("StreamSwitchDemon:".concat(s.toString()));
		input = s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public Stream getInput(){
		return input;
	}
}
