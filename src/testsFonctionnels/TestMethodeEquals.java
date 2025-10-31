package testsFonctionnels;

import cartes.Attaque;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Parade;
import cartes.Type;

public class TestMethodeEquals {

	public TestMethodeEquals() {
	}
	
	public static void main(String[] args) {
		Carte borne1 = new Borne(25);
		Carte borne2 = new Borne(25);
		Carte borne3 = new Borne(50);
		
		Carte feuR1 = new Attaque(Type.FEU);
		Carte feuR2 = new Attaque(Type.FEU);
		
		Carte feuV = new Parade(Type.FEU);
		Carte debutL = new DebutLimite();
		Carte finL = new FinLimite();
		
		
		System.out.println("La carte 25km et 50km sont identiques ? " + borne1.equals(borne3));
		System.out.println("Deux cartes 25km sont identiques ? " + borne1.equals(borne2));
		System.out.println("Deux cartes de feu rouge sont identiques ? " + feuR1.equals(feuR2));
		System.out.println("La carte feu rouge et la carte feu vert sont identiques ? " + feuR1.equals(feuV));
		System.out.println("La carte debut lim et fin lim sont identiques? " + debutL.equals(finL));
	}

}
