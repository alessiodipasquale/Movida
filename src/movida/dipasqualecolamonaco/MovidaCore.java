package movida.dipasqualecolamonaco;
import movida.commons.*;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import movida.commons.IMovidaDB;
import movida.commons.MapImplementation;
import movida.commons.MovidaFileException;
import movida.commons.Movie;
import movida.commons.Person;

public class MovidaCore implements IMovidaDB, IMovidaConfig{
	Database db;
	Map<String, Movie> movieData;
	Map<String, Person> personData;
	
	SortingAlgorithm algorithm = SortingAlgorithm.QuickSort;
	MapImplementation map = MapImplementation.AVL;

	MovidaCore() {
		
	}
	
	@Override
	public void loadFromFile(File f) {
		Database db = new Database();
		db.setStructure(this.map);
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
			FileWriter save = new FileWriter(f);
			for (Map<String, Movie>.Data e : movieData.getData()) {
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
		for (Map<String, Movie>.Data e : movieData.getData()) {
			arr[i++] = e.getValue();
			//System.out.println(e.getValue().getTitle());
		}
		return arr;
	}

	@Override
	public Person[] getAllPeople() {
		Person[] arr = new Person[personData.length()];
		int i = 0;			
		for (Map<String, Person>.Data e : personData.getData()) {
			arr[i++] = e.getValue();
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



}

