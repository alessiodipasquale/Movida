package movida.dipasqualecolamonaco;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayOrdinato<K extends Comparable<K>,V extends Object> extends Map<K,V> {

	private Entry[] array;
	private int lastIndex;
	
	@Override
	public void putIfAbsent(K key, V value) {
		if (lastIndex == -1) 
		{
			array[0] = new Entry(key, value);
			lastIndex = 0;
			return;
		}
		
		int i = locationOf(key);
		if (i <= lastIndex && array[i].getKey().equals(key))
		{
			return;
		}
		else
		{
			if (lastIndex + 1 < array.length)
			{
				for (int j = lastIndex; j >= i; j--)
					array[j+1] = array[j];
				
				array[i] = new Entry(key, value);
			}
			else
			{
				Entry temp[] = (Entry[]) Array.newInstance(Entry.class , array.length * 2);
				
				for (int j = 0; j < i; j++ ) 
					temp[j] = array[j];
				temp[i] = new Entry(key, value);
				for (int j = i; j <= lastIndex; j++)
					temp[j+1] = array[j];
				
				array = temp;
			}
			lastIndex++;
		}
		
	}
	
	
	@Override
	public V search(K key) throws KeyException {
		int i = locationOf(key);
		if (array[i].key.equals(key))
		{
			return array[i].value;			
		}
		else
		{
			throw new KeyException();					
		}
	}

	private int locationOf(K key) {
		return locationOf(key, 0, length());
	}
	
	private int locationOf(K key, int low, int up) {
		int pivot = (low + up) / 2;
		if (array[pivot].key.equals(key)) 
			return pivot;
		else if (up - low <= 1)
			return (array[pivot].key.compareTo(key) < 0)? pivot + 1: pivot;
		else if (array[pivot].key.compareTo(key) > 0)
			return locationOf(key, low, pivot);
		else 
			return locationOf(key, pivot, up);
	}
	
	public int length() {
		return lastIndex + 1;
	}
}
