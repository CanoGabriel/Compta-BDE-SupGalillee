package Default;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

public class FeuilleInventaire {
	private Date dateCreation;
	private int FondCaissePrecedent = 50;
	private int TotaleCaisse = 0;
	private int FondCaisseSuivant = 50;
	private double recetteTheorique;
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public int getFondCaissePrecedent() {
		return FondCaissePrecedent;
	}

	public void setFondCaissePrecedent(int fondCaissePrecedent) {
		FondCaissePrecedent = fondCaissePrecedent;
	}

	public int getTotaleCaisse() {
		return TotaleCaisse;
	}

	public void setTotaleCaisse(int totaleCaisse) {
		TotaleCaisse = totaleCaisse;
	}

	public int getFondCaisseSuivant() {
		return FondCaisseSuivant;
	}

	public void setFondCaisseSuivant(int fondCaisseSuivant) {
		FondCaisseSuivant = fondCaisseSuivant;
	}

	public double getRecetteTheorique() {
		return recetteTheorique;
	}

	public void setRecetteTheorique(double recetteTheorique) {
		this.recetteTheorique = recetteTheorique;
	}

	public double getRecetteReelle() {
		return recetteReelle;
	}

	public void setRecetteReelle(double recetteReelle) {
		this.recetteReelle = recetteReelle;
	}

	public double getBonSnac() {
		return BonSnac;
	}

	public void setBonSnac(double bonSnac) {
		BonSnac = bonSnac;
	}

	public ArrayList<LigneInventaire> getListeProd() {
		return listeProd;
	}

	public void setListeProd(ArrayList<LigneInventaire> listeProd) {
		this.listeProd = listeProd;
	}

	public ArrayList<String> getListCategorie() {
		return listCategorie;
	}

	public void setListCategorie(ArrayList<String> listCategorie) {
		this.listCategorie = listCategorie;
	}
	private double recetteReelle;
	private double BonSnac;
	
	private ArrayList<LigneInventaire> listeProd = new ArrayList<LigneInventaire>();
	private ArrayList<String> listCategorie = new ArrayList<String>();

	public FeuilleInventaire(Date date) {
		dateCreation = date;
	}
	
	public void ajouterLigne(LigneInventaire l) {
		if(!listeProd.contains(l)) {
			listeProd.add(l);
			//Mise a jour de la list des categorie relative � la feuille courante
			if(!listCategorie.contains(l.getCategorie())) {
				listCategorie.add(l.getCategorie());
				Collections.sort(listCategorie, new Comparator<String>() {

					@Override
					public int compare(String o1, String o2) {
						// TODO Auto-generated method stub
						return o1.compareTo(o2);
					}
				});
			}

			//Mise a jour de la recette th�orique
			recetteTheorique = 0;
			for(LigneInventaire i : listeProd) {
				recetteTheorique += i.getRecetteLigne();
			}
		}
	}
	

	@Override
	public String toString() {
		String r = "FeuilleInventaire [dateCreation=" + dateCreation + ", FondCaissePrecedent=" + FondCaissePrecedent
				+ ", TotaleCaisse=" + TotaleCaisse + ", FondCaisseSuivant=" + FondCaisseSuivant + ", recetteTheorique="
				+ recetteTheorique + ", recetteReelle=" + recetteReelle + "]";
		r +="\nlisteProd\n";
		for(LigneInventaire i : listeProd)
			r+= "\t"+i+"\n";
		r+="\nlistCategorie\n";
		for(String i : listCategorie)
			r+="\t"+i+"\n";
		return r;
	}
	
