import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Configuration implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8123741264481871298L;

	private String defaultSaveFilePath;
	public ArrayList<Produit> listProduit = new ArrayList<Produit>();
	public ArrayList<String> categorie = new ArrayList<String>();
	public String getDefaultSaveFilePath() {
		return defaultSaveFilePath;
	}

	public Configuration(String path) {
		defaultSaveFilePath = path;
	}

	public Configuration() {
		ObjectInputStream in = null;
		File fichier = new File("configuration.ser");
		try {
			in = new ObjectInputStream(new FileInputStream(fichier));
			Configuration temp = (Configuration) in.readObject();
			this.defaultSaveFilePath = temp.defaultSaveFilePath;
			this.listProduit = temp.getList();
			categorie = temp.getListCategorie();
		}catch (Exception e) {
			//			e.printStackTrace();
			this.defaultSaveFilePath = "";
			this.listProduit = new ArrayList<Produit>();
		}
		finally {
			try {
				if(in != null)
					in.close();
			} catch (IOException e) {
				this.defaultSaveFilePath = "";
				this.listProduit = new ArrayList<Produit>();
			}
		}

	}

	public ArrayList<String> getListCategorie(){
		ArrayList<String> l = new ArrayList<String>();
		for(Produit i : listProduit) {
			if(!l.contains(i.getCategorie())) {
				l.add(i.getCategorie());
				Collections.sort(l, new Comparator<String>() {

					@Override
					public int compare(String o1, String o2) {
						return o1.compareTo(o2);
					}
				});
			}
		}
		return l;
	}

	public ArrayList<Produit> getProduitByCategorie(String cat){
		ArrayList<Produit> l = new ArrayList<Produit>();
		for(Produit i : this.listProduit)
			if(i.getCategorie().equals(cat)) {
				l.add(i);
				Collections.sort(l, new Comparator<Produit>() {

					@Override
					public int compare(Produit o1, Produit o2) {
						return o1.getNom().compareTo(o2.getNom());
					}
				});
			}
		return l;
	}
	public void write() {
		ObjectOutputStream out = null;
		File fichier = new File("configuration.ser");
		try {
			out = new ObjectOutputStream(new FileOutputStream(fichier));
			if(out != null) {
				out.writeObject(this);
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addProduit(Produit p) {
		if(!listProduit.contains(p)) 
			listProduit.add(p);
		if(!categorie.contains(p.categorie))
			categorie.add(p.categorie);
	}

	public void setDefaultSaveFilePath(String defaultSaveFilePath) {
		this.defaultSaveFilePath = defaultSaveFilePath;
	}

	public ArrayList<Produit> getList() {
		return listProduit;
	}

	@Override
	public String toString() {
		String r = "defaultSaveFilePath = " + defaultSaveFilePath + "\n";
		for(Produit i : listProduit)
			r += i+"\n";
		return r;
	}

}
