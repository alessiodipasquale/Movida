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
	
	
	public static <T> void sort(T[] array, int low, int high, Comparator<T> c) {
		if(low >= high)
				return;
		int m = partition(array, low, high, c);
		sort(array, low, m-1, c);
		sort(array, m+1, high, c);
	}
	
	private static <T> int partition(T[] array, int low, int high, Comparator<T> c) {
		int inf = low;
		int sup= high + 1;
		int pos = low + (int) Math.floor((high-low+1) * Math.random());
		T pivot = array[pos];
		
		swap(array, low, pos);
		
		while(true) {
			
			do {
				inf++;
			} while(c.compare(array[inf], pivot)<=0 && inf<high);
			
			do {
				sup--;
			} while(c.compare(array[sup], pivot)>0);
			
			if (inf<sup) {
				swap(array,inf,sup);
			} else break;
		}
		swap(array,low,sup);
		
		return sup;	
		
	}
	
	private static final <T> void swap(T array[], int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	

}
