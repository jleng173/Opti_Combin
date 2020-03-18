import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BranchBound {
	ArrayList<String> EnsembleEntreprise;
	int coutTotal;
	int majorant;
	int minorant;
	
	public BranchBound() {
		EnsembleEntreprise = new ArrayList<String> ();
		coutTotal = 0;
		majorant = Integer.MAX_VALUE;
	}
	
	public int lecture(String fichier) throws IOException {
		String ligne = "";
		int cout=0;
		BufferedReader ficTexte;
		try {
			ficTexte = new BufferedReader(new FileReader(new File("Data/"+fichier)));
			if (ficTexte == null) {
				throw new FileNotFoundException("Fichier non trouvé :"+fichier);
			}
			cout = Integer.parseInt(ficTexte.readLine());
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

	
	public void parcours(String fichier, int ct) throws IOException {
		minorant = lecture(fichier);
		if (minorant<majorant) {
			EnsembleEntreprise.add("");
			majorant = minorant;
		}
	}
}
