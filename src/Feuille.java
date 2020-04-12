import java.util.ArrayList;

public class Feuille {	
	ArrayList<String> EnsembleEntreprise;
	ArrayList<String> ListeBdd;
	int coutTotal;
	Feuille Gauche;
	Feuille Droite;
	public int profondeur;
	
	public Feuille(ArrayList<String> ensembleEntreprise, ArrayList<String> liste, int coutTotal, Feuille gauche, Feuille droite) {
		EnsembleEntreprise = ensembleEntreprise;
		this.coutTotal = coutTotal;
		ListeBdd = liste;
		Gauche = gauche;
		Droite = droite;
	}
	
	public Feuille(ArrayList<String> ensembleEntreprise, ArrayList<String> liste, int coutTotal) {
		EnsembleEntreprise = ensembleEntreprise;
		this.coutTotal = coutTotal;
		ListeBdd = liste;
		Gauche = null;
		Droite = null;
	}

	public int getCoutTotal() {
		return coutTotal;
	}

	public void setCoutTotal(int coutTotal) {
		this.coutTotal = coutTotal;
	}

	public Feuille getGauche() {
		return Gauche;
	}

	public void setGauche(Feuille gauche) {
		Gauche = gauche;
	}

	public Feuille getDroite() {
		return Droite;
	}

	public void setDroite(Feuille droite) {
		Droite = droite;
	}

	public ArrayList<String> getEnsembleEntreprise() {
		return EnsembleEntreprise;
	}

	public void setEnsembleEntreprise(ArrayList<String> ensembleEntreprise) {
		EnsembleEntreprise = ensembleEntreprise;
	}

	public ArrayList<String> getListeBdd() {
		return ListeBdd;
	}

	public void setListeBdd(ArrayList<String> listeBdd) {
		ListeBdd = listeBdd;
	}
}
