package movida.dipasqualecolamonaco;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import movida.commons.IMovidaDB;
import movida.commons.MapImplementation;
import movida.commons.MovidaFileException;
import movida.commons.Movie;
import movida.commons.Person;

public class MovidaCore implements IMovidaDB{
	Database db;
	Map<String, Movie> movieData;
	Map<String, Person> personData;
	
	MovidaCore() {
		
	}
	/*public static void main(String[] args) throws MovidaFileException, FileNotFoundException {
		//loadFromFile(new File("/Users/alessiodipasquale/Projects/Movida/src/movida/dipasqualecolamonaco/test.txt"));
	}*/
	
	
	@Override
	public void loadFromFile(File f) {
		Database db = new Database();
		db.setStructure(MapImplementation.HashConcatenamento);
		try {
			db.load(f);
		}catch (Exception err) {
			err.printStackTrace();
			return;
		}
		movieData = db.getMovieData();
		personData = db.getPersonData();
		//AGGIUNGI GRAFO
		return;
	}
	
	@Override
	public void saveToFile(File f) {

		try {
			System.out.println(movieData.toString()+'\n');
			FileWriter save = new FileWriter(f);
			for (Map<String, Movie>.Entry e : movieData.entrySet()) {
				System.out.println(e.value.getTitle());
				save.append("Title:" + "\t" + e.value.getTitle() + "\n");

				save.append("Year:" + "\t" + e.value.getYear() + "\n");

				save.append("Director:" + "\t" + e.value.getDirector().getName() + "\n");

				Person cast[] = e.value.getCast();
				save.append("Cast:" + "\t");
				for (int i = 0; i < cast.length; i++) {
					if (i == cast.length - 1)
						save.append(cast[i].getName() + "\n"); // l'ultimo elemento non ha la virgola
					else
						save.append(cast[i].getName() + "," + "\t");
				}

				save.append("Votes" + "\t" + e.value.getVotes() + "\n");
				save.append("\n");

			}

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
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Movie getMovieByTitle(String title) {
		return movieData.search(title.trim().toLowerCase());
	}

	@Override
	public Person getPersonByName(String name) {
		return personData.search(name.trim().toLowerCase());

	}
	
	@Override
	public Movie[] getAllMovies() {
		Movie[] arr = new Movie[movieData.length()];
		int i = 0;
		for (Map<String, Movie>.Entry e : movieData.entrySet()) {
			arr[i++] = e.getValue();
		}
		return arr;
	}

	@Override
	public Person[] getAllPeople() {
		Person[] arr = new Person[personData.length()];
		int i = 0;
		for (Map<String, Person>.Entry e : personData.entrySet()) {
			arr[i++] = e.getValue();
		}
		return arr;
	}


}

