package org.technikradio.task;

public class Tasks {

	private static AdvancedHashtable<ID, Job> t;
	private static boolean running;
	private static AdvancedThread updateValuesThread;
	private static int updateIntervall;

	/**
	 * @return the current updateintervall of the information collector
	 */
	public static synchronized int getUpdateIntervall() {
		return updateIntervall;
	}

	/**
	 * @param updateIntervall the updateIntervall to set for the information collector
	 */
	public static synchronized void setUpdateIntervall(int updateIntervall) {
		Tasks.updateIntervall = updateIntervall;
	}

	static {
		t = new AdvancedHashtable<ID, Job>();
		running = true;
		updateIntervall = 1000;
		updateValuesThread = new AdvancedThread(new Runnable(){
			private void updateValues(){
				
			}
			
			@Override
			public void run() {
				while(Tasks.running == true){
					updateValues();
					try {
						Thread.sleep(Tasks.updateIntervall);
					} catch (InterruptedException e) {
						//should be ok becaus it will check next
					}
				}
			}});
		updateValuesThread.setPriority(Thread.MIN_PRIORITY);
		updateValuesThread.start();
	}

	/**
	 * A basic method to apply small tasks without having<br/>
	 * to code long lines of code. This uses only the basic<br/>
	 * settings
	 * @param r The runnable to apply
	 */
	public static void i(Runnable r) {
		apply(new Job(r));
	}

	/**
	 * This apply a task based on a Job and an ID<br/>
	 * It is recommended to use the ID of the task<br/>
	 * but not required.
	 * @param j The Job containing the task
	 * @param id The ID to give the running Element
	 * @return true | false based on the fact if the
	 *         given ID is already on the stack or not
	 */
	public static boolean apply(Job j, ID id) {
		if (t.containsKey(id))
			return false;
		t.put(id, j);
		j.start(new FinishedNotifier() {
			@Override
			public void onFinishedWork() {
				gc();
			}
		});
		return true;
	}

	/**
	 * This method apply a given task based on it´s ID
	 * @param j the task to apply
	 * @return true | false based on the fact if the<br/>
	 *         given ID is already on the stack or not
	 */
	public static boolean apply(Job j) {
		ID id = j.getID();
		if (t.containsKey(id))
			return false;
		t.put(id, j);
		j.start(new FinishedNotifier() {
			@Override
			public void onFinishedWork() {
				gc();
			}
		});
		return true;
	}

	/**
	 * This method apply a job after a given period of time.<br/>
	 * It is recommended to use the ID of the Job but not<br/>
	 * required.
	 * @param j The job containing the task to apply
	 * @param id The ID to give the running Element
	 * @param millis The period of time between the<br/>
	 *        registration and the execution in milliseconds
	 * @return true | false based on the fact if the<br/>
	 *         given ID is already on the stack or not
	 */
	public static boolean applyAfterTime(final Job j, final ID id,
			final long millis) {
		if (t.containsKey(id))
			return false;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(millis);
					t.put(id, j);
					j.start(new FinishedNotifier() {
						@Override
						public void onFinishedWork() {
							gc();
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		return true;
	}

	/**
	 * This method apply a job after a given period of time.<br/>
	 * This method uses the ID of the given Job to identify<br/>
	 * it.
	 * @param j The job containing the task to apply
	 * @param millis The period of time between the<br/>
	 *        registration and the execution in milliseconds
	 * @return true | false based on the fact if the<br/>
	 *         given ID is already on the stack or not
	 */
	public static boolean applyAfterTime(final Job j, final long millis) {
		final ID id = j.getID();
		if (t.containsKey(id))
			return false;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(millis);
					t.put(id, j);
					j.start(new FinishedNotifier() {
						@Override
						public void onFinishedWork() {
							gc();
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		return true;
	}

	/**
	 * This method interrupt a task based on the given ID.<br/>
	 * NOTE: This method uses the ID given at the time of<br/>
	 * registration and NOT the ID of the task. It is highly<br/>
	 * recommended to use the task´s ID at registration but<br/>
	 * not required. If there is an other task having the ID<br/>
	 * of the registered task´s ID the wrong task may be<br/>
	 * interrupted!
	 * @param id The ID to identify the correct task.
	 */
	public static void interrupt(ID id) {
		t.get(id).getThread().interrupt();
	}

	private static void gc() {
		for (Job j : t.getAllValues()) {
			if (!j.getThread().isAlive()) {
				// .didFinishedWork is not used
				// because it won´t ever be true
				// (this will perhaps executed as
				// an FinishedNotifier)
				t.remove(j);
			}
		}
	}
	
	/**
	 * This method returns the running Thread of an applyed task.<br/>
	 * If there is no task with the given ID it will return null.
	 * @param key The ID to identify the task.
	 * @return the running Thread (AdvancedThread)
	 */
	public static AdvancedThread getRunningInstanceByID(ID key){
		return t.get(key).getThread();
	}

	/**
	 * This method will interrupt all running tasks.
	 */
	public static void interruptAll() {
		for(Job j : t.getAllValues()){
			j.getThread().interrupt();
		}
	}
	
	/**
	 * This method must be called before the<br>
	 * application exits, because it interrupts<br>
	 * all running threads and cleans up the environment.
	 */
	public static synchronized void exit(){
		gc();
		for(Job j : t.getAllValues()){
			try{
				j.getThread().interrupt();
			}catch(NullPointerException e){
				j.stop();
				t.remove(j);
			}
		}
		gc();
		running = false;
		updateValuesThread.interrupt();
		System.gc();
	}
	
	/**
	 * This method detects the amount of<br>
	 * running tasks.
	 * @return The amount of currently running tasks
	 */
	public static synchronized int getTaskCount(){
		return t.size();
	}
}
