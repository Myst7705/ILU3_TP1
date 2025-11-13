package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

public class Coup {
	private Joueur joueurCourant;
	private Carte carteJouee;
	private Joueur joueurCible;

	public Coup(Joueur joueurCourant, Carte carteJouee, Joueur joueurCible) {
		this.joueurCourant = joueurCourant;
		this.carteJouee = carteJouee;
		this.joueurCible = joueurCible; // Si null, on remet la carte dans le sabot
	}
	
	public Joueur getJoueurCourant() {
		return joueurCourant;
	}
	
	public Joueur getJoueurCible() {
		return joueurCible;
	}
	
	public Carte getCarteJouee() {
		return carteJouee;
	}
	
	public boolean estValide() {
		if (carteJouee instanceof Attaque || carteJouee instanceof DebutLimite) {
			return joueurCible != null;
		}
		return joueurCible == null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coup coup) {
			return joueurCourant.equals(coup.getJoueurCourant()) && joueurCible.equals(coup.getJoueurCible()) && carteJouee.equals(coup.getCarteJouee());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 7 * (joueurCourant.hashCode() + joueurCible.hashCode() + carteJouee.hashCode());
	}
}
