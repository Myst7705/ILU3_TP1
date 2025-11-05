package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	private List<Limite> pileLimite = new ArrayList<>();
	private List<Bataille> pileBataille = new ArrayList<>();
	private List<Borne> pileBorne = new ArrayList<>();
	private Set<Botte> botteSet = new HashSet<>();

	public int donnerLimitationVitesse() {
		if (pileLimite.isEmpty() || estPrioritaire() || pileLimite.get(pileLimite.size() - 1).equals(new FinLimite())) {
			return 200;
		}
		return 50;
	}
	
	public int donnerKmParcourus() {
		int total = 0;
		for (Borne b : pileBorne) {
			total += b.getKm();
		}
		return total;
	}
	
	public void deposer(Carte c) {
		if (c instanceof Limite limite) {
			pileLimite.add(limite);
		} else if (c instanceof Borne borne) {
			pileBorne.add(borne);
		} else if (c instanceof Bataille bataille) {
			pileBataille.add(bataille);
		} else if (c instanceof Botte botte) {
			botteSet.add(botte);
		}
	}
	
	public boolean peutAvancer() {
		if (pileBataille.isEmpty() && estPrioritaire()) {
			return true;
		}
		if (!pileBataille.isEmpty()) {
			Carte lastBataille = pileBataille.getLast();
			if (lastBataille.equals(new Parade(Type.FEU))) {
				return true;
			}
			if (lastBataille instanceof Parade && estPrioritaire()) {
				return true;
			}
			if (lastBataille.equals(new Attaque(Type.FEU)) && estPrioritaire()) {
				return true;
			}
			if (lastBataille instanceof Attaque attaque && !attaque.getType().equals(Type.FEU) && botteSet.contains(new Botte(attaque.getType())) && estPrioritaire()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean estDepotFeuVertAutorise() {
		if (estPrioritaire()) {
			return false;
		}
		if (pileBataille.isEmpty()) {
			return true;
		}
		Carte lastBataille = pileBataille.getLast();
		if (lastBataille.equals(new Attaque(Type.FEU)) || (lastBataille instanceof Parade parade && !parade.equals(new Parade(Type.FEU)))
				|| (lastBataille instanceof Attaque attaque && botteSet.contains(new Botte(attaque.getType())))) {
			return true;
		}
		return false;
	}
	
	public boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() && borne.getKm() <= donnerLimitationVitesse() && (donnerKmParcourus() + borne.getKm() <= 1000);
	}
	
	public boolean estDepotLimiteAutorise(Limite limite) {
		if (estPrioritaire()) {
			return false;
		}
		if (limite instanceof DebutLimite) {
			return pileLimite.isEmpty() || pileLimite.getLast().equals(new FinLimite());
		} else {
			return pileLimite.getLast().equals(new DebutLimite());
		}
	}
	
	public boolean estDepotBatailleAutorise(Bataille bataille) {
		if (botteSet.contains(new Botte(bataille.getType()))) {
			return false;
		}
		
		if (bataille instanceof Attaque) {
			return peutAvancer();
		}
		
		if (bataille instanceof Parade parade) {
			if (parade.equals(new Parade(Type.FEU))) {
				return estDepotFeuVertAutorise();
			} else {
				return !pileBataille.isEmpty() && pileBataille.getLast().getType().equals(parade.getType());
			}
		}
		return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		} else if (carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		} else if (carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		} else if (carte instanceof Botte) {
			return true;
		}
		return false;
	}
	
	public boolean estPrioritaire() {
		return botteSet.contains(new Botte(Type.FEU));
	}
}
