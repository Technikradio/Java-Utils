package org.technikradio.universal_tools;

import org.technikradio.universal_tools.Console.LogType;

public class Time{
	
	private static boolean debugOutput = false;
	
	/**
	 * @param debugOutput the debugOutput to set
	 */
	public static void setDebugOutput(boolean debugOutput) {
		Time.debugOutput = debugOutput;
	}

	private short hours;
	private short minutes;
	private short seconds;
	private int millis;
	private int nanos;
	private boolean inited;
	
	public Time(){
		hours = 0;
		minutes = 0;
		seconds = 0;
		millis = 0;
		nanos = 0;
		inited = false;
	}

	/**
	 * @return the hours
	 */
	public short getHours() {
		if(!inited)
			throw new IllegalStateException("Time is not inited");
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(short hours) {
		if(!isInited())
			setInitedFlag();
		this.hours = hours;
	}

	/**
	 * @return the minutes
	 */
	public short getMinutes() {
		if(!inited)
			throw new IllegalStateException("Time is not inited");
		return minutes;
	}

	/**
	 * @param minutes the minutes to set
	 */
	public void setMinutes(short minutes) {
		if(!isInited())
			setInitedFlag();
		this.minutes = minutes;
	}

	/**
	 * @return the seconds
	 */
	public short getSeconds() {
		if(!inited)
			throw new IllegalStateException("Time is not inited");
		return seconds;
	}

	/**
	 * @param seconds the seconds to set
	 */
	public void setSeconds(short seconds) {
		if(!isInited())
			setInitedFlag();
		this.seconds = seconds;
	}

	/**
	 * @return the millis
	 */
	public int getMillis() {
		if(!inited)
			throw new IllegalStateException("Time is not inited");
		return millis;
	}

	/**
	 * @param millis the millis to set
	 */
	public void setMillis(int millis) {
		if(!isInited())
			setInitedFlag();
		this.millis = millis;
	}

	/**
	 * @return the nanos
	 */
	public int getNanos() {
		if(!inited)
			throw new IllegalStateException("Time is not inited");
		return nanos;
	}

	/**
	 * @param nanos the nanos to set
	 */
	public void setNanos(int nanos) {
		if(!isInited())
			setInitedFlag();
		this.nanos = nanos;
	}

	public Time(short hours, short minutes, short seconds, int millis,
			int nanos) {
		super();
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.millis = millis;
		this.nanos = nanos;
		inited = true;
	}

	/**
	 * @return the inited
	 */
	public boolean isInited() {
		return inited;
	}

	/**
	 * @param inited the inited to set
	 */
	public void setInitedFlag() {
		this.inited = true;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(Short.toString(hours));
		sb.append(":");
		sb.append(Short.toString(minutes));
		sb.append(":");
		sb.append(Short.toString(seconds));
		sb.append(":");
		sb.append(Integer.toString(millis));
		sb.append(":");
		sb.append(Integer.toString(nanos));
		if(debugOutput)
			Console.log(LogType.Information, "Debug/ParaDate", "Created string: " + sb.toString());
		return sb.toString();
	}
	
	public static Time valueOf(String s){
		Time t = new Time();
		String[] parts = s.split(":");
		switch(parts.length){
		case 5:
			t.setNanos(Short.valueOf(parts[4]));
		case 4:
			t.setMillis(Short.valueOf(parts[3]));
		case 3:
			t.setSeconds(Short.valueOf(parts[2]));
		case 2:
			t.setMinutes(Short.valueOf(parts[1]));
		case 1:
			t.setHours(Short.valueOf(parts[0]));
		}
		return t;
	}
}
