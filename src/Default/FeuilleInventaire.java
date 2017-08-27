package Default;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ComparisonOperator;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFConditionalFormattingRule;
import org.apache.poi.xssf.usermodel.XSSFFontFormatting;
import org.apache.poi.xssf.usermodel.XSSFPatternFormatting;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheetConditionalFormatting;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FeuilleInventaire implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2484182944097210790L;
	private Date dateCreation;
	public double FondCaissePrecedent = 50;
	private double TotaleCaisse = 0;
	public double FondCaisseSuivant = 50;
	private double recetteTheorique;
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public double getTotaleCaisse() {
		return TotaleCaisse;
	}

	public void setTotaleCaisse(double d) {
		TotaleCaisse = d;
	}

	public double getRecetteTheorique() {
		recetteTheorique = 0;
		for(LigneInventaire i : listeProd)
			recetteTheorique += i.prixUnitaire*i.getQtVendu();
		return recetteTheorique;
	}

	public void setRecetteTheorique(double recetteTheorique) {
		this.recetteTheorique = recetteTheorique;
	}

	public double getRecetteReelle() {
		recetteReelle = TotaleCaisse-FondCaissePrecedent;
		return TotaleCaisse-FondCaissePrecedent;
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

	public FeuilleInventaire(File modele){
		ObjectInputStream in = null;
		FeuilleInventaire temp = null;
		try {
			in = new ObjectInputStream(new FileInputStream(modele));
			if(in != null) {
				temp = (FeuilleInventaire) in.readObject();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		dateCreation = new Date();
		FondCaissePrecedent = temp.FondCaissePrecedent;
		FondCaisseSuivant = temp.FondCaisseSuivant;
		listeProd = temp.listeProd;
		listCategorie = temp.listCategorie;
		recetteReelle = temp.getRecetteReelle();
		recetteTheorique = temp.recetteTheorique;
		TotaleCaisse = temp.getTotaleCaisse();
	}

	public void ajouterLigne(LigneInventaire l) {
		if(!listeProd.contains(l)) {
			listeProd.add(l);
			//Mise a jour de la list des categorie relative � la feuille courante
			if(!listCategorie.contains(l.getCategorie())) {
				listCategorie.add(l.getCategorie());
				Collections.sort(listCategorie, new Comparator<String>() {

					public int compare(String o1, String o2) {
						return o1.compareTo(o2);
					}
				});
			}

			//Mise a jour de la recette theorique
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

	public String writeXLS() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy | HH:mm:ss");
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("Inventaire");
		XSSFCellStyle [] parite = new XSSFCellStyle[2];
		XSSFCellStyle style_normal = wb.createCellStyle();
		style_normal.setAlignment(HorizontalAlignment.CENTER);
		style_normal.setBorderBottom(BorderStyle.THIN);
		style_normal.setBorderRight(BorderStyle.THIN);
		style_normal.setBorderLeft(BorderStyle.THIN);
		style_normal.setBorderTop(BorderStyle.THIN);

		XSSFCellStyle style_emphase = wb.createCellStyle();
		style_emphase.setBorderBottom(BorderStyle.MEDIUM);
		style_emphase.setBorderRight(BorderStyle.MEDIUM);
		style_emphase.setBorderLeft(BorderStyle.MEDIUM);
		style_emphase.setBorderTop(BorderStyle.MEDIUM);
		style_emphase.setAlignment(HorizontalAlignment.CENTER);

		parite[0] = wb.createCellStyle();
		parite[0].setFillForegroundColor(new XSSFColor(new java.awt.Color(0, 176, 240)));
		parite[0].setFillPattern(FillPatternType.SOLID_FOREGROUND);
		parite[0].setAlignment(HorizontalAlignment.CENTER);
		parite[0].setBorderBottom(BorderStyle.THIN);
		parite[0].setBorderRight(BorderStyle.THIN);
		parite[0].setBorderLeft(BorderStyle.THIN);
		parite[0].setBorderTop(BorderStyle.THIN);

		parite[1] = wb.createCellStyle();
		parite[1].setFillForegroundColor(new XSSFColor(new java.awt.Color(146, 208, 80)));
		parite[1].setFillPattern(FillPatternType.SOLID_FOREGROUND);
		parite[1].setAlignment(HorizontalAlignment.CENTER);
		parite[1].setBorderBottom(BorderStyle.THIN);
		parite[1].setBorderRight(BorderStyle.THIN);
		parite[1].setBorderLeft(BorderStyle.THIN);
		parite[1].setBorderTop(BorderStyle.THIN);

		XSSFCellStyle style_valeur = wb.createCellStyle();
		style_valeur.setBorderBottom(BorderStyle.MEDIUM);
		style_valeur.setBorderRight(BorderStyle.MEDIUM);
		style_valeur.setBorderLeft(BorderStyle.MEDIUM);
		style_valeur.setBorderTop(BorderStyle.MEDIUM);
		style_valeur.setAlignment(HorizontalAlignment.CENTER);
		style_valeur.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 192, 0)));
		style_valeur.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		XSSFSheetConditionalFormatting conditionLayout = sheet.getSheetConditionalFormatting();

		XSSFConditionalFormattingRule font_rule = conditionLayout.createConditionalFormattingRule(ComparisonOperator.LT, "0");
		XSSFConditionalFormattingRule color_rule1 = conditionLayout.createConditionalFormattingRule(ComparisonOperator.EQUAL, "oui");
		XSSFConditionalFormattingRule color_rule2 = conditionLayout.createConditionalFormattingRule(ComparisonOperator.EQUAL, "non");

		XSSFFontFormatting  font_form = font_rule.createFontFormatting();
		XSSFPatternFormatting  color_form1 = color_rule1.createPatternFormatting();
		XSSFPatternFormatting  color_form2 = color_rule2.createPatternFormatting();

		font_form.setFontColorIndex(IndexedColors.RED.getIndex());
		color_form1.setFillForegroundColor(IndexedColors.GREEN.index);
		color_form2.setFillForegroundColor(IndexedColors.RED.index);

		CellRangeAddress[] range1 = {CellRangeAddress.valueOf("K12")};
		conditionLayout.addConditionalFormatting(range1,font_rule);

		CellRangeAddress[] range2 = {CellRangeAddress.valueOf("K15")};
		conditionLayout.addConditionalFormatting(range2,color_rule1);

		CellRangeAddress[] range3 = {CellRangeAddress.valueOf("K15")};
		conditionLayout.addConditionalFormatting(range3,color_rule2);

		int ligne = 1;
		addCell(sheet, ligne, 0, "Categorie", style_emphase);
		addCell(sheet, ligne, 1, "Produit", style_emphase);
		addCell(sheet, ligne, 2, "Qte precedent", style_emphase);
		addCell(sheet, ligne, 3, "Qte course", style_emphase);
		addCell(sheet, ligne, 4, "Qte restante", style_emphase);
		addCell(sheet, ligne, 5, "Vendus", style_emphase);
		addCell(sheet, ligne, 6, "Prix", style_emphase);
		addCell(sheet, ligne, 7, "Recette", style_emphase);
		ligne++;

		for(int indexCat = 0 ; indexCat < listCategorie.size();indexCat++ ) {
			addCell(sheet, ligne, 0, listCategorie.get(indexCat), style_normal);
			ligne++;
			for(LigneInventaire i : listeProd) {
				if(i.getCategorie().equals(listCategorie.get(indexCat))) {

					addCell(sheet, ligne, 1, i.nom, parite[ligne%2]);
					addCell(sheet, ligne, 2,i.qtPrecedent, parite[ligne%2]);
					addCell(sheet, ligne, 3,i.qtCourse, parite[ligne%2]);
					addCell(sheet, ligne, 4,i.qtRestante, parite[ligne%2]);
					addCellFormule(sheet, ligne, 5,"C"+(ligne+1)+"+D"+(ligne+1)+"-E"+(ligne+1), parite[ligne%2]);
					addCell(sheet, ligne, 6,i.prixUnitaire, parite[ligne%2]);
					addCellFormule(sheet, ligne, 7,"F"+(ligne+1)+"*G"+(ligne+1), parite[ligne%2]);
					ligne++;
				}
			}
		}        

		sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));
		addCell(sheet, 0, 8, sdf.format(dateCreation), style_valeur);
		addCell(sheet, 0, 0, "Fiche d'inventaire", style_emphase);
		addCell(sheet, 2, 9, "Fond de caisse precedent", style_emphase);
		addCell(sheet, 3, 9, this.FondCaissePrecedent, style_valeur);
		addCell(sheet, 4, 9, "Total caisse", style_emphase);
		addCell(sheet, 5, 9, this.TotaleCaisse, style_valeur);
		addCell(sheet, 6, 9, "Fond de caisse suivant", style_emphase);
		addCell(sheet, 7, 9, this.FondCaisseSuivant, style_valeur);
		addCell(sheet, 10, 9, "Recette theorique", style_emphase);

		String temp = "H4";
		for(int i = 5 ; i <= 100;i++)
			temp+="+H"+i;
		addCellFormule(sheet, 11, 9, temp, style_valeur);
		addCell(sheet, 13, 9, "Recette reelle", style_emphase);
		addCellFormule(sheet, 14, 9, "J6-J8", style_valeur);
		addCell(sheet, 15, 9, "Bon Snac", style_emphase);
		addCell(sheet, 16, 9, this.BonSnac, style_valeur);

		addCell(sheet, 10, 10, "Difference", style_emphase);
		addCellFormule(sheet, 11, 10, "J15+J17-J12", style_emphase);
		//		addCell(sheet, 13, 10, "Inventaire OK", style_emphase);
		//		addCellFormule(sheet, 14, 10, "SI(ABS(J15-J12) <= 0,025*J12;\"Oui\";\"Non\")", style_valeur);

		sheet.setForceFormulaRecalculation(true);

		for(int i = 0 ; i < 11 ; i++)
			sheet.autoSizeColumn(i, true);

		FileOutputStream fileOut;
		String chemin = "Inventaire/Feuille d'inventaire du "+(new SimpleDateFormat("dd-MM-yyyy_HHmmss").format(dateCreation))+".xlsx";
		try {
			//			if(path == null)
			//				chemin = name+".xlsx";
			//			else
			//				chemin = path+name+".xlsx";
			File dir = new File("Inventaire");
			if(!dir.exists())
				dir.mkdir();
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
				e.printStackTrace();
			}
		}
		return chemin;
	}
	private void addCell(XSSFSheet sheet,int r,int c, String valeur, XSSFCellStyle style) {
		XSSFRow row;
		XSSFCell cell;
		row = sheet.getRow(r);
		if(row == null)
			row = sheet.createRow(r);
		cell = row.createCell(c,CellType.STRING);
		cell.setCellValue(valeur);
		cell.setCellStyle(style);
	}
	private void addCell(XSSFSheet sheet,int r,int c, double valeur, XSSFCellStyle style) {
		XSSFRow row;
		XSSFCell cell;
		row = sheet.getRow(r);
		if(row == null)
			row = sheet.createRow(r);
		cell = row.createCell(c,CellType.NUMERIC);
		cell.setCellValue(valeur);
		cell.setCellStyle(style);
	}
	private void addCellFormule(XSSFSheet sheet,int r,int c, String formule, XSSFCellStyle style) {
		XSSFRow row;
		XSSFCell cell;
		row = sheet.getRow(r);
		if(row == null)
			row = sheet.createRow(r);
		cell = row.createCell(c,CellType.FORMULA);
		cell.setCellFormula(formule);
		cell.setCellStyle(style);
	}

	public LigneInventaire shearchLine(String cat,String nom) {
		for(LigneInventaire i : listeProd)
			if(cat.equals(i.categorie) && nom.equals(i.nom))
				return i;
		return null;
	}
	public double getDifference() {
		return recetteReelle+BonSnac-recetteTheorique;
	}

	public void writeModele(String name) {
		ObjectOutputStream out = null;
		File fichier = new File("Modele inventaire/"+name+".mod");
		try {
			out = new ObjectOutputStream(new FileOutputStream(fichier));
			if(out != null) {
				out.writeObject(this);
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Configuration convertToConfig(boolean auto){
		//auto --> vrai alors on rajoute tous les produits inconnu
		//auto --> faux on pose la question de l'ajout pour chaque produit inconnu
		Configuration data = new Configuration("");
		Configuration config = new Configuration();
		for(LigneInventaire i : listeProd){
			Produit temp = config.shearchProduit(i.categorie, i.nom);
			if(temp != null)
				data.addProduit(temp);
			else {
				if (!auto){
					int option = JOptionPane.showConfirmDialog(null, "Produit non repertorie\nImport \n"+temp,"Erreur !!!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if (option == JOptionPane.OK_OPTION){
						config.addProduit(temp);
						data.addProduit(temp);
						config.write();
					}
				}
				else {
					config.addProduit(temp);
					data.addProduit(temp);
					config.write();
					config = new Configuration();
				}
			}
		}

		return data;
	}

	public void readXLS(File fichier) {
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(fichier);
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XSSFSheet sheet = wb.getSheet("Inventaire");
		int lign = 2;
		XSSFRow row = sheet.getRow(lign);
		String cat = new String();
		String nom = new String();
		Configuration config = new Configuration();
		LigneInventaire l = null;
		Produit p;
//		System.out.println(row.getCell(0).getCellTypeEnum() != CellType.BLANK || row.getCell(1).getCellTypeEnum() != CellType.BLANK);
		while(row != null && (row.getCell(0) != null || row.getCell(1) != null)) {
			System.out.println(row.getCell(0)+" | "+row.getCell(1));
			if(row.getCell(0) != null) {
				cat = row.getCell(0).getStringCellValue();
				
			}
			else {
				nom = row.getCell(1).getStringCellValue();
				if((p = config.shearchProduit(cat, nom)) != null)
					l = new LigneInventaire(p);
				else {
					p = new Produit(cat, nom, row.getCell(6).getNumericCellValue());
					config.addProduit(p);
					l = new LigneInventaire(p);
				}
				l.qtCourse = (int) row.getCell(3).getNumericCellValue(); 
				l.qtPrecedent = (int) row.getCell(2).getNumericCellValue(); 
				l.qtRestante= (int) row.getCell(4).getNumericCellValue(); 
				this.ajouterLigne(l);
			}
			lign++;
			row = sheet.getRow(lign);
//			System.out.println(row);
		}
//		System.out.println(lign);
		
		row = sheet.getRow(3);
		FondCaissePrecedent = row.getCell(9).getNumericCellValue();
		row = sheet.getRow(5);
		TotaleCaisse = row.getCell(9).getNumericCellValue();
		row = sheet.getRow(7);
		FondCaisseSuivant = row.getCell(9).getNumericCellValue();
		recetteTheorique = getRecetteTheorique();
		recetteReelle = getRecetteReelle();
		row = sheet.getRow(16);
		BonSnac = row.getCell(9).getNumericCellValue();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy | HH:mm:ss");
		try {
			dateCreation = sdf.parse(sheet.getRow(0).getCell(8).getStringCellValue());
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Probleme avec les format de la date !!!", JOptionPane.ERROR_MESSAGE);
		}
		config.write();
	}
}
