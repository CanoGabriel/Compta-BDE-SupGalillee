
public class LigneInventaire {
	@SuppressWarnings("unused")
	private String categorie;
	private Produit produit;
	private double recetteLigne;
	@SuppressWarnings("unused")
	private int qtPrecedent;
	@SuppressWarnings("unused")
	private int qtCourse;
	private int qtRestante;
	public int getQtCourse() {
		return qtCourse;
	}

	private int qtVendu;
	public LigneInventaire(
			String categorie,
			String nom,
			double prixUnitaire,
			int qtPrecedent,
			int qtCourse,
			int qtRestante) {
		this.produit = new Produit(nom, prixUnitaire);
		this.categorie = categorie;
		this.qtCourse = qtCourse;
		this.qtPrecedent = qtPrecedent;
		this.qtRestante = qtRestante;
		this.qtVendu = qtCourse + qtPrecedent - qtRestante;
		this.recetteLigne = produit.getPrixUnitaire()*this.qtVendu;
	}
	@Override
	public String toString() {
		return "LigneInventaire [categorie=" + categorie + ", produit=" + produit + ", recetteLigne=" + recetteLigne
				+ ", qtPrecedent=" + qtPrecedent + ", qtCourse=" + qtCourse + ", qtRestante=" + qtRestante
				+ ", qtVendu=" + qtVendu + "]";
	}
	public String getCategorie() {
		return categorie;
	}
	public int getQtRestante() {
		return qtRestante;
	}
	public void setQtRestante(int qtRestante) {
		this.qtRestante = qtRestante;
	}
	public Produit getProduit() {
		return produit;
	}
	public double getRecetteLigne() {
		return recetteLigne;
	}
	public int getQtVendu() {
		return qtVendu;
	}
	public void setQtPrecedent(int qtPrecedent) {
		this.qtPrecedent = qtPrecedent;
	}
	public void setQtCourse(int qtCourse) {
		this.qtCourse = qtCourse;
	}
	public int getQtPrecedent() {
		// TODO Auto-generated method stub
		return 0;
	}
}
