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
		pack.add(new Pack(0, 0, 0));
	}
	public LigneCourse(Produit p) {
		super(p.categorie,p.nom,p.prixUnitaire);
		pack.add(new Pack(0, 0, 0));
	}
	public LigneCourse(Produit p,int nbPack,int nbProd, double prix) {
		super(p.categorie,p.nom,p.prixUnitaire);
		pack.add(new Pack(nbPack, nbProd, prix));
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
	public double getPrixLigne() {
		double r = 0;
		for (Pack i : pack) {
			r += i.getNombrePack()*i.getQuantiteProd()*i.getPrixPack();
			System.out.println("r = "+r+"\t");
			System.out.println("nb pack = "+i.getNombrePack()+"\t");
			System.out.println("qt prod = "+i.getQuantiteProd()+"\t");
			System.out.println("prix pack = "+i.getPrixPack()+"\n");
		}
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
