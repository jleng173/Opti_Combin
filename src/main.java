import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class main {
	
	private static int prix;

	public static void affiche(int num) throws IOException {
		String ligne = "";
		String fichier = "Base"+num+".txt";
		BufferedReader clavier = new BufferedReader(new InputStreamReader(
				System.in));
		//String fichier = "/home/etudiant/Documents/eclipse/Opti_Combin/Data/Base" + num + ".txt";

		BufferedReader ficTexte;
		try {
			ficTexte = new BufferedReader(new FileReader(new File("Data/"+fichier)));
			if (ficTexte == null) {
				throw new FileNotFoundException("Fichier non trouvé :"+fichier);
			}
			System.out.println("Fichier " + num);
			do {
				ligne = ficTexte.readLine();
				if (ligne != null) {
					System.out.println(ligne);
				}
			} while (ligne != null);
			ficTexte.close();
			System.out.println("Fin du fichier\n");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static List<String> glouton(String listeEntreprise, String listeBases) {
		//Approche dual
		int prix_final = 0;
		List<String> Bases = new ArrayList<>();
		
		BufferedReader ficListeEntreprise;
		try {
			
			ficListeEntreprise = new BufferedReader(new FileReader(new File("Data/"+listeEntreprise)));

			int nb_entreprise = Integer.parseInt(ficListeEntreprise.readLine());
			List <String> Entreprises = new ArrayList<>();
			for (int i = 0 ; i < nb_entreprise; i++) {
				//System.out.println();
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
		prix = prix_final;
		return Bases;
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		for (int i = 1; i < 24; i++) {
//			affiche(i);
//		}
		List <String> Bases =  new ArrayList<>();
		Bases = glouton("Ent1.txt","ListeBases3.txt");
		System.out.println("Prix final :"+prix);
		for (int i = 0 ; i < Bases.size() ; i++) {
			System.out.println(Bases.get(i));
		}
	}

}
