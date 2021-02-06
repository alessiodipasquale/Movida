package movida.dipasqualecolamonaco;
import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import movida.commons.MovidaFileException;
import movida.commons.Movie;
import movida.commons.Person;
import movida.commons.MapImplementation;
import movida.commons.Collaboration;


public class Database {
	private MapImplementation selectedStructure;
	private Map<String, Person> personData;
	private Map<String, Movie> movieData;
	private ArrayList<Collaboration> collaborations;
	
	public void load(final File f) throws MovidaFileException, FileNotFoundException  {
		
		Scanner scan = new Scanner(f);
		
		String line;
		String title = null;
		String directorName;
		Person director;
		int year = 0;
		int votes = 0;
		
		switch(selectedStructure) {
			case AVL: {
				personData = new AVL<String, Person>();
				movieData = new AVL<String, Movie>();
				break;
			}
			case HashConcatenamento: {
				personData = new HashConcatenamento<String, Person>(10);
				movieData = new HashConcatenamento<String, Movie>(10);
				break;
			}
			default:
				new IllegalArgumentException("Errore nella configurazione").printStackTrace();
				
		}

		this.collaborations = new ArrayList<Collaboration>();
		
		List<Person> cast;
		Person personToAdd = null;

		while (scan.hasNextLine()) 
		{
			cast = new ArrayList<Person>();

			line = scan.nextLine();		
			title = formatLine(line);

			line = scan.nextLine();
			year = Integer.parseInt(formatLine(line));
			
			line = scan.nextLine();
			directorName = formatLine(line);	

			director = new Person(directorName);
			personData.putIfAbsent(directorName, director);
			
			director = personData.search(directorName);
			
			
			line = scan.nextLine();
			String[] names = formatLine(line).split(", ");


			for (int i=0; i<names.length; i++)  		
			{			
				String name = names[i].trim().toLowerCase();
				personToAdd = new Person(name);
				personData.putIfAbsent(name, personToAdd);
				cast.add(personData.search(name)); 	
			}
			

			line = scan.nextLine();
			votes = Integer.parseInt(formatLine(line));

			Person[] castArray = cast.toArray(new Person[cast.size()]);
			Movie movieToAdd = new Movie(title, year, votes, castArray, director);
			
			for (Person p : movieToAdd.getCast())
			{
				p.getMovies().add(movieToAdd);
			}
			director.getMovies().add(movieToAdd);
			
			movieData.putIfAbsent(movieToAdd.getTitle(), movieToAdd);
			
			//Collaboartions
			for(Person person1: movieToAdd.getCast()) {
				for(Person person2: movieToAdd.getCast()) {
					if(person1 != person2) {
						createCollaboration(person1,person2,movieToAdd);
					}
				}
			}
			
			if (scan.hasNextLine())
			{
				scan.nextLine();				
			}
		}
		
		scan.close();	
	}
	
	public void createCollaboration(Person p1, Person p2, Movie m) {
		boolean exist = false;
		for(Collaboration c: p1.getCollaborations()) {
			if(c.getActorA() == p2 || c.getActorB() == p2) {
				exist = true;
				if(!c.containsMovie(m))
					c.addMovie(m);
			}
		}
		
		if(!exist) {
			Collaboration collab = new Collaboration(p1,p2);
			
			collab.addMovie(m);
			
			p1.addCollaboration(collab);
			p2.addCollaboration(collab);
			
			this.collaborations.add(collab);
		}
	}
	
	public Map<String, Person> getPersonData() {
		return personData;
	}

	public Map<String, Movie> getMovieData() {
		return movieData;
	}
	
	public ArrayList<Collaboration> getCollaborations() {
		return this.collaborations;
	};

	
	private String formatLine(String line) {
		int index = line.indexOf(':');
		line = line.substring(index + 1, line.length());
		return line.trim().toLowerCase();
	}
	
	public void setStructure (MapImplementation s) {
		selectedStructure = s;
	}

}
