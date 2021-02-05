package movida.dipasqualecolamonaco;
import movida.commons.*;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MovidaCore implements IMovidaDB, IMovidaConfig, IMovidaSearch, IMovidaCollaborations{
	Database db;
	Map<String, Movie> movieData;
	Map<String, Person> personData;
	ArrayList<Collaboration> collaborations;

	SortingAlgorithm algorithm = SortingAlgorithm.QuickSort;
	MapImplementation map = MapImplementation.HashConcatenamento;

	MovidaCore() {
		this.db = new Database();
	}
	
	@Override
	public void loadFromFile(File f) {
		db.setStructure(this.map);
		try {
			db.load(f);
		}catch (Exception err) {
			err.printStackTrace();
			return;
		}
		movieData = db.getMovieData();
		personData = db.getPersonData();
		collaborations = db.getCollaborations();

		return;
	}
	
	@Override
	public void saveToFile(File f) {

		try {
			FileWriter save = new FileWriter(f);
			for (Movie e : movieData.getData()) {
				save.append("Title:" + "\t" + e.getTitle() + "\n");

				save.append("Year:" + "\t" + e.getYear() + "\n");

				save.append("Director:" + "\t" + e.getDirector().getName() + "\n");

				Person cast[] = e.getCast();
				save.append("Cast:" + "\t");
				for (int i = 0; i < cast.length; i++) {
					if (i == cast.length - 1)
						save.append(cast[i].getName() + "\n"); // l'ultimo elemento non ha la virgola
					else
						save.append(cast[i].getName() + "," + "\t");
				}

				save.append("Votes" + "\t" + e.getVotes() + "\n");
				save.append("\n");

			}
			System.out.println("Scrittura su file completata.");
			save.close();
		} catch (IOException e) {
			System.out.println("Impossibile aprire il file.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void clear() {
		movieData.clear();
		personData.clear();
	}

	@Override
	public int countMovies() {
		return movieData.length();
	}

	@Override
	public int countPeople() {
		return personData.length();
	}
	
	@Override
	public boolean deleteMovieByTitle(String title) {
		try {
			movieData.delete(title);
			System.out.println("Eliminazione film completata.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Movie getMovieByTitle(String title) {
		System.out.println("Ricerca film completata.");
		return movieData.search(title.trim().toLowerCase());
	}

	@Override
	public Person getPersonByName(String name) {
		System.out.println("Ricerca persona completata.");
		return personData.search(name.trim().toLowerCase());
	}
	
	@Override
	public Movie[] getAllMovies() {
		Movie[] arr = new Movie[movieData.length()];
		int i = 0;
		for (Movie e : movieData.getData()) {
			arr[i++] = e;
			//System.out.println(e.getValue().getTitle());
		}
		return arr;
	}

	@Override
	public Person[] getAllPeople() {
		Person[] arr = new Person[personData.length()];
		int i = 0;			
		for (Person e : personData.getData()) {
			arr[i++] = e;
			//System.out.println(e.getValue().getName());
		}
		return arr;
	}

	//FINE DB E INIZIO CONFIG
	
	@Override
	public boolean setSort(SortingAlgorithm a) {
		switch(a) {
			case QuickSort: {
				this.algorithm = SortingAlgorithm.QuickSort;
				return true;
			}
			case SelectionSort: {
				this.algorithm = SortingAlgorithm.SelectionSort;
				return true;
			}
			default: return false;
		}
	}
	
	@Override
	public boolean setMap(MapImplementation m) {
		switch(m) {
		case HashConcatenamento: {
			this.map = MapImplementation.HashConcatenamento;
			this.db.setStructure(MapImplementation.HashConcatenamento);
			// DA FARE LA COPIA
			return true;
		}
		case AVL: {
			this.map = MapImplementation.AVL;
			this.db.setStructure(MapImplementation.AVL);
			// DA FARE LA COPIA
			return true;
		}
		default:
			return false;
			
		}
	}

	//FINE CONFIG INIZIO SEARCH
	public Movie[] searchMoviesByTitle(String title) {
		ArrayList<Movie> res = new ArrayList<Movie>();
		for(Movie e: movieData.getData()) {
			if(e.getTitle().contains(title.trim().toLowerCase()))
				res.add(e);
		}
		return res.toArray(new Movie[res.size()]);
	}
	
	public Movie[] searchMoviesInYear(Integer year) {
		ArrayList<Movie> res = new ArrayList<Movie>();
		for(Movie e: movieData.getData()) {
			if(e.getYear().equals(year))
				res.add(e);
		}
		return res.toArray(new Movie[res.size()]);
	}
	
	public Movie[] searchMoviesDirectedBy(String name) {
		ArrayList<Movie> res = new ArrayList<Movie>();
		for(Movie e: movieData.getData()) {
			if(e.getDirector().getName().contains(name.trim().toLowerCase()))
				res.add(e);
		}
		return res.toArray(new Movie[res.size()]);
	}
	
	public Movie[] searchMoviesStarredBy(String name) {
		ArrayList<Movie> res = new ArrayList<Movie>();
		for(Movie e: movieData.getData()) {
			for(Person p: e.getCast()) {
				if(p.getName().contains(name.trim().toLowerCase())) {
					res.add(e);
				}
			}
		}
		return res.toArray(new Movie[res.size()]);
	}

	public Movie[] searchMostVotedMovies(Integer N) {
		Movie[] movies = movieData.getData().toArray(new Movie[movieData.getData().size()]);
		switch(algorithm) {
		case QuickSort:
			QuickSort.getInstance().sort(movies, 0, movies.length-1, Comparators.Vote.reversed());
			break;
			
		case SelectionSort:
			SelectionSort.getInstance().sort(movies, 0, movies.length, Comparators.Vote.reversed());
			break;
		}
		Movie[] res = new Movie[N<movies.length ? N : movies.length];
		for(int i=0; i<N && i<movies.length; i++) {
			res[i]=movies[i];
		}
		return res;
	}
	
	public Movie[] searchMostRecentMovies(Integer N) {
		Movie[] movies = movieData.getData().toArray(new Movie[movieData.getData().size()]);
		switch(algorithm) {
		case QuickSort:
			QuickSort.getInstance().sort(movies, 0, movies.length-1, Comparators.Date.reversed());
			break;
			
		case SelectionSort:
			SelectionSort.getInstance().sort(movies, 0, movies.length, Comparators.Date.reversed());
			break;
		}
		Movie[] res = new Movie[N<movies.length ? N : movies.length];
		for(int i=0; i<N && i<movies.length; i++) {
			res[i]=movies[i];
		}
		return res;
	}
	
	public Person[] searchMostActiveActors(Integer N) {
		HashSet<Person> actors = new HashSet<Person>();
		Movie[] movies = movieData.getData().toArray(new Movie[movieData.getData().size()]);
		for(Movie m: movies) {
			for(Person c: m.getCast()) {
				actors.add(c);
			}
		}
		Person[] actorsArray = actors.toArray(new Person[actors.size()]);
		switch(algorithm) {
		case QuickSort:
			QuickSort.getInstance().sort(actorsArray, 0, actorsArray.length-1, Comparators.NumberOfMovies.reversed());
			break;
			
		case SelectionSort:
			SelectionSort.getInstance().sort(actorsArray, 0, actorsArray.length, Comparators.NumberOfMovies.reversed());
			break;
		}
		Person[] res = new Person[N<actorsArray.length ? N : actorsArray.length];
		for(int i=0; i<N && i<actorsArray.length; i++) {
			res[i]=actorsArray[i];
		}
		return res;
	}

	//Collaborations
	
	public Person[] getDirectCollaboratorsOf(Person actor) {
		ArrayList<Person> res = new ArrayList<Person>();
		for(Collaboration c: actor.getCollaborations()) {
			if(c.getActorA() == actor)
				res.add(c.getActorB());
			else
				res.add(c.getActorA());
		}
		return res.toArray(new Person[res.size()]);
	}

	public Person[] getTeamOf(Person actor) {
		ArrayList<Person> res = new ArrayList<Person>();
		Queue<Person> queue = new LinkedList<Person>();
		HashMap<Person, Boolean> mark = new HashMap<Person, Boolean>();
		queue.add(actor);
		mark.put(actor, true);
		while(!queue.isEmpty()) {
			Person tmp = queue.remove();
			for(Collaboration collab : tmp.getCollaborations()) {
				Person adjacent = (collab.getActorA().equals(tmp)) ? collab.getActorB() : collab.getActorA();
				if(!mark.containsKey(adjacent) || mark.get(adjacent) == false) {
					queue.add(adjacent);
					mark.put(adjacent, true);
					res.add(adjacent);
				}
			}
		}
		
		return res.toArray(new Person[res.size()]);

	}
	
	public Collaboration[] maximizeCollaborationsInTheTeamOf(Person actor) {
		Person [] team = getTeamOf(actor);
		HashMap<Collaboration, Double> res = new HashMap<Collaboration, Double>();
		HashMap<Person, Double> d = new HashMap<Person, Double>();
		for(Person p: team) {
			d.putIfAbsent(p, Double.MAX_VALUE);
		}
		d.put(actor, 0.);
		Queue<Person> queue = new LinkedList<Person>();
		queue.add(actor);
		while(!queue.isEmpty()) {
			Person tmp = queue.remove();
			for(Collaboration collab : tmp.getCollaborations()) {
				Person adjacent = (collab.getActorA().equals(tmp)) ? collab.getActorB() : collab.getActorA();
				if(d.get(adjacent) == Double.MAX_VALUE) {
					queue.add(adjacent);
					d.put(adjacent, collab.getScore());
					res.put(collab, collab.getScore());
				} else if(collab.getScore() > d.get(adjacent)) {
					queue.add(adjacent);
					d.put(adjacent, collab.getScore());
					res.put(collab, collab.getScore());
				}
			}
		}

		return res.keySet().toArray(new Collaboration[res.keySet().size()]);
	}



}

