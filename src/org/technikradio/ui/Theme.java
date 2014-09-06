package org.technikradio.ui;

import java.util.Hashtable;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class Theme {
	private DataFont mainFont;
	private DataFont secondaryFont;
	private String mainIconFile;
	private DataColor mainColor;
	private DataColor secondColor;
	private DataColor backGroundColor;
	private Hashtable<String, String> IconFileList;
	
	/**
	 * @return the mainFont
	 */
	public DataFont getMainFont() {
		return mainFont;
	}

	/**
	 * @param mainFont the mainFont to set
	 */
	public void setMainFont(DataFont mainFont) {
		this.mainFont = mainFont;
	}

	/**
	 * @return the secondaryFont
	 */
	public DataFont getSecondaryFont() {
		return secondaryFont;
	}

	/**
	 * @param secondaryFont the secondaryFont to set
	 */
	public void setSecondaryFont(DataFont secondaryFont) {
		this.secondaryFont = secondaryFont;
	}

	/**
	 * @return the mainIconFile
	 */
	public String getMainIconFile() {
		return mainIconFile;
	}

	/**
	 * @param mainIconFile the mainIconFile to set
	 */
	public void setMainIconFile(String mainIconFile) {
		this.mainIconFile = mainIconFile;
	}

	/**
	 * @return the mainColor
	 */
	public DataColor getMainColor() {
		return mainColor;
	}

	/**
	 * @param mainColor the mainColor to set
	 */
	public void setMainColor(DataColor mainColor) {
		this.mainColor = mainColor;
	}

	/**
	 * @return the secondColor
	 */
	public DataColor getSecondColor() {
		return secondColor;
	}

	/**
	 * @param secondColor the secondColor to set
	 */
	public void setSecondColor(DataColor secondColor) {
		this.secondColor = secondColor;
	}

	/**
	 * @return the backGroundColor
	 */
	public DataColor getBackGroundColor() {
		return backGroundColor;
	}

	/**
	 * @param backGroundColor the backGroundColor to set
	 */
	public void setBackGroundColor(DataColor backGroundColor) {
		this.backGroundColor = backGroundColor;
	}

	/**
	 * @return the iconFileList
	 */
	public Hashtable<String, String> getIconFileList() {
		return IconFileList;
	}

	/**
	 * @param iconFileList the iconFileList to set
	 */
	public void setIconFileList(Hashtable<String, String> iconFileList) {
		IconFileList = iconFileList;
	}

	public Theme(){}
	
	public static Theme save(Theme t, String file){
		JAXB.marshal(t, file);
		return t;
	}
	
	public static Theme load(String file){
		return JAXB.unmarshal(file, Theme.class);
	}
}
