package movida.dipasqualecolamonaco;
import java.io.File;

import movida.commons.Collaboration;
import movida.commons.MapImplementation;
import movida.commons.Movie;
import movida.commons.Person;
import movida.commons.SortingAlgorithm;



import java.io.File;

public class Main {
	
	static MovidaCore mc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mc = new MovidaCore();
		mc.setMap(MapImplementation.HashConcatenamento);
		mc.setSort(SortingAlgorithm.QuickSort);
		//mc.setSort(SortingAlgorithm.QuickSort);
		mc.loadFromFile(new File("/Users/alessiodipasquale/Projects/Movida/src/movida/dipasqualecolamonaco/test.txt"));
		//System.out.println(mc.movieData.length());
		//mc.deleteMovieByTitle("scarface");
		//System.out.println(mc.getPersonByName("Martin Scorsese").numberOfMovies());
		
		/*for(movida.commons.Movie e: mc.getAllMovies()) {
			System.out.println(e);
		}*/
		
		/*Movie[] m = mc.getAllMovies();
		int i=0;
		while(i<m.length) {
			System.out.println(m[i++].getTitle());
		}*/
		
		/* Test collaboration
		for(Person e: mc.searchMostActiveActors(1)) {
			for( Collaboration c :e.getCollaborations()) {
				System.out.println(c.getActorA()+ " "+c.getActorB()+" "+c.getMovies());
			}
		}*/
		
		
		//mc.saveToFile(new File("/Users/alessiodipasquale/Projects/Movida/src/movida/dipasqualecolamonaco/output.txt"));

	}

}
