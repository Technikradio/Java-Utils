package org.technikradio.universal_tools;

public class ParaDate {
	private short day;
	private short month;
	private int year;
	private Time time;
	
	public class Time{
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
			String s = Short.toString(hours);
			s.concat(":");
			s.concat(Short.toString(minutes));
			s.concat(":");
			s.concat(Short.toString(seconds));
			s.concat(":");
			s.concat(Integer.toString(millis));
			s.concat(":");
			s.concat(Integer.toString(nanos));
			return s;
		}
	}

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
		String s = Short.toString(month);
		s.concat("/");
		s.concat(Short.toString(day));
		s.concat("/");
		s.concat(Integer.toString(year));
		s.concat(" ");
		s.concat(time.toString());
		return s;
	}

}
