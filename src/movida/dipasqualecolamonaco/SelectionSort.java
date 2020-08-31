package movida.dipasqualecolamonaco;
import java.util.Comparator;

public class SelectionSort {
	
	private static SelectionSort instance = null;
	
	private SelectionSort() {}
	
	public static SelectionSort getInstance() {
		
		if(instance == null) {
			instance = new SelectionSort();
		}
		
		return instance;
	}
	
	public static <T> void sort(T[] array, final int low, final int high, Comparator<T> c) {
		assert 0<= low;
		assert low <= high;
		assert high <= array.length;
		
		for(int i = low; i<high; ++i) {
			var minIndex = i;
			
			for(int j=i+1; j<high; ++j) {
				if(c.compare(array[j], array[i]) < 0) {
					minIndex = j;
				}
			}
			
			final var tmp = array[i];
			array[i] = array[minIndex];
			array[minIndex] = tmp;
		}
	}

}
