package org.technikradio.universal_tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import org.technikradio.cui.Console;

public class SettingsReader {

	private Hashtable<String, String> values;

	public SettingsReader() {
		super();
		values = new Hashtable<String, String>();
	}

	public SettingsReader(String file) throws FileNotFoundException {
		super();
		values = new Hashtable<String, String>();
		loadFile(file);
	}

	public SettingsReader(FileReader file) throws FileNotFoundException {
		super();
		values = new Hashtable<String, String>();
		loadFile(file);
	}

	/**
	 * This loads an configuration file
	 * 
	 * @param file
	 *            : the file to load
	 * @return the parent object
	 */
	public SettingsReader loadFile(String file) throws FileNotFoundException {
		this.loadFile(new FileReader(file));
		return this;
	}

	/**
	 * This loads an configuration file
	 * 
	 * @param file
	 *            : the file to load
	 * @return the parent object
	 */
	public SettingsReader loadFile(FileReader file)
			throws FileNotFoundException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(file);
			String line = null;
			while ((line = reader.readLine()) != null) {
				String s1 = line.split("=")[0];
				String s2 = line.split("=")[1];
				values.put(s1, s2);
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
					Console.log(Console.LogType.Error, this,
							"Can´t close BufferedReader");
					e.printStackTrace();
				}
			}
		}
		return this;
	}

	public SettingsReader saveFile(String file) throws FileNotFoundException{
		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter(file);
			for (Enumeration<String> e = values.keys(); e.hasMoreElements();) {
				String s = e.nextElement();
				pWriter.print(s + "=" + values.get(s));
				pWriter.flush();
			}
			pWriter.close();
		} catch (IOException e) {
			Console.log(Console.LogType.Error, this, "Fail to save settings");
			e.printStackTrace();
		} finally {
			if (pWriter != null)
				pWriter.flush();
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SettingsReader";
	}

}
