import java.util.ArrayList;

public class Feuille {	
	ArrayList<String> EnsembleEntreprise;
	int coutTotal;
	Feuille Gauche;
	Feuille Droite;
	
	public Feuille(ArrayList<String> ensembleEntreprise, int coutTotal, Feuille gauche, Feuille droite) {
		EnsembleEntreprise = ensembleEntreprise;
		this.coutTotal = coutTotal;
		Gauche = gauche;
		Droite = droite;
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
}
