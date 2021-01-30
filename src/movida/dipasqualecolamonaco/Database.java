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


public class Database {
	private MapImplementation selectedStructure;
	private Map<String, Person> personData;
	private Map<String, Movie> movieData;
	
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

		
		//System.out.println(personData.toString());
		
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
			
			//System.out.println(personData.toString());

			
			director = personData.search(directorName);
			
			
			line = scan.nextLine();
			String[] names = formatLine(line).split(", ");


			for (int i = 0; i < names.length; i++)  		
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
			
			//popola lista di film per ogni attore
			for (Person p : movieToAdd.getCast())
			{
				p.getMovies().add(movieToAdd);
			}
			director.getMovies().add(movieToAdd);
			
			movieData.putIfAbsent(movieToAdd.getTitle(), movieToAdd);
			
			if (scan.hasNextLine())
			{
				scan.nextLine();				
			}
		}
		
		//TEST
		System.out.println("Conclusa lettura");

		System.out.println(personData.length());
		
		scan.close();	
	}
	
	public Map<String, Person> getPersonData() {
		return personData;
	}

	public Map<String, Movie> getMovieData() {
		return movieData;
	}
	
	private String formatLine(String line) {
		int index = line.indexOf(':');
		line = line.substring(index + 1, line.length());
		return line.trim().toLowerCase();
	}
	
	public void setStructure (MapImplementation s) {
		selectedStructure = s;
	}

}
