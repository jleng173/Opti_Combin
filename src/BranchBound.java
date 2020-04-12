import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BranchBound {
	ArrayList<String> EnsembleEntreprise;
	ArrayList<String> EnsembleBdd;
	Feuille racine;
	int coutTotal;
	int majorant;
	int minorant;
	
	public BranchBound(String fichierEnt, String fichierBdd) {
		EnsembleEntreprise = initListe(fichierEnt);
		EnsembleBdd = initListe(fichierBdd);
		racine = new Feuille(EnsembleEntreprise, new ArrayList<String>(), 0, null, null);
		coutTotal = 0;
		majorant = Integer.MAX_VALUE;
	}
	
	public double boundMoyenne(String fichierListBase) {
		BufferedReader ficListeBases;
		try {
			ficListeBases = new BufferedReader(new FileReader(new File("Data/"+fichierListBase)));

			double nb_bases = Integer.parseInt(ficListeBases.readLine());
			String nomBase_prixMin = "";
			double prixTotalbases = 0;
							
			for(int i = 0 ; i < nb_bases ; i++) {
				String nomBase = ficListeBases.readLine();
				BufferedReader ficBase = new BufferedReader(new FileReader(new File("Data/"+nomBase)));
				int coutBase = Integer.parseInt(ficBase.readLine());
				prixTotalbases += coutBase;
				ficBase.close();
			}
			ficListeBases.close();
			
			return prixTotalbases/nb_bases;
		
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return 0.0;
	}
	
	public void lectureBdd(Feuille f, String fichier) throws IOException {
		String ligne = "";
		int cout=0;
		boolean trouve=false;
		BufferedReader ficTexte;
		ArrayList<String> listeBdd = f.getListeBdd();
		Feuille feuilleG = new Feuille(f.getEnsembleEntreprise(), f.getListeBdd(), f.getCoutTotal(), null, f);
		
		try {
			ficTexte = new BufferedReader(new FileReader(new File("Data/"+fichier)));
			if (ficTexte == null) {
				throw new FileNotFoundException("Fichier non trouvé :"+fichier);
			}
			cout = Integer.parseInt(ficTexte.readLine()); //Retourne le cout
			System.out.println("Cout" + cout);
			do {
				ligne = ficTexte.readLine();
				if (ligne != null) {
					for(int i=0;i<f.getEnsembleEntreprise().size();i++) {
						if(ligne.matches(f.getEnsembleEntreprise().get(i)))
						{
							System.out.println("Trouvé");
							trouve = true;
							feuilleG.getEnsembleEntreprise().remove(f.getEnsembleEntreprise().get(i));
						}
					}
				}
			} while (ligne != null);
			ficTexte.close();
			//Si les données sont trouvées, on met a jour la listeBdd et le cout 
			if(trouve) {
				listeBdd.add(fichier);
				feuilleG.setCoutTotal(feuilleG.getCoutTotal()+cout);
				System.out.println("Total" + feuilleG.getCoutTotal());
			}
			System.out.println("Fin du fichier2\n");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		Feuille feuilleD = new Feuille(f.getEnsembleEntreprise(), listeBdd, f.getCoutTotal());
		f.setGauche(feuilleG);
		f.setDroite(feuilleD);
		System.out.println("Resultat " + feuilleG.getCoutTotal());
	}

	//Initialise la ListeBdd à parcourir et la ListeEntreprise à parcourir
	public ArrayList<String> initListe(String fichier) {
		ArrayList<String> listeInit = new ArrayList<String>();
		String ligne = "";
		int nb = 0;
		BufferedReader ficTexte;
		try {
			ficTexte = new BufferedReader(new FileReader(new File("Data/"+fichier)));
			if (ficTexte == null) {
				throw new FileNotFoundException("Fichier non trouvé :"+fichier);
			}
			nb = Integer.parseInt(ficTexte.readLine()); //Retourne le cout
			do {
				ligne = ficTexte.readLine();
				if (ligne != null) {
					listeInit.add(ligne);
				}
			} while (ligne != null);
			ficTexte.close();
			System.out.println("Fin du fichier\n");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return listeInit;
	}
	
	public void parcoursG(Feuille f, String fichier) throws IOException {
		if(f.getGauche()!=null)
			parcoursG(f.getGauche(),fichier);
		else
			lectureBdd(f,fichier);
	}
	
	public void parcoursD(Feuille f, String fichier) throws IOException {
		if(f.getDroite()!=null)
			parcoursD(f.getDroite(),fichier);
		else
			lectureBdd(f,fichier);
	}

	public ArrayList<String> getEnsembleEntreprise() {
		return EnsembleEntreprise;
	}

	public ArrayList<String> getEnsembleBdd() {
		return EnsembleBdd;
	}

	public Feuille getRacine() {
		return racine;
	}

	public int getCoutTotal() {
		return coutTotal;
	}

	public int getMajorant() {
		return majorant;
	}
}
