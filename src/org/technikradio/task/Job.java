package org.technikradio.task;

/**
 * @author Doralitze
 */
public class Job{
	private ID id;
	private Runnable task;
	private AdvancedThread th;
	private int maxProgress;
	private int currentProgress;
	private boolean allowed;
	
	/**
	 * This creates a new Job.
	 * @param r the runnable to execute
	 */
	public Job(Runnable r){
		this(r, new ID("new_job"));
	}
	
	/**
	 * This creates a new job based on the given ID
	 * NOTE: this is the only constructor making
	 * possible to assign a strong fingerprint
	 * @param r the runnable to execute
	 * @param id the ID to comply with
	 */
	public Job(Runnable r, ID id){
		task = r;
		this.id = id;
		maxProgress = 100;
		currentProgress = 0;
		allowed = true;
	}
	
	/**
	 * This creates a new job.
	 * @param r the runnable to execute
	 * @param name the name of the ID
	 */
	public Job(Runnable r, String name){
		this(r, new ID(name));
	}
	
	//Undocumented because it is intern
	void start(FinishedNotifier fn){
		AdvancedThread t = new AdvancedThread(task);
		t.setName("Task:" + id.getName());
		id.setRunningIdentifier(t.getId());
		t.addFinishedNotifier(fn);
		if(allowed)
			t.start();
		th = t;
	}
	
	//Undocumented because it is intern
	AdvancedThread getThread(){
		return th;
	}
	
	/**
	 * This returns the assigned ID
	 * @return the ID
	 */
	public ID getID(){
		return id;
	}

	/**
	 * @return the maximum possible progress<br>
	 * (for exmaple 100 if the job calculates in<br>
	 * percent)
	 */
	public synchronized int getMaxProgress() {
		return maxProgress;
	}

	/**
	 * @param maxProgress the maximum amount of progress<br>
	 * possible (default: 100 because of percent)
	 */
	public synchronized void setMaxProgress(int maxProgress) {
		this.maxProgress = maxProgress;
	}

	/**
	 * @return the current made progress
	 */
	public synchronized int getCurrentProgress() {
		return currentProgress;
	}

	/**
	 * @param currentProgress the currentProgress to set<br>
	 * (from 0 to {@link org.technikradio.task.Job#setMaxProgress(int)
	 * maximum progress})
	 */
	public synchronized void setCurrentProgress(int currentProgress) {
		this.currentProgress = currentProgress;
	}
	
	//Undocumented because it is intern
	synchronized void stop(){
		allowed = false;
	}
}
