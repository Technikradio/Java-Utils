package org.technikradio.controls;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.technikradio.controls.Calendar.Month.Day;

public class Calendar extends JPanel {
	public static final short TYPE_DISABLED = 0;
	public static final short TYPE_ENABLED = 1;
	public static final short TYPE_CLICKED = 2;
	private static final long serialVersionUID = 2978852859495419818L;
	private ArrayList<StateChangedListener> stateChangedListeners;
	private ArrayList<Month> months;
	private int year;
	private InfoBox infoBox;
	private int maxAllowedDays = 0;
	private boolean locked = true;

	public Calendar(int days, int year) {
		super(new GridLayout());
		maxAllowedDays = days;
		this.year = year;
		setup();
	}

	public Calendar(LayoutManager layout, int days) {
		super(layout);
		maxAllowedDays = days;
		setup();
	}

	public Calendar(boolean isDoubleBuffered, int days) {
		super(isDoubleBuffered);
		maxAllowedDays = days;
		setup();
	}

	public Calendar(LayoutManager layout, boolean isDoubleBuffered, int days) {
		super(layout, isDoubleBuffered);
		maxAllowedDays = days;
		setup();
	}

	private void setup() {
		stateChangedListeners = new ArrayList<StateChangedListener>();
		months = new ArrayList<Month>();
		infoBox = new InfoBox();
		infoBox.setDaysLeft(maxAllowedDays);
		infoBox.setLocked(this.isLocked());
		this.add(infoBox);
		for (int i = 1; i < 13; i++) {
			Month m = new Month(getDays(year, i));
			months.add(m);
			this.add(m);
		}
		this.addStateChangedListener(infoBox);
	}

	/**
	 * This loads an identity into the calendar Format: <allowed day 1>|<allowed
	 * day 2> where an allowed day is defined as follows: <day>:<month>:<state>
	 * Note: this method doesn´t care about checking the format. It is up to you
	 * to check it if you aren´t shoure about the correctness of your source. If
	 * you provide this method with a broken idetity you will line up with a
	 * totally messed up calendar.
	 * 
	 * @param identity
	 *            The identity to load
	 */
	public void loadIdentity(String identity) {
		String[] days = identity.split("|");
		for (String day : days) {
			if (day != "" && day != null) {
				String[] parts = day.split(":");
				int date = Integer.parseInt(parts[0]);
				int month = Integer.parseInt(parts[1]);
				int state = Integer.parseInt(parts[2]);
				Month m = months.get(month - 1);
				Day d = m.getDay(date);
				d.setState((short) state);
			}
		}
	}

	/**
	 * This will return an shorted identity of this calendar
	 * 
	 * @return the generated identity
	 */
	public String getIdentity() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < months.size(); i++) {
			Month m = months.get(i);
			Day[] dl = m.getDays();
			for (int j = 0; i < dl.length; i++) {
				Day d = dl[j];
				if (d.getState() != TYPE_DISABLED) {
					s.append(j + 1);
					s.append(':');
					s.append(i + 1);
					s.append(':');
					s.append(d.getState());
					s.append('|');
				}
			}
		}
		return s.toString();
	}

	/**
	 * This will return an full identity of this calendar. It is recommendet to
	 * use the short on because the full one contains more data than nessesary.
	 * However if the destination system doesn´t have a default state or a
	 * different on you must use this method.
	 * 
	 * @return the generated identity
	 */
	public String getFullIdentity() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < months.size(); i++) {
			Month m = months.get(i);
			Day[] dl = m.getDays();
			for (int j = 0; i < dl.length; i++) {
				Day d = dl[j];

				s.append(j + 1);
				s.append(':');
				s.append(i + 1);
				s.append(':');
				s.append(d.getState());
				s.append('|');

			}
		}
		return s.toString();
	}

	private int getDays(int year, int month) {
		// TODO Add code to decide the amount of days inside this month
		return 0;
	}

	public interface AllowedChangeListener {
		public void allowedChanged(boolean newState);
	}

	public interface StateChangedListener {
		public void changedByCode(short newState, Object source, short oldState);

		public void changedByUser(short newState, Object source);
	}

	public void addStateChangedListener(StateChangedListener i) {
		stateChangedListeners.add(i);
		for (Month m : months) {
			m.addStateChangedListener(i);
		}
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	class Month extends JPanel {
		private static final long serialVersionUID = -8955843311815327560L;
		private ArrayList<Day> dayList;
		private ArrayList<StateChangedListener> stateChangedListeners;

		class Day extends JPanel implements MouseListener {
			private static final long serialVersionUID = 6467788095873411530L;
			private ArrayList<StateChangedListener> stateChangedListeners;
			private JLabel l;
			private short state;

			public Day(int count) {
				super();
				// Make UI
				l = new JLabel();
				l.setText(Integer.toString(count));
				this.add(l);
				this.addMouseListener(this);
				setState(TYPE_DISABLED);
				stateChangedListeners = new ArrayList<StateChangedListener>();
			}

			public short getState() {
				return state;
			}

			public void setState(short state) {
				short oldState = this.state;
				this.state = state;
				if (state == TYPE_CLICKED) {
					this.setBackground(Color.RED);
				} else if (state == TYPE_DISABLED) {
					this.setBackground(Color.lightGray);
				} else if (state == TYPE_ENABLED) {
					this.setBackground(Color.BLUE);
				}
				for (StateChangedListener s : stateChangedListeners) {
					s.changedByCode(state, this, oldState);
				}
			}

			public void addStateChangedListener(StateChangedListener i) {
				stateChangedListeners.add(i);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (this.state == TYPE_DISABLED) {
					// Do nothing
				} else if (this.state == TYPE_ENABLED) {
					setState(TYPE_CLICKED);
				} else if (this.state == TYPE_CLICKED) {
					setState(TYPE_ENABLED);
				}
				for (StateChangedListener s : stateChangedListeners) {
					s.changedByUser(state, this);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		}

		public Month(int days) {
			super();
			// Make UI
			dayList = new ArrayList<Day>();
			for (int i = 0; i < days; i++) {
				Day d = new Day(i + 1);
				dayList.add(d);
				this.add(d);
			}
			stateChangedListeners = new ArrayList<StateChangedListener>();
		}

		public void addStateChangedListener(StateChangedListener i) {
			stateChangedListeners.add(i);
			for (Day d : dayList) {
				d.addStateChangedListener(i);
			}
		}

		public Day getDay(int date) {
			return dayList.get(date - 1);
		}

		public Day[] getDays() {
			Day[] d = new Day[dayList.size()];
			return dayList.toArray(d);
		}
	}

	class InfoBox extends JComponent implements StateChangedListener {
		private static final long serialVersionUID = 8511965928874657612L;
		private int days = 0;
		private boolean locked;

		@Override
		public void changedByCode(short newState, Object source, short old) {
			if (newState == TYPE_CLICKED && old == TYPE_ENABLED) {
				setDaysLeft(getDaysLeft() - 1);
			} else if (newState == TYPE_ENABLED && old == TYPE_CLICKED) {
				setDaysLeft(getDaysLeft() + 1);
			}
		}

		@Override
		public void changedByUser(short newState, Object source) {
			// Nothing to do here
		}

		public void setDaysLeft(int daysLeft) {
			days = daysLeft;
		}

		public boolean areMoreDaysAllowed() {
			if (days > 0)
				return true;
			return false;
		}

		public int getDaysLeft() {
			return days;
		}

		public boolean isLocked() {
			return locked;
		}

		public void setLocked(boolean locked) {
			this.locked = locked;
		}

	}

}
