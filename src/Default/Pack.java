package Default;

import java.io.Serializable;

public class Pack implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3334835104321955905L;
	private int quantiteProd;
	private int nombrePack;
	private double prixPack;
	
	public Pack(int nb,int qtProd,double prix) {
		nombrePack = nb;
		quantiteProd = qtProd;
		prixPack = prix;
	}

	@Override
	public String toString() {
		return "Pack [quantiteProd=" + quantiteProd + ", nombrePack=" + nombrePack + ", prixPack=" + prixPack
				+ "]";
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

}
