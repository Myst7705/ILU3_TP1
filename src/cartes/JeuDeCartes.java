package cartes;

public class JeuDeCartes {
	private Configuration[] configurations = new Configuration[19];
	private int nbCarte = 19;
	
	private static int NB_CARTES_TOTAL = 106;
	
	public JeuDeCartes() {
		configurations[0] = new Configuration(new Borne(25),10);
		configurations[1] = new Configuration(new Borne(50),10);
		configurations[2] = new Configuration(new Borne(75),10);
		configurations[3] = new Configuration(new Borne(100),12);
		configurations[4] = new Configuration(new Borne(200),4);
		configurations[5] = new Configuration(new Parade(Type.FEU), 14);
		configurations[6] = new Configuration(new FinLimite(),6);
		configurations[7] = new Configuration(new Parade(Type.ESSENCE),6);
		configurations[8] = new Configuration(new Parade(Type.CREVAISON),6);
		configurations[9] = new Configuration(new Parade(Type.ACCIDENT),6);
		configurations[10] = new Configuration(new Attaque(Type.FEU), 5);
		configurations[11] = new Configuration(new DebutLimite(),4);
		configurations[12] = new Configuration(new Attaque(Type.ESSENCE),3);
		configurations[13] = new Configuration(new Attaque(Type.CREVAISON),3);
		configurations[14] = new Configuration(new Attaque(Type.ACCIDENT),3);
		configurations[15] = new Configuration(new Botte(Type.FEU),1);
		configurations[16] = new Configuration(new Botte(Type.ESSENCE),1);
		configurations[17] = new Configuration(new Botte(Type.CREVAISON),1);
		configurations[18] = new Configuration(new Botte(Type.ACCIDENT),1);
	}
	
	public String affichageJeuDeCartes() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < nbCarte; i++) {
			s.append(configurations[i].getNbExemplaires() + " " + configurations[i].getCarte() + "\n");
		}
		System.out.println(s);
		return s.toString();
	}

	public Carte[] donnerCartes() {
		int tailleJeu = 0;
		for (int i = 0; i < nbCarte; i++) {
			tailleJeu += configurations[i].getNbExemplaires();
		}
		Carte[] tabCartes = new Carte[tailleJeu];
		int indiceCartes = 0;
		for (int i = 0; i < nbCarte; i++) {
			int nbCarteActuel = configurations[i].getNbExemplaires();

			for (int j = 0; j < nbCarteActuel; j++) {
				tabCartes[indiceCartes] = configurations[i].getCarte();
				indiceCartes++;
			}
		}
		return tabCartes;
	}
	
	public void addCarte(int nb, Carte c) {
		if (nbCarte >= 19) {
			System.out.println("Configuration pleine");
		}
		configurations[nbCarte] = new Configuration(c, nb);
		nbCarte++;
	}
	
	public boolean checkCount() {
		Carte[] tabCarte = donnerCartes();
		return tabCarte.length == NB_CARTES_TOTAL;
	}

	private static class Configuration {
		private int nbExemplaires;
		private Carte carte;

		private Configuration(Carte carte, int nbExemplaires) {
			this.nbExemplaires = nbExemplaires;
			this.carte = carte;
		}

		public Carte getCarte() {
			return carte;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}
	}

}
