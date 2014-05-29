package org.technikradio.universal_tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

public class LangReader {
	
	private Hashtable<String, String> strings;
	
	public LangReader(){
		super();
		strings = new Hashtable<String, String>();
	}
	
	public LangReader(String file) throws FileNotFoundException, IOException{
		super();
		strings = new Hashtable<String, String>();
		loadFile(file);
	}

	public void loadFile(String file) throws IOException, FileNotFoundException {
		FileReader fr = new FileReader(new File(file));
		BufferedReader br = new BufferedReader(fr);
		String[] s;
		do {
			s = br.readLine().split("=");
			setValue(s[0], s[1]);
		} while (s != null);
		br.close();
		fr.close();
	}
	
	public void loadFile(File f) throws IOException, FileNotFoundException {
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String[] s;
		do {
			s = br.readLine().split("=");
			setValue(s[0], s[1]);
		} while (s != null);
		br.close();
		fr.close();
	}
	
	public void save(String file) throws IOException{
		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter(new FileWriter(file));
			for(int i = 0; i < getEntryCount(); i++){
				pWriter.print(getKey(i) + "=" + getValue(i));
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (pWriter != null)
				pWriter.flush();
			pWriter.close();
		}
	}
	
	public int getEntryCount(){
		return strings.size();
	}
	
	public void remove(String key){
		strings.remove(key);
	}
	
	public void removeIfEntry(String key, String value){
		strings.remove(key, value);
	}
	
	public boolean contains(String key){
		return strings.containsKey(key);
	}
	
	public boolean conatinsValue(String value){
		return strings.contains(value);
	}

	public void setValue(String key, String value) {
		if(strings.containsKey(key)){
			strings.replace(key, value);
		} else {
			strings.put(key, value);
		}
	}
	
	public String getValue(String key){
		return strings.get(key);
	}
	
	/**
	 * This will get the value at key. If the entry with key does not exist it will return the default value.
	 * @param key The key to look for.
	 * @param defaultValue The default value in case it will not find the key
	 * @return the value at key or the default value
	 */
	public String getValue(String key, String defaultValue){
		return strings.getOrDefault(key, defaultValue);
	}
	
	/**
	 * Gets the value at index
	 * 
	 * @param index
	 * @return The value at index; If index is greater than the indexes it will
	 *         return an empty String
	 */
	public String getValue(int index){
		int i = 0;
		String s = "";
		Enumeration<String> k = strings.elements();
		do{
			s = k.nextElement();
			if(i == index){
				return s;
			}
			i++;
		} while(s != null);
		return "";
	}
	
	public String getKey(int index){
		int i = 0;
		String s = "";
		Enumeration<String> k = strings.keys();
		do{
			s = k.nextElement();
			if(i == index){
				return s;
			}
			i++;
		} while(s != null);
		return "";
	}

	@Override
	public String toString(){
		return "LangReader";
	}
}
