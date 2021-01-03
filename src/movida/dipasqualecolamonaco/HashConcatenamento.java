package movida.dipasqualecolamonaco;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class HashConcatenamento<K extends Comparable<K>, V extends Object> extends Map<K,V> 
{
	protected LinkedList<Data>[] m;
	private int length = 0;
	
	public HashConcatenamento(int l)
	{
		this.length = Math.abs(l);
		m =  new LinkedList[length];
		for(int i =0;i < m.length;i++) 
		{
			m[i] = new LinkedList<Data>();
		}
		
	}

	public void delete(K key) {
		int hash = Math.abs(key.hashCode() % length);
		if(m[hash].getFirst().key.equals(key)) m[hash].removeFirst();
		else {
			int i=0;
			while(m[hash] != null) {
				if(m[hash].get(i).key.equals(key)) {
					m[hash].remove(i);
					break;
				} else i++;
			}
		}

	}
	
	@Override
	public void putIfAbsent(K key, V value) {
		int hash = Math.abs(key.hashCode() % length);
		m[hash].add(new Data(key,value));
	}
	
	
	@Override
	public V search(K key) throws KeyException {
		int hash = Math.abs(key.hashCode() % length);
		if(m[hash].getFirst().key.equals(key)) return (V)(m[hash].getFirst().value);
		else if(m[hash].equals(null)) return null;
		else {
			int i=1;
			while(m[hash] != null) {
				if(m[hash].get(i).key.equals(key)) {
					return (V)(m[hash].get(i).value);
				} else i++;
			}
		}
		
		throw new KeyException();
	}
	
	@Override
	public int length() {
		int count = 0;
		int i=0;
		while(i<m.length) {
			count+=m[i].size();
			i++;
		}
		return count;
	}
	
	
	@Override
	public void clear() {
		for(int i =0;i < m.length;i++) 
		{
			m[i] = null;
		}
	}
	
	@Override
	public Set<Map<K, V>.Data> getData() {
		Set<Map<K, V>.Data> out = new HashSet<Map<K,V>.Data>();
		for (LinkedList<Map<K, V>.Data> e : m)
		{
			while(!e.isEmpty()) {
				Data tmp = e.getFirst();
				e.removeFirst();
				out.add(tmp);
			}
		}
		//System.out.println(out.toString());
		return out;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(Arrays.toString(m));
		return builder.toString();
	}
}
