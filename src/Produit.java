import java.io.Serializable;

public class Produit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3143360128045219497L;
	private String nom;
	private double prixUnitaire;

	public Produit(String nom,double prixUnitaire) {
		this.nom = nom;
		this.prixUnitaire = prixUnitaire;
	}
	public String getNom() {
		return nom;
	}
	
	public double getPrixUnitaire() {
		return prixUnitaire;
	}
	public void setPrixUnitaire(double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Override
	public String toString() {
		return "Produit [nom=" + nom + ", prixUnitaire=" + prixUnitaire + "]";
	}
	
}
