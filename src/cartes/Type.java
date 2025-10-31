package cartes;

public enum Type {
	FEU("Feu vert", "Feu rouge", "Vehicule prioritaire"), ESSENCE("Essence", "Panne d'essence", "Citerne d'essence"),
	CREVAISON("Roue de secours", "Crevaison", "Increvable"), ACCIDENT("Reparations", "Accident", "As du volant");

	private String parade;
	private String attaque;
	private String botte;

	Type(String parade, String attaque, String botte) {
		this.parade = parade;
		this.attaque = attaque;
		this.botte = botte;
	}

	public String getParade() {
		return parade;
	}

	public String getAttaque() {
		return attaque;
	}

	public String getBotte() {
		return botte;
	}
	
}
