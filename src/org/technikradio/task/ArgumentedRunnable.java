package org.technikradio.task;

/**
 * This interface is desinged to fit the<br>
 * requirements of the ArgumentedThread<br>
 * class because there was no possibility<br>
 * to attach the parent object into the<br>
 * Runnable interface.
 * @author Doralitze
 * @param <V> The type of arguments to use.
 * @see org.technikradio.task.ArgumentedThread
 */
public interface ArgumentedRunnable<V> {
	public void run(ArgumentedThread<V> parent, V[] args);
}
