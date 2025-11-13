package cartes;

public abstract class Limite extends Carte {
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Limite limite) {
			return this.getClass() == limite.getClass();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		if (this instanceof DebutLimite) {
			return 7;
		}
		return 14;
	}
}
