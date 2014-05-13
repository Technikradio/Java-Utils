package org.technikradio.cui;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

interface ConsoleListener{
	public void listen();
}
interface InputListener{
	public void listen();
}
/**
 * @Author Doralitze
 * @License LGPLv3
 * @Copyright (c) Doralitze 2014
 */
public class Console {
	
	private static final int buffersize = 80; //For configuration: must contain the lenght of the current used OS console
	private static boolean autoFlush;
	private static String buffer[];
	private static int line;
	private static ArrayList<ConsoleListener> listeners;
	public static final int VERSION = 100;
	
	public enum LogType{
		Warning, Error, STDOUT, Information
	}
	
	static{
		autoFlush = true;
		buffer = new String[buffersize];
		listeners = new ArrayList<ConsoleListener>();
	}
	
	/**
	 * Flushes the buffer to the console
	 */
	public static void flush(){
		for(int i = 0; i <= getBuffersize(); i++){
			System.out.println(buffer[i]);
		}
		for(int i = 0; i <= listeners.size() - 1; i++){
			listeners.get(i).listen();
		}
	}
	
	public static void addListener(ConsoleListener listener){
		listeners.add(listener);
	}
	
	/**
	 * Clear the buffer
	 */
	public static void clear(){
		line = 0;
		for(int i = 0; i <= getBuffersize(); i++){
			buffer[i] = "";
		}
		if(isAutoFlush()){
			flush();
		}
	}

	/**
	 * @return the buffersize
	 */
	public static int getBuffersize() {
		return buffersize;
	}

	/**
	 * @return the autoFlush
	 */
	public static boolean isAutoFlush() {
		return autoFlush;
	}

	/**
	 * @param autoFlush the autoFlush to set
	 */
	public static void setAutoFlush(boolean autoFlush) {
		Console.autoFlush = autoFlush;
	}
	
	public static void println(Object o){
		Console.print(o);
		doReturn();
	}
	
	public static void print(Object o){
		buffer[getLine()] = buffer[getLine()] + o.toString();
	}

	/**
	 * @return the line
	 */
	public static int getLine() {
		return line;
	}

	/**
	 * @param line the line to set
	 */
	public static void setLine(int line) {
		if(line <= getBuffersize() && line >= 0)
		Console.line = line; else Console.line = getBuffersize();
	}
	
	/**
	 * This terminates the current line
	 */
	public static void doReturn(){
		if(getBuffersize() == getLine()) setLine(0); else setLine(getLine() + 1);
	}
	
	/**
	 * log a message
	 * @param type the type of log
	 * @param sender the sending object (probably has to be 'this')
	 * @param message the message to log
	 */
	public static void log(LogType type, Object sender, String message){
		println(getDateTime() + " [" + getTypeString(type) + "] [" + sender.toString() + "] :: " + message);
		System.out.println(getDateTime() + " [" + getTypeString(type) + "] [" + sender.toString() + "] :: " + message);
	}
	
	private static String getTypeString(LogType t){
		if(t == LogType.Error) return "ERROR"; else if(t == LogType.Information) return "INFORMATION";
		else if(t == LogType.STDOUT) return "STDOUT"; else return "WARNING";
	}
	
	private static String getDateTime() {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = new Date();
	    return dateFormat.format(date);
	}
	
	public static void write(String s){
		Console.print(s);
	}
}