	public void writeXLS(String path,String name) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy | HH:mm:ss");
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Inventaire");
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		
		sheet.addMergedRegion(new CellRangeAddress(1,1,0,7));
		addCell(sheet, 0, 8, CellType.STRING, sdf.format(dateCreation), style);
		addCell(sheet, 1, 0, CellType.STRING, "Fiche d'inventaire", style);
		addCell(sheet, 2, 9, CellType.STRING, "Fond de caisse precedent", style);
		addCell(sheet, 3, 9, CellType.NUMERIC, this.FondCaissePrecedent, style);
		addCell(sheet, 4, 9, CellType.STRING, "Total caisse", style);
		addCell(sheet, 5, 9, CellType.NUMERIC, this.TotaleCaisse, style);
		addCell(sheet, 6, 9, CellType.STRING, "Fond de caisse suivant", style);
		addCell(sheet, 7, 9, CellType.NUMERIC, this.FondCaisseSuivant, style);
		addCell(sheet, 10, 9, CellType.STRING, "Recette theorique", style);
		addCell(sheet, 11, 9, CellType.NUMERIC, this.recetteTheorique, style);
		addCell(sheet, 13, 9, CellType.STRING, "Recette reelle", style);
		addCell(sheet, 14, 9, CellType.NUMERIC, this.recetteReelle, style);
		addCell(sheet, 15, 9, CellType.STRING, "Bon Snac", style);
		addCell(sheet, 16, 9, CellType.NUMERIC, this.BonSnac, style);

		addCell(sheet, 10, 10, CellType.STRING, "Difference", style);
		addCell(sheet, 11, 10, CellType.NUMERIC, recetteReelle - recetteTheorique, style);
		addCell(sheet, 13, 10, CellType.STRING, "Inventaire OK", style);
		addCell(sheet, 14, 10, CellType.STRING, "", style);
		
		int ligne = 2;
		addCell(sheet, ligne, 0, CellType.STRING, "Categorie", style);
		addCell(sheet, ligne, 1, CellType.STRING, "Produit", style);
		addCell(sheet, ligne, 2, CellType.STRING, "Qte precedent", style);
		addCell(sheet, ligne, 3, CellType.STRING, "Qte course", style);
		addCell(sheet, ligne, 4, CellType.STRING, "Qte restante", style);
		addCell(sheet, ligne, 5, CellType.STRING, "Vendus", style);
		addCell(sheet, ligne, 6, CellType.STRING, "Prix", style);
		addCell(sheet, ligne, 7, CellType.STRING, "Recette", style);
		ligne++;
		
		for(int indexCat = 0 ; indexCat < listCategorie.size();indexCat++ ) {
			addCell(sheet, ligne, 0, CellType.STRING, listCategorie.get(indexCat), style);
			ligne++;
			for(LigneInventaire i : listeProd) {
				if(i.getCategorie().equals(listCategorie.get(indexCat))) {

					addCell(sheet, ligne, 1, CellType.STRING, i.nom, style);
					addCell(sheet, ligne, 2, CellType.NUMERIC,i.qtPrecedent, style);
					addCell(sheet, ligne, 3, CellType.NUMERIC,i.qtCourse, style);
					addCell(sheet, ligne, 4, CellType.NUMERIC,i.qtCourse, style);
					addCell(sheet, ligne, 5, CellType.NUMERIC,i.getQtVendu(), style);
					addCell(sheet, ligne, 6, CellType.NUMERIC,i.prixUnitaire, style);
					addCell(sheet, ligne, 7, CellType.NUMERIC,i.getRecetteLigne(), style);
					ligne++;
				}
			}
		}

		for(int i = 0 ; i < 11 ; i++)
			sheet.autoSizeColumn(i, true);

		FileOutputStream fileOut;
		try {
			String chemin;
			if(path == null)
				chemin = name;
			else
				chemin = path+name;
			fileOut = new FileOutputStream(chemin);
			wb.write(fileOut);
			fileOut.close();  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void addCell(HSSFSheet sheet,int r,int c,CellType type, String valeur, HSSFCellStyle style) {
		HSSFRow row;
		HSSFCell cell;
		row = sheet.getRow(r);
		if(row == null)
			row = sheet.createRow(r);
		cell = row.createCell(c,type);
		cell.setCellValue(valeur);
		cell.setCellStyle(style);
	}
	private void addCell(HSSFSheet sheet,int r,int c,CellType type, double valeur, HSSFCellStyle style) {
		HSSFRow row;
		HSSFCell cell;
		row = sheet.getRow(r);
		if(row == null)
			row = sheet.createRow(r);
		cell = row.createCell(c,type);
		cell.setCellValue(valeur);
		cell.setCellStyle(style);
	}
}
