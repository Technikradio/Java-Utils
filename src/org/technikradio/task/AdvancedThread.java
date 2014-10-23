package org.technikradio.task;

import java.util.ArrayList;

public class AdvancedThread extends Thread {
	
	private ArrayList<FinishedNotifier> lfn;
	private boolean finishedWork;

	public AdvancedThread() {
		super();
		lfn = new ArrayList<FinishedNotifier>();
		finishedWork = false;
	}

	public AdvancedThread(Runnable target) {
		super(target);
		lfn = new ArrayList<FinishedNotifier>();
		finishedWork = false;
	}

	public AdvancedThread(String name) {
		super(name);
		lfn = new ArrayList<FinishedNotifier>();
		finishedWork = false;
	}

	public AdvancedThread(ThreadGroup group, Runnable target) {
		super(group, target);
		lfn = new ArrayList<FinishedNotifier>();
		finishedWork = false;
	}

	public AdvancedThread(ThreadGroup group, String name) {
		super(group, name);
		lfn = new ArrayList<FinishedNotifier>();
		finishedWork = false;
	}

	public AdvancedThread(Runnable target, String name) {
		super(target, name);
		lfn = new ArrayList<FinishedNotifier>();
		finishedWork = false;
	}

	public AdvancedThread(ThreadGroup group, Runnable target, String name) {
		super(group, target, name);
		lfn = new ArrayList<FinishedNotifier>();
		finishedWork = false;
	}

	public AdvancedThread(ThreadGroup group, Runnable target, String name,
			long stackSize) {
		super(group, target, name, stackSize);
		lfn = new ArrayList<FinishedNotifier>();
		finishedWork = false;
	}
	
	public AdvancedThread(Runnable target, FinishedNotifier fn){
		super(target);
		lfn = new ArrayList<FinishedNotifier>();
		finishedWork = false;
	}
	
	/**
	 * This method adds an FinishedNotifier to<br/>
	 * the list of notifications
	 * @param fn The FinishedNotifier to add
	 */
	public void addFinishedNotifier(FinishedNotifier fn){
		lfn.add(fn);
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		super.run();
		for(FinishedNotifier fn : lfn){
			fn.onFinishedWork();
		}
		finishedWork = true;
	}
	
	/**
	 * This method decides if a thread did finished its work
	 * Note: this is not the same as Thread.isAlive() because
	 * this is just true if all FinishedNotifier´s have done
	 * their work.
	 * @return true if the thread did finished his work
	 */
	public boolean didFinishedWork(){
		return finishedWork;
	}
}
