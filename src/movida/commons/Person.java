/* 
 * Copyright (C) 2020 - Angelo Di Iorio
 * 
 * Progetto Movida.
 * Corso di Algoritmi e Strutture Dati
 * Laurea in Informatica, UniBO, a.a. 2019/2020
 * 
*/
package movida.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe usata per rappresentare una persona, attore o regista,
 * nell'applicazione Movida.
 * 
 * Una persona � identificata in modo univoco dal nome 
 * case-insensitive, senza spazi iniziali e finali, senza spazi doppi. 
 * 
 * Semplificazione: <code>name</code> � usato per memorizzare il nome completo (nome e cognome)
 * 
 * La classe pu� essere modicata o estesa ma deve implementare il metodo getName().
 * 
 */
public class Person {

	private String name;
	private ArrayList<Movie> movies;
	private ArrayList<Collaboration> collaborations;

	
	public Person(String name) {
		this.name = name;
		this.movies = new ArrayList<Movie>();
		this.collaborations = new ArrayList<Collaboration>();
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Movie> getMovies() {
		return this.movies;
	}
	
	public ArrayList<Collaboration> getCollaborations() {
		return this.collaborations;
	}
	
	public void addCollaboration(Collaboration c) {
		this.collaborations.add(c);
	}
	
	public void removeCollaboration(Collaboration c) {
		this.collaborations.remove(c);
	}
	
	public boolean collaborationExist(Collaboration c) {
		return this.collaborations.contains(c);
	}
	
	public int numberOfMovies() {
		return this.movies.size();
	}
	
	@Override
    public String toString() 
    { 
        return getName();
    } 
}

