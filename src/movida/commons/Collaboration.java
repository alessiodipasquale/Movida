package movida.commons;

import java.util.ArrayList;

public class Collaboration {

	Person actorA;
	Person actorB;
	ArrayList<Movie> movies;
	
	public Collaboration(Person actorA, Person actorB) {
		this.actorA = actorA;
		this.actorB = actorB;
		this.movies = new ArrayList<Movie>();
	}

	public Person getActorA() {
		return actorA;
	}

	public Person getActorB() {
		return actorB;
	}

	public Double getScore(){
		
		Double score = 0.0;
		
		for (Movie m : movies)
			score += m.getVotes();
		
		return score / movies.size();
	}
	
	public ArrayList<Movie> getMovies() {
		return this.movies;
	}
	
	public void addMovie(Movie m) {
		this.movies.add(m);
	}
	
	public void removeMovie(Movie m) {
		this.movies.remove(m);
	}
	
	public boolean containsMovie(Movie m) {
		return this.movies.contains(m);
	}
	
}
