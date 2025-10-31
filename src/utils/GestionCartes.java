package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes{

	public GestionCartes() {
		// TODO Auto-generated constructor stub
	}
	
	public static <T> T extraire(List<T> tab) {
		Random r = new Random();
		
		int tailleTab = tab.size();
		int rn = r.nextInt(tailleTab);
		
		return tab.remove(rn);
	}
	
	public static <T> List<T> melanger(List<T> l){
		List<T> melange = new ArrayList<>();
		while (!l.isEmpty()) {
			melange.add(extraire(l));
		}
		return melange;
	}
	
	public static <T> boolean verifierMelange(List<T> l1, List<T> l2) {
		for (T t1 : l1) {
			if (Collections.frequency(l1, t1) != Collections.frequency(l2, t1)) {
				return false;
			}
		}
		return true;
	}
	
	public static <T> List<T> rassembler(List<T> l){
		List<T> r = new ArrayList<>();
		HashSet<T> dejaVu = new HashSet<>();
		
		for (T elem : l) {
			if (!dejaVu.contains(elem)) {
				dejaVu.add(elem);
				for (T elemToAdd : l) {
					if (elem.equals(elemToAdd)) {
						r.add(elemToAdd);
					}
				}
			}
		}
		return r;
	}
	
	public static <T> boolean verifierRassemblement(List<T> l) {
		if (l.isEmpty()) {
			return true;
		}
		
		for (ListIterator<T> it1 = l.listIterator(); it1.hasNext();) {
			T courant = it1.next();
			
			boolean consecutif = true;
			for (ListIterator<T> it2 = l.listIterator(it1.nextIndex()); it2.hasNext();) {
				T suivant = it2.next();
				if (!suivant.equals(courant)) {
					consecutif = false;
				}
				
				if (!consecutif && courant.equals(suivant)) {
					return false;
				}
			}
		}
		return true;
	}
	
	

}
