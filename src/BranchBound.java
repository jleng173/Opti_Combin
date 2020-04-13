import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BranchBound {
	ArrayList<String> EnsembleEntreprise;
	ArrayList<String> EnsembleBdd;
	ArrayList<Feuille> listeCout;
	Feuille racine;
	public int nb_trouve = 0;
	
	public BranchBound(String fichierEnt, String fichierBdd) {
		EnsembleEntreprise = initListe(fichierEnt);
		EnsembleBdd = initListe(fichierBdd);
		racine = new Feuille(EnsembleEntreprise, new ArrayList<String>(), 0, null, null);
		listeCout = new ArrayList<Feuille>();
	}
	
	public double boundMoyenne(String fichierListBase) {
		BufferedReader ficListeBases;
		try {
			ficListeBases = new BufferedReader(new FileReader(new File("Data/"+fichierListBase)));

			double nb_bases = Integer.parseInt(ficListeBases.readLine());
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
	
	public void lectureBddG(Feuille f, String fichier) throws IOException {
		String ligne = "";
		int cout=0;
		boolean trouve=false;

		BufferedReader ficTexte;
		ArrayList<String> listeBdd = new ArrayList<String>();
		for (String st : f.getListeBdd()) {
			listeBdd.add(st);
		}
		Feuille feuilleG = new Feuille(new ArrayList<String>(), f.getListeBdd(), f.getCoutTotal(), null, null);
		for (String entreprise : f.getEnsembleEntreprise()) {
			feuilleG.getEnsembleEntreprise().add(entreprise);
		}
		try {
			ficTexte = new BufferedReader(new FileReader(new File("Data/"+fichier)));
			if (ficTexte == null) {
				throw new FileNotFoundException("Fichier non trouvé :"+fichier);
			}
			cout = Integer.parseInt(ficTexte.readLine()); //Retourne le cout
			do {
				ligne = ficTexte.readLine();
				if (ligne != null) {
					for(int i=0;i<f.getEnsembleEntreprise().size();i++) {
						if(ligne.matches(f.getEnsembleEntreprise().get(i)))
						{
							//System.out.println("Trouvé");
							trouve = true;
							feuilleG.getEnsembleEntreprise().remove(f.getEnsembleEntreprise().get(i));
							
						}
					}
				}
			} while (ligne != null);
			ficTexte.close();
			//Si les données sont trouvées, on met a jour la listeBdd et le cout 
			if(trouve) {
				nb_trouve ++;
				listeBdd.add(fichier);
				feuilleG.setListeBdd(listeBdd);
				feuilleG.setCoutTotal(feuilleG.getCoutTotal()+cout);
				
//				System.out.println("Total" + feuilleG.getCoutTotal() + "  "+ fichier);
			}
//			System.out.println("Fin du fichier2 " + fichier + "\n");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		f.setGauche(feuilleG);
//		System.out.println("Resultat " + feuilleG.getCoutTotal() + feuilleG.getListeBdd());
	}
	
	public void lectureBddD(Feuille f, String fichier) throws IOException {
		ArrayList<String> listeBdd = new ArrayList<String>();
		for (String st : f.getListeBdd()) {
			listeBdd.add(st);
		}
		Feuille feuilleD = new Feuille(new ArrayList<String>(), listeBdd, f.getCoutTotal());
		for (String entreprise : f.getEnsembleEntreprise()) {
			feuilleD.getEnsembleEntreprise().add(entreprise);
		}
		f.setDroite(feuilleD);
	}

	//Initialise la ListeBdd à parcourir et la ListeEntreprise à parcourir
	public ArrayList<String> initListeBDD(String fichier) {
		ArrayList<String> listeInit = new ArrayList<String>();
		int nb_bases = 0;
		try {
			do {
			BufferedReader ficListeBases = new BufferedReader(new FileReader(new File("Data/"+fichier)));
			int prix_min = 1000;
			nb_bases = Integer.parseInt(ficListeBases.readLine());
			String nomBase_prixMin = "";	
							
			for(int i = 0 ; i < nb_bases ; i++) {
				String nomBase = ficListeBases.readLine();
				if (!listeInit.contains(nomBase)) {
					BufferedReader ficBase = new BufferedReader(new FileReader(new File("Data/"+nomBase)));
					int coutBase = Integer.parseInt(ficBase.readLine());
					if( coutBase < prix_min) {
						prix_min = coutBase;
						nomBase_prixMin = nomBase;
					}
					ficBase.close();	
				}
			}
			
			listeInit.add(nomBase_prixMin);
			ficListeBases.close();
			
			}while(listeInit.size() != nb_bases);
			
			//System.out.println("Fin du fichier\n");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return listeInit;
	}
	
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
	//		System.out.println("Fin du fichier\n");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return listeInit;
	}
	
	public void parcoursG(Feuille f, String fichier) throws IOException {
		if(f.getGauche()!=null) {
			parcoursG(f.getGauche(),fichier);
			parcoursD(f.getGauche(), fichier);
		}
		else
			if(!f.getEnsembleEntreprise().isEmpty())
				lectureBddG(f,fichier);
			else
				System.out.println("Fini " + f.getListeBdd() + " " + f.getCoutTotal());
	}
	
	public void parcoursD(Feuille f, String fichier) throws IOException {
		if(f.getDroite()!=null)
		{
			parcoursG(f.getDroite(),fichier);
			parcoursD(f.getDroite(),fichier);
		}
		else
			if(!f.getEnsembleEntreprise().isEmpty())
				lectureBddD(f,fichier);
			else
				System.out.println("Fini " + f.getListeBdd() + " " + f.getCoutTotal() + " " + f.getEnsembleEntreprise().size());
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

	public void parcourssuffixe(Feuille f) {
		if(f.getGauche()!=null)
			parcourssuffixe(f.getGauche());
		if(f.getDroite()!=null)
			parcourssuffixe(f.getDroite());
		if(f.getEnsembleEntreprise().isEmpty())
			listeCout.add(f);
	}
	
	public ArrayList<Feuille> getListeCout() {
		return listeCout;
	}
	
//	public void moyenne
}
