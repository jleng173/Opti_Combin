import java.io.*;

public class main {
	

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
		String fichierListEnt = args[0];
		String fichierListBases = args[1];
		System.out.println("On cherche les entreprises du fichier "+fichierListEnt+" parmis les bases du fichier "+fichierListBases);
		Glouton G = new Glouton(fichierListEnt,fichierListBases);
		System.out.println("Prix final :"+G.getPrixFinal());
		System.out.println("Bases achetées :");
		for (int i = 0 ; i < G.getBasesG().size() ; i++) {
			System.out.println("    "+G.getBasesG().get(i));
		}
		
		System.out.println("Temps d'éxécution en milliseconde :");
		System.out.println(System.currentTimeMillis()-debut);
		
		System.out.println();
		long debutBandD = System.currentTimeMillis();
		BranchBound B = new BranchBound(fichierListEnt, fichierListBases);
		
		B.parcoursD(B.getRacine(), B.getEnsembleBdd().get(0));
		//B.parcoursG(B.getRacine(), B.getEnsembleBdd().get(0));
		while(!B.getEnsembleBdd().isEmpty()) {
			B.parcoursD(B.getRacine(), B.getEnsembleBdd().get(0));
			B.parcoursG(B.getRacine(), B.getEnsembleBdd().get(0));
			B.getEnsembleBdd().remove(0);
		}
		B.parcoursSuffixe(B.getRacine());
		int x = Integer.MAX_VALUE;
		Feuille f=null;
		for (Feuille feuille : B.getListeCout()) {
//			System.out.println("Feuille " + feuille.getListeBdd() + " " + feuille.getCoutTotal());
			if ((feuille.getCoutTotal()<x) && (feuille.getCoutTotal() !=0))
			{
				x=feuille.getCoutTotal();
				f=feuille;
			}
		}
		System.out.println("Meilleur cout " + x + f.getListeBdd());
		System.out.println();
		System.out.println("Temps d'éxécution en milliseconde :");
		System.out.println(System.currentTimeMillis()-debutBandD);
		

	}

}
