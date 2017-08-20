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
	public ArrayList<InnerTemp> list = new ArrayList<InnerTemp>();
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
			this.list = temp.getList();
		}catch (Exception e) {
			//			e.printStackTrace();
			this.defaultSaveFilePath = "";
			this.list = new ArrayList<InnerTemp>();
		}
		finally {
			try {
				if(in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				this.defaultSaveFilePath = "";
				this.list = new ArrayList<InnerTemp>();
			}
		}
		for(InnerTemp i : list) {
			if(!categorie.contains(i.categorie)) {
				categorie.add(i.categorie);
				Collections.sort(categorie, new Comparator<String>() {

					@Override
					public int compare(String o1, String o2) {
						// TODO Auto-generated method stub
						return o1.compareTo(o2);
					}
				});
			}
		}
	}

	public InnerTemp initInnerTemp(Produit prod,String cat) {
		return new InnerTemp(prod, cat);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setDefaultSaveFilePath(String defaultSaveFilePath) {
		this.defaultSaveFilePath = defaultSaveFilePath;
	}

	public ArrayList<InnerTemp> getList() {
		return list;
	}

	@Override
	public String toString() {
		String r = "defaultSaveFilePath = " + defaultSaveFilePath + "\n";
		for(InnerTemp i : list)
			r += i+"\n";
		return r;
	}

}
