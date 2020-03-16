import java.io.*;

public class main {

	public static void affiche() throws IOException {
		String ligne = "";
		String fichier = "/home/etudiant/Documents/eclipse/Opti_Combin/Data/Base1.txt";
		BufferedReader clavier = new BufferedReader(new InputStreamReader(
				System.in));

		BufferedReader ficTexte;
		try {
			ficTexte = new BufferedReader(new FileReader(new File(fichier)));
			if (ficTexte == null) {
				throw new FileNotFoundException("Fichier non trouvé: "
						+ fichier);
			}
			do {
				ligne = ficTexte.readLine();
				if (ligne != null) {
					System.out.println(ligne);
				}
			} while (ficTexte != null);
			ficTexte.close();
			System.out.println("\n");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		affiche();
	}

}
