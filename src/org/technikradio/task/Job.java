package org.technikradio.task;

/**
 * @author Doralitze
 */
public class Job{
	private ID id;
	private Runnable task;
	private AdvancedThread th;
	
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
	}
	
	/**
	 * This creates a new job.
	 * @param r the runnable to execute
	 * @param name the name of the ID
	 */
	public Job(Runnable r, String name){
		this(r, new ID(name));
	}
	
	void start(FinishedNotifier fn){
		AdvancedThread t = new AdvancedThread(task);
		t.setName("Task:" + id.getName());
		id.setRunningIdentifier(t.getId());
		t.addFinishedNotifier(fn);
		t.start();
		th = t;
	}
	
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
}
