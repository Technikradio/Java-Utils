package org.technikradio.universal_tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SettingsReader {

	private Dictionary<String, String> values;

	public SettingsReader() {
		super();
		values = new Dictionary<String, String>();
	}
	
	public SettingsReader(String file) throws FileNotFoundException {
		super();
		values = new Dictionary<String, String>();
		loadFile(file);
	}
	
	public SettingsReader(FileReader file) throws FileNotFoundException {
		super();
		values = new Dictionary<String, String>();
		loadFile(file);
	}
	
	/**
	 * This loads an config file
	 * @param file : the file to load 
	 * @return the parent object
	 */
	public SettingsReader loadFile(String file) throws FileNotFoundException{
		this.loadFile(new FileReader(file));
		return this;
	}
	
	/**
	 * This loads an config file
	 * @param file : the file to load 
	 * @return the parent object
	 */ 
	public SettingsReader loadFile(FileReader file) throws FileNotFoundException {
		try{
			reader = new BufferedReader(file);
			String line = null;
			while ((line = reader.readLine()) != null) {
				String s1 = line.split("=")[0];
				String s2 = line.split("=")[1];
				values.add(s1, s2);
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					Console.Log(Console.LogType.Error, this, "Can´t close BufferedReader");
					e.printStackTrace();
				}
			}
		}
		return this;
	}

	public SettingsReader saveFile(String file){
		PrintWriter pWriter = null;
        	try {
        		for(int i = 0; i < values.size() i++){
        			
        		}
        	} catch (IOException e) {
         		Console.Log(Console.LogType.Error, this, "Fail to save settings");
            		e.printStackTrace();
		} finally {
            		if (pWriter != null)
                		pWriter.flush();
        	}
		return this;	
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SettingsReader";
	}

}
