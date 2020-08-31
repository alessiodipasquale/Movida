package movida.dipasqualecolamonaco;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort{
	
	private static QuickSort instance = null;
	
	private QuickSort() {}
	
	public static QuickSort getInstance() {
		
		if(instance == null) {
			instance = new QuickSort();
		} 
		
		return instance;
	}
	
	
	public static <K extends Comparable<K>> void sort(K array[], int low, int high, Comparator<K> c) {
		if(low >= high)
				return;
		int m = partition(array, low, high, c);
		sort(array, low, m-1, c);
		sort(array, m+1, high, c);
	}
	
	private static <K extends Comparable<K>> int partition(K array[], int low, int high, Comparator<K> c) {
		int i = low;
		int j= high + 1;
		int m = ThreadLocalRandom.current().nextInt(i,j);
		K pivot = array[m];
		
		swap(array, low, m);
		
		while(i<j) {
			
			do {
				i++;
			} while(c.compare(array[i], pivot)<0 && i<high);
			
			do {
				j--;
			} while(c.compare(array[j], pivot)>0 && j>low);
			
			if (i<j) 
				swap(array,i,j);
			
		}
		
		return j;	
		
	}
	
	private static final <T> void swap(T array[], int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	

}
