package org.technikradio.task;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

public class AdvancedHashtable<K, V> extends Hashtable<K, V> {
	private static final long serialVersionUID = -5489809316910888489L;

	public AdvancedHashtable() {
		super();
	}

	public AdvancedHashtable(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public AdvancedHashtable(int initialCapacity) {
		super(initialCapacity);
	}

	public AdvancedHashtable(Map<? extends K, ? extends V> t) {
		super(t);
	}
	
	@SuppressWarnings("unchecked")
	public V[] getAllValues(){
		Enumeration<V> values = this.elements();
		ArrayList<V> l = new ArrayList<V>();
		while(values.hasMoreElements()){
			V ne = values.nextElement();
			if(ne != null)
				l.add(ne);
		}
		return ((V[]) l.toArray(new Object[l.size()])); //Should work because there are no other types
	}
}
