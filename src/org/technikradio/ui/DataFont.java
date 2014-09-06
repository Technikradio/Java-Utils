package org.technikradio.ui;

public class DataFont {
	private String fontName;
	private int fontSize;
	private boolean bolt;
	private boolean cursive;
	private DataColor color;
	/**
	 * @return the fontName
	 */
	public String getFontName() {
		return fontName;
	}
	/**
	 * @param fontName the fontName to set
	 */
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	/**
	 * @return the fontSize
	 */
	public int getFontSize() {
		return fontSize;
	}
	/**
	 * @param fontSize the fontSize to set
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	/**
	 * @return the bolt
	 */
	public boolean isBolt() {
		return bolt;
	}
	/**
	 * @param bolt the bolt to set
	 */
	public void setBolt(boolean bolt) {
		this.bolt = bolt;
	}
	/**
	 * @return the cursive
	 */
	public boolean isCursive() {
		return cursive;
	}
	/**
	 * @param cursive the cursive to set
	 */
	public void setCursive(boolean cursive) {
		this.cursive = cursive;
	}
	/**
	 * @return the color
	 */
	public DataColor getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(DataColor color) {
		this.color = color;
	}
	
	public DataFont(){}
}
