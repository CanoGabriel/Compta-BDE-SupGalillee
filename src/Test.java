import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import Default.Configuration;
import Default.FenAcceuil;
import Default.FeuilleCourse;
import Default.FeuilleInventaire;
import Default.LigneCourse;
import Default.LigneInventaire;
import Default.Pack;
import Default.Produit;
import PopupComponent.PopupImportModele;

public class Test {

	public static void main (String [] args) {

		// TODO Auto-generated method stub
		// testPopup();
		testIHM();
		// testConfig();
		// testCourse();
		// testInventaire();
	}

	@SuppressWarnings ("unused")
	private static void testCourse () {

		FeuilleCourse f = new FeuilleCourse(new Date());
		LigneCourse l = new LigneCourse("cat1", "prod1", 0);
		l.addPack(new Pack(1, 1, 0.5));
		f.ajouterLigne(l);
		l = new LigneCourse("cat3", "prod2", 0);
		l.addPack(new Pack(1, 1, 0.6));
		l.addPack(new Pack(1, 1, 0.6));
		l.addPack(new Pack(1, 1, 0.6));
		f.ajouterLigne(l);

		l = new LigneCourse("cat1", "prod3", 0);
		l.addPack(new Pack(1, 1, 0.7));
		l.addPack(new Pack(1, 1, 0.7));
		l.addPack(new Pack(1, 1, 0.7));
		l.addPack(new Pack(1, 1, 0.7));
		f.ajouterLigne(l);
		// System.out.println(f);

		// f.writeXLS(null, "CourseTest");
	}

	@SuppressWarnings ("unused")
	private static void testInventaire () {

		FeuilleInventaire f = new FeuilleInventaire(new Date());
		f.ajouterLigne(new LigneInventaire("cat1", "mon produit", 0.1, 1, 0, 0));
		f.ajouterLigne(new LigneInventaire("cat5", "mon produit", 0.2, 1, 0, 0));
		f.ajouterLigne(new LigneInventaire("zcat1", "mon produit", 0.3, 1, 0, 0));
		f.ajouterLigne(new LigneInventaire("zcat1", "mon produit", 0.3, 1, 0, 0));
		f.ajouterLigne(new LigneInventaire("zcat1", "mon produit", 0.3, 1, 0, 0));
		f.ajouterLigne(new LigneInventaire("zcat1", "mon produit", 0.3, 1, 0, 0));
		f.ajouterLigne(new LigneInventaire("zcat1", "mon produit", 0.3, 1, 0, 0));
		f.ajouterLigne(new LigneInventaire("zcat1", "mon produit", 0.3, 1, 0, 0));
		System.out.println(f);
		f.writeXLS();
	}

	@SuppressWarnings ("unused")
	private static void testConfig () {

		Configuration conf = new Configuration("Mon chemin");
		for (int i = 0 ; i < 5 ; i++)
			conf.listProduit.add(new Produit("Categ " + (i % 2) + 1, "Produit " + i, i));
		System.out.println(conf + "\n\n");
		ObjectOutputStream out = null;
		File fichier = null;
		fichier = new File("serialTest.ser");
		try {
			out = new ObjectOutputStream(new FileOutputStream(fichier));
			out.writeObject(conf);
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		conf = null;
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(fichier));
			conf = (Configuration) in.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (in != null) in.close();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(conf);
		}
	}

	public static void testIHM () {

		@SuppressWarnings ("unused")
		FenAcceuil f = new FenAcceuil();
	}

	public static void testPopup () {

		PopupImportModele pop = new PopupImportModele(true);
		pop.showDialogCourse();
	}
}
