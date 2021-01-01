package movida.dipasqualecolamonaco;

import java.lang.reflect.Array;
import java.util.Set;

public class AVL<K extends Comparable<K>,V extends Object> extends Map<K,V> {
	V t;
	public void delete(K key) {
		
	}
	
	@Override
	public void putIfAbsent(K key, V value) {
	
	}
	
	
	@Override
	public V search(K key) throws KeyException {
		return t;
	}
	/*public V search(K key) {
		
	}*/

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Map<K, V>.Entry> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
}
