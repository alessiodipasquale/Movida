package movida.dipasqualecolamonaco;

import java.util.ArrayList;
import java.util.Set;

import movida.commons.MovidaFileException;

abstract class Map<K extends Comparable<K>, V extends Object> {
	
	final class Entry  {
		protected K key;
		protected V value;

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}
		
		 Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	
	public abstract void putIfAbsent(K key, V value);
	
	abstract public V search(K key) throws MovidaFileException;

	abstract public int length();

	
	/*abstract public void put(K key, V value);

	
	abstract void delete(K key) throws MovidaFileException;
	
	
	abstract public void clear();
	
	
	abstract public Set<Entry> entrySet();
	
	
	abstract public ArrayList<V> valueList();

	
	*/
	
}