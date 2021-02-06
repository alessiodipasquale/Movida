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

		mc = new MovidaCore();
		
		//IMovidaConfig
		mc.setMap(MapImplementation.HashConcatenamento);
		mc.setSort(SortingAlgorithm.QuickSort);
		mc.loadFromFile(new File("/Users/alessiodipasquale/Projects/Movida/src/movida/dipasqualecolamonaco/test.txt"));

		//IMovidaDB
		/*System.out.println("IMovidaDB: ");
			mc.clear();
			mc.loadFromFile(new File("/Users/alessiodipasquale/Projects/Movida/src/movida/dipasqualecolamonaco/test.txt"));
			System.out.println(mc.countMovies());
			System.out.println(mc.countPeople());
			mc.deleteMovieByTitle("scarface");
			System.out.println(mc.countMovies());
			System.out.println(mc.getPersonByName("Martin Scorsese").numberOfMovies());
			System.out.println(mc.getMovieByTitle("taxi driver").getYear());
			System.out.println(mc.getAllMovies().length);
			System.out.println(mc.getAllPeople().length);
			mc.saveToFile(new File("/Users/alessiodipasquale/Projects/Movida/src/movida/dipasqualecolamonaco/output.txt"));
		*/
		
		
		//IMovidaSearch
		/*System.out.println("IMovidaSearch: ");
			System.out.println(mc.searchMoviesByTitle("sca")[0].getTitle());
			
			for(Person e: mc.searchMostActiveActors(1)) {
				System.out.println(e.getName());
			}
			
			for(Movie m: mc.searchMostRecentMovies(1)) {
				System.out.println(m.getTitle());
			}
			
			for(Movie m: mc.searchMostVotedMovies(1)) {
				System.out.println(m.getTitle());
			}
			
	
			for(Movie m: mc.searchMoviesInYear(2000)) {
				System.out.println(m.getTitle());
			}
			
			for(Movie m: mc.searchMoviesDirectedBy("Martin Scorsese")) {
				System.out.println(m.getTitle());
			}
			
			for(Movie m: mc.searchMoviesStarredBy("Uma Thurman")) {
				System.out.println(m.getTitle());
			}*/
		
		
		
		//IMovidaCollaborations
			/*System.out.println("IMovidaCollaborations: ");
			Person e = mc.getPersonByName("al pacino");
			
			for (Person p: mc.getDirectCollaboratorsOf(e)) {
				System.out.println(p.getName());
			};
			
			System.out.println("");
			
			for (Person p: mc.getTeamOf(e)) {
				System.out.println(p.getName());
			};
			
			System.out.println("");
	
			for(Collaboration p: mc.maximizeCollaborationsInTheTeamOf(e)) {
				System.out.println(p.getActorA()+" "+p.getActorB()+" "+p.getScore());
			}*/

			
			
	}

}
