import java.util.ArrayList;

public class BranchBound {
	ArrayList<String> EnsembleEntreprise;
	int cout;
	int majorant;
	int minorant;

	public BranchBound() {
		EnsembleEntreprise = new ArrayList<String> ();
		cout = 0;
		majorant = Integer.MAX_VALUE;
	}
	
	public void parcours(int fichier, int ct) {
//		affiche(fichier);
		minorant = ct;
		if (minorant<majorant) {
			EnsembleEntreprise.add("");
			majorant = minorant;
		}
	}
}
