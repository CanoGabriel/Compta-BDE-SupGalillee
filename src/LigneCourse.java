import java.util.ArrayList;

public class LigneCourse{
	private String categorie;
	private int qtProd = 0;
	private double prixLigne = 0;
	private ArrayList<Pack> pack = new ArrayList<Pack>();

	public int getQtProd() {
		return qtProd;
	}

	public double getPrixLigne() {
		return prixLigne;
	}

	public LigneCourse(
			String categorie,
			String nom,
			int qtPack,
			int qtProdPack,
			double prixPack) {
		this.categorie = categorie;
		Pack temp = new Pack(qtPack, qtProdPack, prixPack,new Produit(nom, prixPack/qtPack));
		ajouterPack(temp);
		this.qtProd = qtProdPack*qtPack;
		this.prixLigne = qtProd*temp.getProduit().getPrixUnitaire();
	}
	public ArrayList<Pack> getPack() {
		return pack;
	}

	private void ajouterPack(Pack p) {
		pack.add(p);
		//maj des donnée;
		qtProd += p.getQuantiteProd()*p.getNombrePack();
		prixLigne += p.getPrixPack()*p.getNombrePack();
	}

	@Override
	public String toString() {
		return "LigneCourse [categorie=" + categorie + ", qtProd=" + qtProd + ", prixLigne=" + prixLigne + ", pack="
				+ pack + "]";
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

}
