/*
 * Cette class représente la fenetre qui acceuilera toutes les IHMs 
 */
package Default;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import IHMComponent.Boutton;
import IHMComponent.IHMAcceuil;
import IHMComponent.IHMConfigModifPanel;
import IHMComponent.IHMConfigOverview;
import IHMComponent.IHMCourse;
import IHMComponent.IHMInventaire;
import PopupComponent.PopupAjoutProduit;
import PopupComponent.PopupAjouterPack;
import PopupComponent.PopupExportModele;
import PopupComponent.PopupImportModele;
import PopupComponent.PopupOuvrir;
import PopupComponent.PopupProduit;

public class FenAcceuil extends JFrame implements ActionListener{
	private static final long serialVersionUID = 4358673801877559732L;
	private Configuration data_config = null;

	private IHMAcceuil acceuil = new IHMAcceuil(this);
	private IHMConfigOverview config = new IHMConfigOverview(this);
	private IHMConfigModifPanel modif_config = new IHMConfigModifPanel(this);
	private IHMCourse course = new IHMCourse(this);
	private IHMInventaire inventaire = new IHMInventaire(this);

	public FenAcceuil() {
		data_config = new Configuration();
		this.setTitle("Comptabilite BDE 2017");
		this.setResizable(true);
		this.setSize(660, 550);
		this.setMinimumSize(new Dimension(660, 550));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setContentPane(acceuil);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof Boutton)
			System.out.println(((Boutton)e.getSource()).getName());
		else if (e.getSource() instanceof JMenuItem){
			System.out.println(((JMenuItem)e.getSource()).getName());
		}
		else
			System.out.println(e.getSource().toString());
		
		
		if(e.getSource() == config.getBoutton(IHMConfigOverview.BTN_ACCEUIL)
				|| e.getSource() == modif_config.getBoutton(IHMConfigModifPanel.BTN_ACCEUIL)
				|| e.getSource() == course.getBoutton(IHMCourse.BTN_ACCEUIL)
				|| e.getSource() == inventaire.getBoutton(IHMInventaire.BTN_ACCEUIL)) {
			this.setContentPane(acceuil);
			data_config.setDefaultSaveFilePath(config.getTxtf_path().getText());
			data_config.write();
			actualiser();
		}
		else if(e.getSource() == acceuil.getBoutton(IHMAcceuil.BTN_CONFIG)) {
			this.setContentPane(config);
			config.getTxtf_path().setText(data_config.getDefaultSaveFilePath());
			config.buildArbre(data_config);
			actualiser();
		}
		else if(e.getSource() == config.getBoutton(IHMConfigOverview.BTN_PATHOK)) {
			data_config.setDefaultSaveFilePath(config.getTxtf_path().getText());
		}
		else if (e.getSource() == config.getBoutton(IHMConfigOverview.BTN_MODIFIER)) {
			this.setContentPane(modif_config);
			modif_config.buildArbre(data_config);
			actualiser();
		}
		else if (e.getSource() == modif_config.getBoutton(IHMConfigModifPanel.BTN_VALIDER)) {
			this.setContentPane(config);
			config.buildArbre(data_config);
			data_config.write();
			actualiser();
		}
		else if (e.getSource() == inventaire.getBoutton(IHMInventaire.BTN_VALIDER)) {
			try {
				inventaire.getFtxtf_qtCourse().commitEdit();
				inventaire.curLine.qtCourse=Math.abs(((Long)inventaire.getFtxtf_qtCourse().getValue()).intValue());
				inventaire.getFtxtf_qtPrecedente().commitEdit();
				inventaire.curLine.qtPrecedent=Math.abs(((Long)inventaire.getFtxtf_qtPrecedente().getValue()).intValue());
				inventaire.getFtxtf_qtRestante().commitEdit();
				inventaire.curLine.qtRestante=Math.abs(((Long)inventaire.getFtxtf_qtRestante().getValue()).intValue());
				inventaire.getLab_qtVendu().setText(""+inventaire.curLine.getQtVendu());
				inventaire.getFtxtf_fondPrecedent().commitEdit();
				if (inventaire.getFtxtf_fondPrecedent().getValue() instanceof Long)
					inventaire.data.FondCaissePrecedent = Math.abs(((Long)inventaire.getFtxtf_fondPrecedent().getValue()).doubleValue());
				else
					inventaire.data.FondCaissePrecedent = Math.abs(((Double)inventaire.getFtxtf_fondPrecedent().getValue()).doubleValue());
				inventaire.getFtxtf_fondSuivant().commitEdit();
				if(inventaire.getFtxtf_fondSuivant().getValue() instanceof Long)
					inventaire.data.FondCaisseSuivant = Math.abs(((Long)inventaire.getFtxtf_fondSuivant().getValue()).doubleValue());
				else
					inventaire.data.FondCaisseSuivant = Math.abs(((Double)inventaire.getFtxtf_fondSuivant().getValue()).doubleValue());
				inventaire.getFtxtf_totalCaisse().commitEdit();
				
				if(inventaire.getFtxtf_totalCaisse().getValue() instanceof Long)
					inventaire.data.setTotaleCaisse(Math.abs(((Long)inventaire.getFtxtf_totalCaisse().getValue()).doubleValue()));
				else
					inventaire.data.setTotaleCaisse(Math.abs(((Double)inventaire.getFtxtf_totalCaisse().getValue()).doubleValue()));
				
				if(inventaire.getFtxtf_bonSnack().getValue() instanceof Long)
					inventaire.data.setBonSnac(Math.abs(((Long)inventaire.getFtxtf_bonSnack().getValue()).doubleValue()));
				else
					inventaire.data.setBonSnac(Math.abs(((Double)inventaire.getFtxtf_bonSnack().getValue()).doubleValue()));
				inventaire.getLab_recetteReelle().setText(""+inventaire.data.getRecetteReelle());
				inventaire.getLab_Difference().setText(""+inventaire.data.getDifference()+"debug");

			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(null,"Donnee invalide ou inexistente !", "Erreur",JOptionPane.ERROR_MESSAGE);
			}
			inventaire.actualiserChamp();
		}
		else if (e.getSource() == course.getBoutton(IHMCourse.BTN_VALIDER)) {
			try {
				course.getFtxtf_totalTicket().commitEdit();
				if(course.getFtxtf_totalTicket().getValue() instanceof Long)
					course.data.setTotalTicket(Math.abs(((Long)course.getFtxtf_totalTicket().getValue()).doubleValue()));
				else if(course.getFtxtf_totalTicket().getValue() instanceof Double)
					course.data.setTotalTicket(Math.abs(((Double)course.getFtxtf_totalTicket().getValue()).doubleValue()));
				inventaire.getFtxtf_qtPrecedente().commitEdit();
				inventaire.curLine.qtPrecedent=Math.abs(((Long)inventaire.getFtxtf_qtPrecedente().getValue()).intValue());
				inventaire.getFtxtf_qtRestante().commitEdit();
				inventaire.curLine.qtRestante=Math.abs(((Long)inventaire.getFtxtf_qtRestante().getValue()).intValue());
				inventaire.getLab_qtVendu().setText(""+inventaire.curLine.getQtVendu());
				if(inventaire.curLine.getQtVendu()<0)
					JOptionPane.showMessageDialog(null, "Attention quantité vendu < 0 !!!", "Avertissement", JOptionPane.WARNING_MESSAGE);
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(null,"Donnee invalide ou inexistente !", "Erreur",JOptionPane.ERROR_MESSAGE);
			}
			inventaire.actualiserChamp();
		}
		else if(e.getSource() == modif_config.getBoutton(IHMConfigModifPanel.BTN_AJOUTER)) {
			Produit t = null;
			PopupProduit pop = new PopupProduit(data_config);
			t = pop.showDialog();
			if (t.prixUnitaire <0)
				t.prixUnitaire *= -1;
			int i = JOptionPane.showConfirmDialog(null,t.toString(), "Confirmer la saisie !!!",JOptionPane.YES_NO_OPTION);
			while(i != 0) {
				t = pop.showDialog();
				if (t.prixUnitaire <0)
					t.prixUnitaire *= -1;
				i = JOptionPane.showConfirmDialog(null,"Categorie : "+t.categorie+"\nNom : "+ t.nom+"\nPrix : "+t.prixUnitaire+" E", "Confirmer la saisie !!!",JOptionPane.YES_NO_OPTION);
			}
			data_config.addProduit(t);
			actualiser();

			System.out.println(data_config.categorie.toString());
		}
		else if (e.getSource() == modif_config.getBoutton(IHMConfigModifPanel.BTN_OK)) {
			if (modif_config.curProduit != null) {
				modif_config.curProduit.setCategorie(modif_config.getTxtf_categorie().getText());
				modif_config.curProduit.setNom(modif_config.getTxtf_nom().getText());
				modif_config.curProduit.setPrixUnitaire((double)modif_config.getTxtf_prix().getValue());
				modif_config.actualiser(data_config);
			}
		}
		else if(e.getSource() == acceuil.getBoutton(IHMAcceuil.BTN_COURSE)) {
			this.setContentPane(course);
			actualiser();
		}
		else if(e.getSource() == acceuil.getBoutton(IHMAcceuil.BTN_INVENTAIRE)) {
			this.setContentPane(inventaire);
			actualiser();
		}
		else if(e.getSource() == course.getBoutton(IHMCourse.BTN_AJOUTER_PRODUIT)) {
			PopupAjoutProduit pop = new PopupAjoutProduit(data_config);
			Produit t = pop.showDialog();
			course.addProduit(t);
			course.actualiserArbre();
			actualiser();
		}
		else if(e.getSource() == inventaire.getBoutton(IHMInventaire.BTN_AJOUTER_PRODUIT)) {
			PopupAjoutProduit pop = new PopupAjoutProduit(data_config);
			Produit t = pop.showDialog();
			inventaire.addProduit(t);
			inventaire.actualiserArbre();
			actualiser();
		}
		else if (e.getSource() == course.getBoutton(IHMCourse.BTN_SUPPRIMER_PACK)) {
			int row = course.prod.getSelectedRow();
			if(course.curLine == null)
				JOptionPane.showMessageDialog(null, "Vous n'avez selectionner aucun produit...","Erreur",JOptionPane.ERROR_MESSAGE);
			else if(row == -1)
				JOptionPane.showMessageDialog(null, "Vous n'avez selectionner aucune ligne...","Erreur",JOptionPane.ERROR_MESSAGE);
			else {
				if(course.curLine.getPack().size() == 1) {
					int option = JOptionPane.showConfirmDialog(null, "Attention vous êtes sur le point de supprimer le dernier Pack de ce produit,\nCela entrainera sa suppression de la feuille !\nConfirmer la suppression ?", "Avertissement !!!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(option == JOptionPane.OK_OPTION) {
						course.curLine.getPack().remove(row);
						course.data.supprimerLigne(course.curLine);
						course.setLocal_config(course.data.convertToConfig(true));
					}
				}
				course.actualiserTab();
				course.actualiserArbre();
			}
		}
		else if (e.getSource() == course.getBoutton(IHMCourse.BTN_AJOUTER_PACK)) {
			if(course.curLine != null) {
				PopupAjouterPack pop = new PopupAjouterPack();
				Pack t = pop.showDialog();
				course.curLine.addPack(t);
				course.actualiserTab();
				course.actualiserChamp();
			}
			else
				JOptionPane.showMessageDialog(null, "Vous n'avez selectionner aucune ligne...","Erreur",JOptionPane.ERROR_MESSAGE);
		}
		else if(e.getSource() == course.getBoutton(IHMCourse.BTN_SUPPRIMER_PRODUIT)){
			if(course.curLine != null) {
				int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce produit ?","Demande de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.OK_OPTION){
					course.data.supprimerLigne(course.curLine);
					Configuration temp = course.getLocal_config();
					temp.listProduit.remove(temp.shearchProduit(course.curLine.categorie, course.curLine.nom));
					temp.categorie = temp.getListCategorie();
				}
				course.actualiserTab();
				course.actualiserArbre();
				course.actualiserChamp();
			}
			else
				JOptionPane.showMessageDialog(null, "Vous n'avez selectionner aucune ligne...","Erreur", JOptionPane.ERROR_MESSAGE);
		}
		else if(e.getSource() == course.getJMenuItem(IHMCourse.JMI_EXPORTER_MODELE)){
			PopupExportModele pop = new PopupExportModele(course.data,PopupExportModele.COURSE);
			pop.showDialog();
		}
		else if(e.getSource() == inventaire.getJMenuItem(IHMInventaire.JMI_EXPORTER_MODELE)){
			PopupExportModele pop = new PopupExportModele(inventaire.data, PopupExportModele.INVENTAIRE);
			pop.showDialog();
		}
		else if (e.getSource() == course.getJMenuItem(IHMCourse.JMI_IMPORTER_MODELE)){
			PopupImportModele pop = new PopupImportModele(PopupImportModele.COURSE);
			course.data = pop.showDialogCourse();
			int option = JOptionPane.showConfirmDialog(null, "Demander pour l'ajout d'un produit non configure ?", "Mode auto : Activation ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			course.setLocal_config(course.data.convertToConfig(option != JOptionPane.OK_OPTION));
			course.actualiserArbre();
			course.actualiserChamp();
		}
		else if (e.getSource() == inventaire.getJMenuItem(IHMInventaire.JMI_IMPORTER_MODELE)){
			PopupImportModele pop = new PopupImportModele(PopupImportModele.INVENTAIRE);
			inventaire.data = pop.showDialogInventaire();
			int option = JOptionPane.showConfirmDialog(null, "Demander pour l'ajout d'un produit non configure ?", "Mode auto : Activation ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			inventaire.setLocal_config(inventaire.data.convertToConfig(option != JOptionPane.OK_OPTION));
			inventaire.actualiserArbre();
			inventaire.actualiserChamp();
		}
		else if (e.getSource() == inventaire.getJMenuItem(IHMInventaire.JMI_SAUVER)) {
			String path = inventaire.data.writeXLS();
			JOptionPane.showMessageDialog(null, "Fichier sauvegarder.\n Chemin : "+path, "Sauvegarde reussi !!!", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getSource() == course.getJMenuItem(IHMInventaire.JMI_SAUVER)) {
			String path = course.data.writeXLS();
			JOptionPane.showMessageDialog(null, "Fichier sauvegarder.\n Chemin : "+path, "Sauvegarde reussi !!!", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getSource() == inventaire.getJMenuItem(IHMInventaire.JMI_OUVRIR)) {
			PopupOuvrir pop = new PopupOuvrir(PopupOuvrir.INVENTAIRE);
			inventaire.data = pop.showDialogInventaire();
			inventaire.setLocal_config(inventaire.data.convertToConfig(true));
			inventaire.actualiserArbre();
			inventaire.actualiserChamp();
		}
		else if (e.getSource() == course.getJMenuItem(IHMInventaire.JMI_OUVRIR)) {
			PopupOuvrir pop = new PopupOuvrir(PopupOuvrir.COURSE);
			course.data = pop.showDialogCourse();
			course.setLocal_config(course.data.convertToConfig(true));
			course.actualiserArbre();
			course.actualiserTab();
		}
	}

	private void actualiser() {
		modif_config.buildArbre(data_config);
		data_config.categorie = data_config.getListCategorie();
		this.repaint();
		this.revalidate();
	}


}
