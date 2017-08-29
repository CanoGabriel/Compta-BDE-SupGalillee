package Default;


import java.io.Serializable;

public class Produit implements Serializable {

	/**
	 * 
	 */
	protected static final long	serialVersionUID	= -3143360128045219497L;
	protected String			nom;
	protected String			categorie;
	protected double			prixUnitaire;

	public Produit (String categorie,
					String nom,
					double prixUnitaire) {

		this.nom = nom;
		this.setCategorie(categorie);
		this.prixUnitaire = prixUnitaire;
	}

	public String getNom () {

		return nom;
	}

	public double getPrixUnitaire () {

		return prixUnitaire;
	}

	public void setPrixUnitaire (double prixUnitaire) {

		this.prixUnitaire = prixUnitaire;
	}

	public void setNom (String nom) {

		this.nom = nom;
	}

	@Override
	public String toString () {

		return "Cattegorie : " + categorie + "\nNom : " + nom + "\nPrix : " + prixUnitaire + " E";
	}

	public String getCategorie () {

		return categorie;
	}

	public void setCategorie (String categorie) {

		this.categorie = categorie;
	}

}
