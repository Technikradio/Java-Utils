package org.technikradio.task;

/**
 * This class is designed to run an thread with arguments<br>
 * It fixes to problems at once because the Arguments don´t<br>
 * need to be final and there are not instable structures<br>
 * required to access the based Thread object.
 * @author Doralitze
 * @param <V> The type of arguments
 */
public class ArgumentedThread<V> extends AdvancedThread {
	private ArgumentedRunnable<V> ar;
	private V[] args;
	private boolean isRunning;
	
	private static Runnable getEmtyDummy(){
		return new Runnable(){
			@Override
			public void run() {
				//Nothing to do here
			}
		};
	}
	
	/**
	 * This constructs a new argumented thread.
	 */
	public ArgumentedThread() {
		super(getEmtyDummy());
		isRunning=false;
	}

	/**
	 * This constructs a new argumented thread
	 * @param target The context to run
	 */
	public ArgumentedThread(ArgumentedRunnable<V> target) {
		super(getEmtyDummy());
		ar = target;
		isRunning=false;
	}

	/**
	 * This constructs a new argumented thread
	 * @param name The name of the new thread
	 */
	public ArgumentedThread(String name) {
		super(getEmtyDummy(), name);
		isRunning=false;
	}

	/**
	 * This constructs a new argumented thread
	 * @param group the threadgroup
	 * @param target the context to run
	 */
	public ArgumentedThread(ThreadGroup group, ArgumentedRunnable<V> target) {
		super(group, getEmtyDummy());
		ar = target;
		isRunning=false;
	}

	/**
	 * This constructs a new argumented thread
	 * @param group The threadgroup
	 * @param name The name of the new thread
	 */
	public ArgumentedThread(ThreadGroup group, String name) {
		super(group, getEmtyDummy());
		super.setName(name);
		isRunning=false;
	}

	/**
	 * This constructs a new argumented thread
	 * @param target The context to run
	 * @param name The name of the new Thread
	 */
	public ArgumentedThread(ArgumentedRunnable<V> target, String name) {
		super(getEmtyDummy(), name);
		ar = target;
		isRunning=false;
	}

	/**
	 * This constructs a new argumented thread
	 * @param group The group of the new thread
	 * @param target The context to run
	 * @param name The name of the new thread
	 */
	public ArgumentedThread(ThreadGroup group, ArgumentedRunnable<V> target, String name) {
		super(group, getEmtyDummy(), name);
		ar = target;
		isRunning=false;
	}

	/**
	 * This constructs a new argumented thread
	 * @param group The group of the new thread
	 * @param target The context to run
	 * @param name The name of the new thread
	 * @param stackSize see: {@link java.lang.Thread#Thread(ThreadGroup, Runnable, String, long) StackSize}
	 */
	public ArgumentedThread(ThreadGroup group, ArgumentedRunnable<V> target, String name,
			long stackSize) {
		super(group, getEmtyDummy(), name, stackSize);
		ar = target;
		isRunning=false;
	}

	/**
	 * This constructs a new argumented thread
	 * @param target the context to run
	 * @param fn The finishedNotifier (see: {@link org.technikradio.task.FinishedNotifier FinishedNotifier})
	 */
	public ArgumentedThread(ArgumentedRunnable<V> target, FinishedNotifier fn) {
		super(getEmtyDummy(), fn);
		ar = target;
		isRunning=false;
	}
	
	/**
	 * This constructs a new argumented thread
	 * @param target the context to execute as a new thread
	 * @param arguments the arguments to apply to the thread
	 */
	public ArgumentedThread(ArgumentedRunnable<V> target, V[] arguments){
		super(getEmtyDummy());
		ar = target;
		args = arguments;
		isRunning=false;
	}
	
	/**
	 * This method has been overridden because<br>
	 * it was required to fit the needs of this<br>
	 * class. Read {@link org.technikradio.task
	 * .AdvancedThread#run() this} for further information.
	 */
	@Override
	public void run(){
		isRunning=true;
		ar.run(this, args);
		super.run();
	}
	
	/**
	 * This method sets the arguments for the thread.<br>
	 * NOTE: Use only before the task has been started!
	 * @param args The arguments to set
	 * @throws RunningTaskException will be thrown if<br>
	 *         the thread is already running
	 */
	public void setArguments(V[] args) throws RunningTaskException{
		if(!isAlredyRunning())
			this.args = args;
		else
			throw new RunningTaskException();
	}

	/**
	 * This method indicates if the thread has been<br>
	 * started or not. This is required because it<br>
	 * is not possible to attach any arguments to a<br>
	 * running thread. See also {@link org.technikradio.
	 * task.RunningTaskException this}.
	 * @return true | false indicating the state.
	 */
	public boolean isAlredyRunning() {
		return isRunning;
	}

}
