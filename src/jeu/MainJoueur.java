package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte> {
	private List<Carte> listeCarte = new ArrayList<>();
	private int cptModif = 0;

	public MainJoueur() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Carte> getListeCarte() {
		return listeCarte;
	}
	
	void prendre(Carte c) {
		listeCarte.add(c);
	}
	
	void jouer(Carte c) {
		assert(listeCarte.contains(c));
		listeCarte.remove(c);
	}
	
	@Override
	public String toString() {
		StringBuilder main = new StringBuilder();
		for (Carte c: listeCarte) {
			main.append(c.toString() + " ");
		}
		return main.toString();
	}
	
	@Override
	public Iterator<Carte> iterator(){
		return new MainIterator();
	}
	
	private class MainIterator implements Iterator<Carte>{
		private int index = 0;
		private int cptModifAttendu = cptModif;
		private boolean removePossible = false;
		
		@Override
		public boolean hasNext() {
			return index < listeCarte.size();
		}
		
		@Override
		public Carte next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
				
			}
			removePossible = true;
			return listeCarte.get(index++);
		}
		
		@Override
		public void remove() {
			if (!removePossible || cptModif != cptModifAttendu) {
				throw new IllegalStateException();
			}
			listeCarte.remove(--index);
			removePossible = false;
			cptModif++;
			cptModifAttendu++;
		}
	}

}
