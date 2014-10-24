package org.technikradio.task;

/**
 * The FinishedNotifier interface is designed to work<br>
 * as a callback method if a thread (of any kind) has<br>
 * finished his work. This interface can be applyed to<br>
 * any ArgumentedThread or an extending class of it.<br>
 * This interface can also be applyed while the thread is<br>
 * running but not if the Thread already finished work.<br>
 * For further information please see the following articles:<br>
 * {@link org.technikradio.task.AdvancedThread#addFinishedNotifier(FinishedNotifier)
 * apply a FinishedNotifier to an already constructed Thread},<br>
 * {@link java.lang.Thread#isAlive() see if a thread is running},<br>
 * {@link org.technikradio.task.AdvancedThread#didFinishedWork()
 * see if a thread his work and all callbacks}
 * @author Doralitze
 */
public interface FinishedNotifier {
	/**
	 * This is called if the work is done.
	 */
	public void onFinishedWork();
}
