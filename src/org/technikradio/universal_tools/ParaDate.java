package org.technikradio.universal_tools;

public class ParaDate {
	private short day;
	private short month;
	private int year;
	private Time time;

	public ParaDate(short day, short month, int year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
		this.time = new Time();
	}

	public ParaDate(short day, short month, int year, Time time) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
		this.time = time;
	}
	
	public ParaDate(){
		day = 1;
		month = 1;
		year = 1970;
		time = new Time();
	}

	/**
	 * @return the day
	 */
	public short getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(short day) {
		this.day = day;
	}

	/**
	 * @return the month
	 */
	public short getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(short month) {
		this.month = month;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Time time) {
		this.time = time;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(Short.toString(month));
		sb.append("//");
		sb.append(Short.toString(day));
		sb.append("//");
		sb.append(Integer.toString(year));
		sb.append("_");
		sb.append(time.toString());
		return sb.toString();
	}
	
	public static ParaDate valueOf(String s){
		ParaDate pd = new ParaDate();
		String[] parts = s.split("_");
		{
			String[] data = parts[0].split("//");
			pd.setMonth(Short.valueOf(data[0]));
			pd.setDay(Short.valueOf(data[1]));
			pd.setYear(Integer.valueOf(data[2]));
		}
		pd.setTime(Time.valueOf(parts[1]));
		return pd;
	}
	
	public String getMinimalDate(){
		StringBuilder sb = new StringBuilder();
		sb.append(Short.toString(day));
		sb.append(". ");
		sb.append(Short.toString(month));
		sb.append(". ");
		sb.append(Integer.toString(year));
		return sb.toString();
	}

}
