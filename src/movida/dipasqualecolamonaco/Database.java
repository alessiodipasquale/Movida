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
	private MapImplementation selectedMap;
	private Map<String, Person> personMap;
	private Map<String, Movie> movieMap;
	
	public void load(final File f) throws MovidaFileException, FileNotFoundException  {
		
		Scanner scan = new Scanner(f);
		
		String line;
		String title = null;
		String directorName;
		Person director;
		int year = 0;
		int votes = 0;
		
		/*switch(selectedMap) {
			case AVL: {
				personMap = new AVL<String, Person>();
				movieMap = new AVL<String, Movie>();
				break;
			}
			case ArrayOrdinato: {
				personMap = new ArrayOrdinato<String, Person>();
				movieMap = new ArrayOrdinato<String, Movie>();
				break;
			}
			default:
				new IllegalArgumentException("Errore nella configurazione").printStackTrace();
				
		}*/
		
		List<Person> cast;
		Person personToAdd = null;

		while (scan.hasNextLine()) 
		{
			cast = new ArrayList<Person>();

			line = scan.nextLine();		
			System.out.println(line);
			title = formatLine(line);
			
			line = scan.nextLine();
			year = Integer.parseInt(formatLine(line));
			
			line = scan.nextLine();
			directorName = formatLine(line);	
			director = new Person(directorName);
			personMap.putIfAbsent(directorName, director);
			director = personMap.search(directorName);
			
			line = scan.nextLine();
			String[] names = formatLine(line).split(",");

			for (int i = 0; i < names.length; i++)  		
			{
				String name = names[i].trim().toLowerCase();
				personToAdd = new Person(name);
				personMap.putIfAbsent(name, personToAdd);
				cast.add(personMap.search(name)); 	
			}

			line = scan.nextLine();
			votes = Integer.parseInt(formatLine(line));

			Person[] castArray = cast.toArray(new Person[cast.size()]);
			Movie movieToAdd = new Movie(title, year, votes, castArray, director);
			
			//popola lista di film per ogni attore
			/*for (Person p : movieToAdd.getCast())
			{
				p.getMovies().add(movieToAdd);
			}*/
			
			movieMap.putIfAbsent(movieToAdd.getTitle(), movieToAdd);
			
			if (scan.hasNextLine())
			{
				scan.nextLine();				
			}
		}
		scan.close();	
	

	

	}
	
	private String formatLine(String line) {
		int index = line.indexOf(':');
		line = line.substring(index + 1, line.length());
		return line.trim().toLowerCase();
	}

}
