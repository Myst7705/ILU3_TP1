package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte> {
	private int nbCartes;
	private Carte[] cartes;
	private int nombreOperations = 0;
	
	@Override
	public Iterator<Carte> iterator(){
		return new Iterateur();
	}
	
	public Sabot(Carte[] tabCarte) {
		this.nbCartes = tabCarte.length;
		this.cartes = new Carte[nbCartes];
		
		for (int i = 0; i < nbCartes; i++) {
			cartes[i] = tabCarte[i];
		}
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) {
		if (nbCartes >= cartes.length) {
			throw new IllegalStateException();
		}
		cartes[nbCartes] = carte;
		nombreOperations++;
		nbCartes++;
	}
	
	public Carte piocher() {
		Iterator<Carte> it = iterator();
		Carte top;
		top = it.next();
		it.remove();
		nombreOperations++;
		return top;
	}
	
	private class Iterateur implements Iterator<Carte>{
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nombreOperationsReference = nombreOperations;
		
		@Override
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}
		
		@Override
		public Carte next() {
			verificationConccurence();
			if (hasNext()) {
				nextEffectue = true;
				indiceIterateur++;
				return cartes[indiceIterateur];
			}
			throw new NoSuchElementException();
		}
		
		@Override
		public void remove() {
			verificationConccurence();
			if (!nextEffectue || nbCartes < 1) {
				throw new IllegalStateException();
			}
			for (int i = indiceIterateur - 1; i < nbCartes - 1; i++) {
				cartes[i] = cartes[i+1];
			}
			nextEffectue = false;
			indiceIterateur--;
			nbCartes--;
			nombreOperations++;
			nombreOperationsReference++;
		}
		
		private void verificationConccurence() {
			if (nombreOperations != nombreOperationsReference) {
				throw new ConcurrentModificationException();
			}
		}
	}

}
