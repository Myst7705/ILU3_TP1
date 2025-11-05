package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
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

	public int donnerLimitationVitesse() {
		if (pileLimite.isEmpty() || pileLimite.get(pileLimite.size() - 1).equals(new FinLimite())) {
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
		}
	}
	
	public boolean peutAvancer() {
		return !pileBataille.isEmpty() && pileBataille.getLast().equals(new Parade(Type.FEU));
	}
	
	public boolean estDepotFeuVertAutorise() {
		if ( pileBataille.isEmpty() || (pileBataille.getLast().equals(new Attaque(Type.FEU)))) {
			return true;
		}
		Carte lastC = pileBataille.getLast();
		if ((lastC instanceof Parade parade) && !parade.equals(new Parade(Type.FEU))) {
			return true;
		}
		return false;
	}
	
	public boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() && borne.getKm() <= donnerLimitationVitesse() && (donnerKmParcourus() + borne.getKm() <= 1000);
	}
	
	public boolean estDepotLimiteAutorise(Limite limite) {
		if (limite instanceof DebutLimite) {
			return pileLimite.isEmpty() || pileLimite.getLast().equals(new FinLimite());
		} else {
			return pileLimite.getLast().equals(new DebutLimite());
		}
	}
	
	public boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque attaque) {
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
		}
		return false;
	}
	
	
}
