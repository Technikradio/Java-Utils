package org.technikradio.task;

public class Tasks {

	private static AdvancedHashtable<ID, Job> t;

	static {
		t = new AdvancedHashtable<ID, Job>();
	}

	public static void i(Runnable r) {
		apply(new Job(r));
	}

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
	
	public static AdvancedThread getRunningInstanceByID(ID key){
		return t.get(key).getThread();
	}

	public static void interruptAll() {
		for(Job j : t.getAllValues()){
			j.getThread().interrupt();
		}
	}
}
