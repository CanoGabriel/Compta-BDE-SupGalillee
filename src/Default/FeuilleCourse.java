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
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FeuilleCourse implements Serializable {

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 372538109112150394L;
	private Date					dateCreation;
	private ArrayList<LigneCourse>	listeProd			= new ArrayList<LigneCourse>();
	private ArrayList<String>		listCategorie		= new ArrayList<String>();
	private double					totalTicket			= 0;
	private double					totalAttendu		= 0;

	public FeuilleCourse (Date date) {

		dateCreation = date;
	}

	public FeuilleCourse (File modele) {

		ObjectInputStream in = null;
		FeuilleCourse temp = null;
		try {
			in = new ObjectInputStream(new FileInputStream(modele));
			if (in != null) {
				temp = (FeuilleCourse) in.readObject();
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dateCreation = new Date();
		listeProd = temp.listeProd;
		listCategorie = temp.listCategorie;
		totalAttendu = temp.totalAttendu;
		totalTicket = temp.totalTicket;
	}

	public Configuration convertToConfig (boolean auto) {

		// auto --> vrai alors on rajoute tous les produits inconnu
		// auto --> faux on pose la question de l'ajout pour chaque produit
		// inconnu
		Configuration data = new Configuration("");
		Configuration config = new Configuration();
		for (LigneCourse i : listeProd) {
			Produit temp = config.shearchProduit(i.categorie, i.nom);
			if (temp != null)
				data.addProduit(temp);
			else {
				if ( auto) {
					int option = JOptionPane.showConfirmDialog(null, "Produit non repertorie\nImport ", "Erreur !!!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (option == JOptionPane.OK_OPTION) {
						config.addProduit(i);
						data.addProduit(i);
					}
				}
				else {
					System.out.println("debug : " + i);
					config.addProduit(i);
					data.addProduit(i);
//					config.write();
				}
			}
		}
		config.write();
		FenAcceuil.data_config = config;
		return data;
	}

	public void setTotalTicket (double totalTicket) {

		this.totalTicket = totalTicket;
	}

	public void ajouterLigne (LigneCourse l) {

		if (shearchLine(l.categorie, l.nom) == null) listeProd.add(l);
		if ( !listCategorie.contains(l.getCategorie())) {
			listCategorie.add(l.getCategorie());
			Collections.sort(listCategorie, new Comparator<String>() {

				@Override
				public int compare (String o1, String o2) {

					// TODO Auto-generated method stub
					return o1.compareTo(o2);
				}
			});
		}
		totalAttendu = 0;
		for (LigneCourse i : listeProd) {
			totalAttendu += i.getPrixLigne();
		}
	}

	public void supprimerLigne (LigneCourse l) {

		if (listeProd.remove(l)) {
			listCategorie.clear();
			for (LigneCourse i : listeProd) {
				if ( !listCategorie.contains(i.getCategorie())) {
					listCategorie.add(i.getCategorie());
					Collections.sort(listCategorie, new Comparator<String>() {

						@Override
						public int compare (String o1, String o2) {

							// TODO Auto-generated method stub
							return o1.compareTo(o2);
						}
					});
				}
			}
			totalAttendu = 0;
			for (LigneCourse i : listeProd) {
				totalAttendu += i.getPrixLigne();
			}
		}
	}

	public LigneCourse shearchLine (String cat, String nom) {

		for (LigneCourse i : listeProd)
			if (cat.equals(i.categorie) && nom.equals(i.nom)) return i;
		return null;
	}

	@Override
	public String toString () {

		String r = "FeuilleCourse [dateCreation=" + dateCreation + ", totalAttendu=" + totalAttendu + ", totalTicket=" + totalTicket + "]";
		r += "\nlistProd : \n";
		for (LigneCourse i : listeProd)
			r += "\t" + i.toString() + "\n";
		r += "\nlistCategorie : \n";
		for (String i : this.listCategorie)
			r += "\t" + i + "\n";
		return r;
	}

	public String writeXLS () {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy | HH:mm:ss");
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheetSimple = wb.createSheet("simple");
		XSSFSheet sheet = wb.createSheet("detail");
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

		XSSFCellStyle [] parite = new XSSFCellStyle [2];
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
		style_valeur.setFillForegroundColor(new XSSFColor(new java.awt.Color(204, 192, 217)));
		style_valeur.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		addCell(sheet, 0, 0, "Fiche de course", style_normal);
		addCell(sheet, 0, 4, sdf.format(dateCreation), style_valeur);

		sheetSimple.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		addCell(sheetSimple, 0, 0, "Fiche de course", style_normal);
		addCell(sheetSimple, 0, 4, sdf.format(dateCreation), style_valeur);

		String temp = "D4";
		for (int i = 5 ; i <= 100 ; i++)
			temp += "+D" + i;
		addCell(sheet, 0, 5, "Total theorique", style_emphase);
		addCell(sheetSimple, 0, 5, "Total theorique", style_emphase);
		addCellFormule(sheet, 0, 6, temp, style_valeur);
		addCellFormule(sheetSimple, 0, 6, "'detail'!G1", style_valeur);
		addCell(sheet, 1, 5, "Total ticket", style_emphase);
		addCell(sheetSimple, 1, 5, "Total ticket", style_emphase);
		addCellFormule(sheetSimple, 1, 6, "'detail'!G2", style_emphase);
		addCell(sheet, 1, 6, this.totalTicket, style_valeur);

		int ligne = 1;
		addCell(sheet, ligne, 0, "Type", style_emphase);
		addCell(sheet, ligne, 1, "Nom", style_emphase);
		addCell(sheet, ligne, 2, "Quantite", style_emphase);
		addCell(sheet, ligne, 3, "Prix total", style_emphase);

		addCell(sheetSimple, ligne, 0, "Type", style_emphase);
		addCell(sheetSimple, ligne, 1, "Nom", style_emphase);
		addCell(sheetSimple, ligne, 2, "Quantite", style_emphase);
		addCell(sheetSimple, ligne, 3, "Prix total", style_emphase);

		ligne++;
		int ligne2 = ligne;

		ArrayList<String> ref_total = new ArrayList<String>();
		ArrayList<String> ref_qt = new ArrayList<String>();
		for (int indexCat = 0 ; indexCat < listCategorie.size() ; indexCat++) {
			addCell(sheet, ligne, 0, listCategorie.get(indexCat), style_normal);
			ligne++;
			for (LigneCourse i : listeProd) {
				if (i.getCategorie().equals(listCategorie.get(indexCat))) {
					addCell(sheet, ligne, 1, i.nom, parite[ligne % 2]);
					String temp1 = "F" + (ligne + 3);
					String temp2 = "G" + (ligne + 3) + "*E" + (ligne + 3);
					for (int k = ligne + 4 ; k <= ligne + 2 + i.getPack().size() ; k++) {
						temp1 += "+F" + k;
						temp2 += "+G" + k + "*E" + k;
					}
					addCellFormule(sheet, ligne, 2, temp1, parite[ligne % 2]);
					ref_qt.add("'detail'!C" + (ligne + 1));
					addCellFormule(sheet, ligne, 3, temp2, parite[ligne % 2]);
					ref_total.add("'detail'!D" + (ligne + 1));
					ligne++;
					addCell(sheet, ligne - 1, 4, "Pack :", style_emphase);
					addCell(sheet, ligne, 4, "Nombre de Pack", style_emphase);
					addCell(sheet, ligne, 5, "Qt de produit/Pack", style_emphase);
					addCell(sheet, ligne, 6, "Prix/Pack", style_emphase);
					ligne++;
					for (Pack j : i.getPack()) {
						addCell(sheet, ligne, 4, j.getNombrePack(), parite[ligne % 2]);
						addCell(sheet, ligne, 5, j.getQuantiteProd(), parite[ligne % 2]);
						addCell(sheet, ligne, 6, j.getPrixPack(), parite[ligne % 2]);
						ligne++;
					}
				}
			}
		}
		for (int indexCat = 0 ; indexCat < listCategorie.size() ; indexCat++) {
			addCell(sheetSimple, ligne2, 0, listCategorie.get(indexCat), style_normal);
			ligne2++;
			for (LigneCourse i : listeProd) {
				if (i.getCategorie().equals(listCategorie.get(indexCat))) {
					addCell(sheetSimple, ligne2, 1, i.nom, parite[ligne2 % 2]);
					addCellFormule(sheetSimple, ligne2, 2, ref_qt.get(0), parite[ligne2 % 2]);
					ref_qt.remove(0);
					addCellFormule(sheetSimple, ligne2, 3, ref_total.get(0), parite[ligne2 % 2]);
					ref_total.remove(0);
					ligne2++;
				}
			}
		}

		for (int i = 0 ; i < 9 ; i++) {
			sheet.autoSizeColumn(i, true);
			if (i < 6) sheetSimple.autoSizeColumn(i, true);
		}
		FileOutputStream fileOut;
		String chemin = "Course/Feuille de course du " + (new SimpleDateFormat("dd-MM-yyyy_HHmmss").format(dateCreation)) + ".xlsx";
		try {
			File dir = new File("Course");
			if ( !dir.exists()) dir.mkdir();
			fileOut = new FileOutputStream(chemin);
			wb.write(fileOut);
			fileOut.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				wb.close();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return chemin;
	}

	public Date getDateCreation () {

		return dateCreation;
	}

	public double getTotalAttendu () {

		totalAttendu = 0;
		for (LigneCourse i : listeProd)
			for (Pack j : i.getPack())
				totalAttendu += j.getPrixPack() * j.getNombrePack();
		return totalAttendu;
	}

	public double getTotalTicket () {

		return totalTicket;
	}

	private void addCell (XSSFSheet sheet, int r, int c, String valeur, XSSFCellStyle style) {

		XSSFRow row;
		XSSFCell cell;
		row = sheet.getRow(r);
		if (row == null) row = sheet.createRow(r);
		cell = row.createCell(c, CellType.STRING);
		cell.setCellValue(valeur);
		cell.setCellStyle(style);
	}

	private void addCell (XSSFSheet sheet, int r, int c, double valeur, XSSFCellStyle style) {

		XSSFRow row;
		XSSFCell cell;
		row = sheet.getRow(r);
		if (row == null) row = sheet.createRow(r);
		cell = row.createCell(c, CellType.NUMERIC);
		cell.setCellValue(valeur);
		cell.setCellStyle(style);
	}

	private void addCellFormule (XSSFSheet sheet, int r, int c, String formule, XSSFCellStyle style) {

		XSSFRow row;
		XSSFCell cell;
		row = sheet.getRow(r);
		if (row == null) row = sheet.createRow(r);
		cell = row.createCell(c, CellType.FORMULA);
		cell.setCellFormula(formule);
		cell.setCellStyle(style);
	}

	public void writeModele (String name) {

		ObjectOutputStream out = null;
		File fichier = new File("Modele course/" + name + ".mod");
		try {
			out = new ObjectOutputStream(new FileOutputStream(fichier));
			if (out != null) {
				out.writeObject(this);
				out.flush();
				out.close();
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readXLS (File fichier) {

		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(fichier);
		}
		catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XSSFSheet sheet = wb.getSheet("detail");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy | HH:mm:ss");
		try {
			dateCreation = sdf.parse(sheet.getRow(0).getCell(4).getStringCellValue());
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		totalTicket = sheet.getRow(1).getCell(6).getNumericCellValue();
		XSSFRow row = sheet.getRow(2);
		int lign = 2;
		String cat = null;
		String nom = null;
		LigneCourse l = null;
		while (row != null && (row.getCell(0) != null || row.getCell(1) != null || row.getCell(6) != null)) {
			if (row.getCell(0) != null)
				cat = row.getCell(0).getStringCellValue();
			else if (row.getCell(1) != null) {
				nom = row.getCell(1).getStringCellValue();
				lign++;
			}
			else {
				l = new LigneCourse(new Produit(cat, nom, 0), (int) row.getCell(4).getNumericCellValue(), (int) row.getCell(5).getNumericCellValue(), row.getCell(6).getNumericCellValue());
				while (sheet.getRow(lign + 1) != null && sheet.getRow(lign + 1).getCell(6) != null) {
					lign++;
					row = sheet.getRow(lign);
					l.addPack(new Pack((int) row.getCell(4).getNumericCellValue(), (int) row.getCell(5).getNumericCellValue(), row.getCell(6).getNumericCellValue()));
				}
				ajouterLigne(l);
			}
			lign++;
			row = sheet.getRow(lign);
		}
	}
}
