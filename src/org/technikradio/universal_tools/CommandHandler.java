package org.technikradio.universal_tools;

import java.io.IOException;
import java.util.ArrayList;

interface Command {
	public void doCommand(String[] args);

	public String getLowHelp();

	public void printHelp();

	public String getName();
}

public class CommandHandler implements Command, Runnable {

	private ArrayList<Command> commands;
	private int helpPageLenght;
	private int maxHelpPageCount;
	private boolean running = false;
	private Thread th;
	private int ID;
	
	/**
	 * This create a new object from type 'CommandHandler'
	 */
	public CommandHandler() {
		commands = new ArrayList<Command>();
		setHelpPageLenght(15);
		try {
			this.addCommand(this);
		} catch (DoubleCommandException e) {
			e.printStackTrace();
		}
		setID(0);
	}

	/**
	 * This executes an command line
	 * 
	 * @param line
	 * @return The parent object
	 */
	public CommandHandler execCommand(String line) {
		String[] cl = line.split(" ");
		String command = cl[0];
		String[] args = new String[cl.length - 2];
		for (int i = 1; i < cl.length - 1; i++) {
			args[i - 1] = cl[i];
		}
		for (int i = 0; i < commands.size(); i++) {
			Command c = commands.get(i);
			if (c.getName() == command) {
				c.doCommand(args);
				break;
			}
		}
		return this;
	}

	/**
	 * This adds a command to the handler
	 * 
	 * @param The
	 *            command to add
	 * @return The parent object
	 */
	public CommandHandler addCommand(Command c) throws DoubleCommandException{
		for(int i = 0; i < commands.size(); i++){
			if(commands.get(i).getName() == c.getName()){
				throw new DoubleCommandException("There is an other command named like " + c.getName(), new Throwable(), false, true);
			}
		}
		commands.add(c);
		return this;
	}

	private int getMaxPage() {
		return (int) (commands.size() / this.getHelpPageLenght());
	}

	@Override
	public void doCommand(String[] args) {
		String page = (args.length > 0) ? args[0] : "1";
		try {
			int mpage = Integer.parseInt(page);
			if (mpage > maxHelpPageCount) {
				System.out.println("There are just " + maxHelpPageCount
						+ " pages.");
				return;
			}
			mpage--;
			System.out.println("help: (page " + mpage + " of "
					+ maxHelpPageCount + ")");
			for (int i = mpage * this.getHelpPageLenght(); i <= (mpage + 1)
					* this.getHelpPageLenght(); i++) {
				System.out.println("	" + commands.get(i).getLowHelp());
			}
		} catch (NumberFormatException ex) {
			for (int i = 0; i < commands.size(); i++) {
				Command c = commands.get(i);
				if (c.getName() == page) {
					c.printHelp();
					break;
				}
			}
		}
		return;
	}

	@Override
	public String getLowHelp() {
		return "help [<page>]";
	}

	@Override
	public void printHelp() {
		System.out.println("This displays the help");
		System.out
				.println("If the page number not set will the default page 1.");
	}

	@Override
	public String getName() {
		return "help";
	}

	/**
	 * @return the helpPageLenght
	 */
	public int getHelpPageLenght() {
		return helpPageLenght;
	}

	/**
	 * @param helpPageLenght
	 *            the helpPageLenght to set
	 * @return The parent object
	 */
	public CommandHandler setHelpPageLenght(int helpPageLenght) {
		this.helpPageLenght = helpPageLenght;
		this.maxHelpPageCount = this.getMaxPage();
		return this;
	}

	@Override
	public void run() {
		while(running){
			StringBuilder line = new StringBuilder();
			while(true){
				int c = 0;
				if(!running)
					break;
				try {
					c = System.in.read();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(c == 13){
					break;
				} else {
					line.append(c);
				}
			}
			this.execCommand(new String(line));
		}
	}
	
	/**
	 * Start the commandhandler
	 * @return The parent object
	 * @throws RunningStateException
	 */
	public CommandHandler start() throws RunningStateException{
		if(running)
			throw new RunningStateException("The commandhandler is already running");
		running=true;
		th = new Thread(this);
		th.setPriority(Thread.MIN_PRIORITY);
		th.setName(this.toString());
		th.start();
		return this;
	}
	
	public CommandHandler stop() throws RunningStateException{
		if(!running)
			throw new RunningStateException("The commandhandler is not running");
		running = false;
		th.interrupt();
		return this;
	}
	
	@Override
	public String toString(){
		return "CommandHandler " + getID();
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 * @return The parent object
	 */
	public CommandHandler setID(int iD) {
		ID = iD;
		return this;
	}

}
