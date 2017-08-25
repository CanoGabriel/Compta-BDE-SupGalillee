package Default;
import java.util.ArrayList;

public class LigneCourse extends Produit{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8536069087642469910L;
	private ArrayList<Pack> pack = new ArrayList<Pack>();

	public LigneCourse(String categorie,String nom,double prixUnitaire) {
		super(categorie,nom,prixUnitaire);
	}
	public LigneCourse(Produit p) {
		super(p.categorie,p.nom,p.prixUnitaire);
	}

	public String toString() {
		String r = super.toString() + "\npack = \n";
		for(Pack i : pack)
			r += "\t" + i + "\n";
		return r;
	}
	
	public ArrayList<Pack> getPack() {
		return pack;
	}
	public int getPrixLigne() {
		int r = 0;
		for (Pack i : pack)
			r += i.getNombrePack()*i.getPrixPack();
		return r;
	}
	
	public int getNombreProduitLigne() {
		int r = 0;
		for(Pack i : pack)
			r += i.getQuantiteProd()*i.getNombrePack();
		return r;
	}
	
	public void addPack(Pack p) {
		pack.add(p);
		prixUnitaire = getPrixLigne()/getNombreProduitLigne();
	}
	
	
}
