import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Glouton {
	
	private int PrixFinal;
	private List<String> BasesG = new ArrayList<>();
	
	public Glouton(String listeEntreprise, String listeBases) {
		glouton(listeEntreprise,listeBases);
	}
	
	public void glouton(String listeEntreprise, String listeBases) {
		//Approche duale
		//On choisit à chaque fois la base au coup le plus faible
		int prix_final = 0;
		List<String> Bases = new ArrayList<>();
		
		BufferedReader ficListeEntreprise;
		try {
			
			ficListeEntreprise = new BufferedReader(new FileReader(new File("Data/"+listeEntreprise)));

			int nb_entreprise = Integer.parseInt(ficListeEntreprise.readLine());
			List <String> Entreprises = new ArrayList<>();
			for (int i = 0 ; i < nb_entreprise; i++) {
				Entreprises.add(ficListeEntreprise.readLine().toString());
			}
			ficListeEntreprise.close();

			do {
				BufferedReader ficListeBases = new BufferedReader(new FileReader(new File("Data/"+listeBases)));
				int nb_bases = Integer.parseInt(ficListeBases.readLine());
				int prix_min = 1000;
				String nomBase_prixMin = "";	
								
				for(int i = 0 ; i < nb_bases ; i++) {
					String nomBase = ficListeBases.readLine();
					if (!Bases.contains(nomBase)) {
						BufferedReader ficBase = new BufferedReader(new FileReader(new File("Data/"+nomBase)));
						int coutBase = Integer.parseInt(ficBase.readLine());
						//Heuristique -> choix de la base la moins chère
						if( coutBase < prix_min) {
							prix_min = coutBase;
							nomBase_prixMin = nomBase;
						}
						ficBase.close();
					}
					
				}
				ficListeBases.close();
				
				prix_final += prix_min ;
				Bases.add(nomBase_prixMin);
				BufferedReader ficBase = new BufferedReader(new FileReader(new File("Data/"+nomBase_prixMin)));
				int coutBase = Integer.parseInt(ficBase.readLine());
				String ligne;
				do {
					ligne = ficBase.readLine();
					Entreprises.remove(ligne);
				} while (ligne != null);
				ficBase.close();
				
			}while(!Entreprises.isEmpty());
		
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.PrixFinal = prix_final;
		this.BasesG = Bases;
	}
	

	public int getPrixFinal() {
		return PrixFinal;
	}

	public List<String> getBasesG() {
		return BasesG;
	}



}
