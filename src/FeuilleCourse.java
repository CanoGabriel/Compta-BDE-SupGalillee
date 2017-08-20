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

public class FeuilleCourse {
	private Date dateCreation;
	private ArrayList<LigneCourse> listeProd = new ArrayList<LigneCourse>();
	private ArrayList<String> listCategorie = new ArrayList<String>();
	private double totalAttendu = 0;
	private double totalTicket = 0;
	public FeuilleCourse(Date date) {
		dateCreation = date;
	}

	public void ajouterLigne(LigneCourse l) {
		listeProd.add(l);
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
		totalAttendu = 0;
		for(LigneCourse i : listeProd) {
			totalAttendu += i.getPrixLigne();
		}
	}

	@Override
	public String toString() {
		String r = "FeuilleCourse [dateCreation=" + dateCreation + ", totalAttendu=" + totalAttendu + ", totalTicket=" + totalTicket + "]";
		r += "\nlistProd : \n";
		for(LigneCourse i : listeProd)
			r += "\t"+i.toString()+"\n";
		r += "\nlistCategorie : \n";
		for(String i : this.listCategorie)
			r += "\t"+i+"\n";
		return r;
	}

	public void writeXLS(String path,String name) {
		int ligne = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy | hh:mm:ss");
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("ma feuille");
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
		addCell(sheet, ligne, 0, CellType.STRING, "Fiche de course", style);
		addCell(sheet, ligne, 3, CellType.STRING, sdf.format(dateCreation), style);
		ligne++;
		
		addCell(sheet, ligne, 5, CellType.STRING, "Total theorique", style);
		addCell(sheet, ligne, 6, CellType.NUMERIC, this.totalAttendu, style);
		addCell(sheet, ligne, 7, CellType.STRING, "Total ticket", style);
		addCell(sheet, ligne, 8, CellType.NUMERIC, this.totalTicket, style);
		ligne++;
				
		addCell(sheet, ligne, 0, CellType.STRING, "Type", style);
		addCell(sheet, ligne, 1, CellType.STRING, "Categorie", style);
		addCell(sheet, ligne, 2, CellType.STRING, "Quantite", style);
		addCell(sheet, ligne, 3, CellType.STRING, "Prix total", style);
		
		ligne++;
		ligne++;
		
		
		for(int indexCat = 0 ; indexCat < listCategorie.size();indexCat++ ) {
			addCell(sheet, ligne, 0, CellType.STRING, listCategorie.get(indexCat), style);
			ligne++;
			for(LigneCourse i : listeProd) {
				if(i.getCategorie().equals(listCategorie.get(indexCat))) {

					addCell(sheet, ligne, 1, CellType.STRING, i.getPack().get(0).getProduit().getNom(), style);
					addCell(sheet, ligne, 2, CellType.NUMERIC,i.getQtProd(), style);
					addCell(sheet, ligne, 3, CellType.NUMERIC,i.getPrixLigne(), style);
					ligne++;
				}
			}
		}
		
		for(int i = 0 ; i < 9 ; i++)
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

