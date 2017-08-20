
public class Pack {
	private int quantiteProd;
	private int nombrePack;
	private double prixPack;
	private Produit produit;
	
	public Pack(int nb,int qtProd,double prix, Produit prod) {
		nombrePack = nb;
		quantiteProd = qtProd;
		prixPack = prix;
		produit = prod;
	}

	@Override
	public String toString() {
		return "Pack [quantiteProd=" + quantiteProd + ", nombrePack=" + nombrePack + ", prixPack=" + prixPack
				+ ", produit=" + produit + "]";
	}

	public int getQuantiteProd() {
		return quantiteProd;
	}

	public void setQuantiteProd(int quantiteProd) {
		this.quantiteProd = quantiteProd;
	}

	public int getNombrePack() {
		return nombrePack;
	}

	public void setNombrePack(int nombrePack) {
		this.nombrePack = nombrePack;
	}

	public double getPrixPack() {
		return prixPack;
	}

	public void setPrixPack(double prixPack) {
		this.prixPack = prixPack;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

}
