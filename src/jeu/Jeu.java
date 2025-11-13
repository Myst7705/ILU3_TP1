package jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import cartes.Carte;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	private List<Carte> listeCartes;
	private Set<Joueur> joueurs;
	
	private static final int NBCARTES = 6;
	
	public Jeu(Carte[] cartes) {
		this.listeCartes = new ArrayList<>(Arrays.asList(cartes));
		this.listeCartes = GestionCartes.melanger(listeCartes);
		this.sabot = new Sabot(listeCartes.toArray(new Carte[0]));
	}
	
	public void inscrire(Joueur... tabJoueurs) {
		for (Joueur joueur : tabJoueurs) {
			joueurs.add(joueur);
		}
	}
	
	public void distribuerCartes() {
		for (int i = 0; i < NBCARTES; i++) {
			for (Joueur joueur : joueurs) {
				Carte carteADonner = sabot.piocher();
				joueur.getMain().prendre(carteADonner);
			}
		}
	}

}
