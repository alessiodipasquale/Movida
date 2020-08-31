package movida.dipasqualecolamonaco;
import java.util.Comparator;
import movida.commons.Movie;
//import movida.commons.Person;

public class Comparators {
	public static Comparator<Movie> Title = new Comparator<Movie>() {
		@Override
		public int compare(Movie m1, Movie m2) {
			return m1.getTitle().compareTo(m2.getTitle());
		}
	};
	
	public static Comparator<Movie> Date = new Comparator<Movie>() {
		@Override
		public int compare(Movie m1, Movie m2) {
			return m1.getYear().compareTo(m2.getYear());
		}
	};
	
	public static Comparator<Movie> Vote = new Comparator<Movie>() {
		@Override
		public int compare(Movie m1, Movie m2) {
			return m1.getVotes().compareTo(m2.getVotes());
		}
	};
	
	/*public static Comparator<Person> NumberOfMovies = new Comparator<Person>() {
		@Override
		public int compare(Person p1, Person p2) {
			return p1.numberOfMovies() - p2.numberOfMovies();
		}
	};*/
}
