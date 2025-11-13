package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur main;

	public Joueur(String nom, ZoneDeJeu zoneDeJeu) {
		this.nom = nom;
		this.zoneDeJeu = zoneDeJeu;
		this.main = new MainJoueur();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur joueur) {
			return joueur.nom.equals(this.nom);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 7 * nom.hashCode();
	}
	
	@Override
	public String toString() {
		return this.nom;
	}
	
	public MainJoueur getMain() {
		return main;
	}
	
	public void donner(Carte c) {
		main.prendre(c);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) {
			return null;
		}
		Carte c = sabot.piocher();
		donner(c);
		return c;
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
	public boolean estDepotAutorise(Carte carte) {
		return zoneDeJeu.estDepotAutorise(carte);
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants){
		Set<Coup> setCoupsPoss = new HashSet<>();
		
		for (Carte carteJouee : main.getListeCarte()) {
			for (Joueur joueurCible : participants) {
				Coup coupCourant = new Coup(this, carteJouee, joueurCible);
				if (coupCourant.estValide()) {
					setCoupsPoss.add(coupCourant);
				}
			}
		}
		return setCoupsPoss;
	}
	
	public Set<Coup> coupsDefausse(){
		Set<Coup> setCoupsDef = new HashSet<>();
		
		for (Carte carteJouee : main.getListeCarte()) {
			Coup coupSabot = new Coup (this, carteJouee, null);
			setCoupsDef.add(coupSabot);
		}
		return setCoupsDef;
	}
	
	public void retirerDeLaMain(Carte carte) {
		main.jouer(carte);
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> setCoupsPoss = coupsPossibles(participants);
		
		if (setCoupsPoss.isEmpty()) {
			setCoupsPoss = coupsDefausse();
		}
		return choisirCoupAleatoire(setCoupsPoss);
	}
	
	private Coup choisirCoupAleatoire(Set<Coup> coups) {
		Random random = new Random();
		int nbCoups = coups.size();
		ArrayList<Coup> listCoups = new ArrayList<>(coups);
		
		int randInt = random.nextInt() % nbCoups;
		return listCoups.get(randInt);
	}
}
