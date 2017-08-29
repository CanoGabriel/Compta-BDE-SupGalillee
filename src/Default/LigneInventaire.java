package Default;


public class LigneInventaire extends Produit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5275644157447099384L;

	public int	qtPrecedent;
	public int	qtCourse;
	public int	qtRestante;

	public LigneInventaire (String categorie,
							String nom,
							double prixUnitaire,
							int qtPrec,
							int qtCourse,
							int qtRest) {

		super(categorie, nom, prixUnitaire);
		qtPrecedent = qtPrec;
		this.qtCourse = qtCourse;
		qtRestante = qtRest;
	}

	public LigneInventaire (Produit p) {

		super(p.categorie, p.nom, p.prixUnitaire);
	}

	public int getQtVendu () {

		return qtPrecedent + qtCourse - qtRestante;
	}

	public double getRecetteLigne () {

		return prixUnitaire * this.getQtVendu();
	}

	public String toString () {

		String r = super.toString() + " ";
		r += "[ qtPrecedent=" + qtPrecedent + ", qtCourse=" + qtCourse + ", qtRestante=" + qtRestante + " ]";
		return r;
	}

}
