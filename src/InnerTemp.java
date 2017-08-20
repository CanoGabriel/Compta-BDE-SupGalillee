import java.io.Serializable;

public class InnerTemp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 794791669761198352L;
	@SuppressWarnings("unused")
	Produit produit;
	@SuppressWarnings("unused")
	String categorie;
	@SuppressWarnings("unused")
	public InnerTemp(Produit prod,String cat) {
		produit = prod;
		categorie = cat;
	}
	public String toString() {
		return "[ " + produit + ",\t" + categorie + " ]";
	}
}

