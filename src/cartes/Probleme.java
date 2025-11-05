package cartes;

public abstract class Probleme extends Carte {
	private Type type;
	
	protected Probleme(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		if (obj instanceof Probleme probleme) {
//			return this.getType().getAttaque().equals(probleme.getType().getAttaque()) ||
//					this.getType().getParade().equals(probleme.getType().getParade()) ||
//					this.getType().getBotte().equals(probleme.getType().getBotte());
//		}
//		return false;
//	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Probleme probleme) {
			return this.getType() == probleme.getType() && this.getClass() == probleme.getClass();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 7 * (this.getClass().hashCode() + this.getType().hashCode());
	}
}
