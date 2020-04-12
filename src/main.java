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
	
	
	public static void main(String[] args) throws IOException {
		long debut = System.currentTimeMillis();
		// TODO Auto-generated method stub
		String fichierListEnt = "Ent3.txt";
		String fichierListBases = "ListeBases3.txt";
		System.out.println("On cherche les entreprises du fichier "+fichierListEnt+" parmis les bases du fichier "+fichierListBases);
		Glouton G = new Glouton(fichierListEnt,fichierListBases);
		System.out.println("Prix final :"+G.getPrixFinal());
		System.out.println("Bases achetées :");
		for (int i = 0 ; i < G.getBasesG().size() ; i++) {
			System.out.println("    "+G.getBasesG().get(i));
		}
		
		System.out.println("Temps d'éxécution en milliseconde :");
		System.out.println(System.currentTimeMillis()-debut);
		
		
		BranchBound B = new BranchBound("Ent2.txt", "ListeBases1.txt");
//		while(!B.getEnsembleBdd().isEmpty()) {
			B.parcoursD(B.getRacine(), B.getEnsembleBdd().get(0),B.getRacine().getListeBdd());
			B.parcoursG(B.getRacine(), B.getEnsembleBdd().get(0),B.getRacine().getListeBdd());
//		}
//		System.out.println(B.getRacine().getDroite().getCoutTotal());
//		System.out.println(B.getRacine().getGauche().getCoutTotal());
		

	}

}
