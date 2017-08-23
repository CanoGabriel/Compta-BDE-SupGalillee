package Default;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import IHMComponent.Boutton;
import IHMComponent.IHMAcceuil;
import IHMComponent.IHMConfigModifPanel;
import IHMComponent.IHMConfigOverview;

public class FenAcceuil extends JFrame implements ActionListener{
	private static final long serialVersionUID = 4358673801877559732L;
	private Configuration data_config = null;

	private IHMAcceuil acceuil = new IHMAcceuil(this);
	private IHMConfigOverview config = new IHMConfigOverview(this);
	private IHMConfigModifPanel modif_config = new IHMConfigModifPanel(this);

	public FenAcceuil() {
		data_config = new Configuration();
		this.setTitle("Comptabilite BDE 2017");
		this.setResizable(true);
		this.setSize(600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setContentPane(acceuil);
		this.setVisible(true);
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(((Boutton)e.getSource()).getName());
		if(e.getSource() == config.getBoutton(IHMConfigOverview.BTN_ACCEUIL) || e.getSource() == modif_config.getBoutton(IHMConfigModifPanel.BTN_ACCEUIL)) {
			this.setContentPane(acceuil);
			data_config.setDefaultSaveFilePath(config.getTxtf_path().getText());
			data_config.write();
			actualiser();
		}
		else if(e.getSource() == acceuil.getBoutton(IHMAcceuil.BTN_CONFIG)) {
			this.setContentPane(config);
//			data_config = new Configuration();
			System.out.println(data_config);
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
			data_config.write();
			actualiser();
		}
		else if(e.getSource() == modif_config.getBoutton(IHMConfigModifPanel.BTN_AJOUTER)) {
			Produit t = null;
			JOptionPane jop = new JOptionPane();
			PopupProduit pop = new PopupProduit(null,"test popup",true);
			t = pop.showDialog();
			if (t.prixUnitaire <0)
				t.prixUnitaire *= -1;
			int i = jop.showConfirmDialog(null,t.toString(), "Confirmer la saisie !!!",JOptionPane.YES_NO_OPTION);
			while(i != 0) {
				t = pop.showDialog();
				if (t.prixUnitaire <0)
					t.prixUnitaire *= -1;
				i = jop.showConfirmDialog(null,"Categorie : "+t.categorie+"\nNom : "+ t.nom+"\nPrix : "+t.prixUnitaire+" E", "Confirmer la saisie !!!",JOptionPane.YES_NO_OPTION);
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
	}

	private void actualiser() {
		modif_config.buildArbre(data_config);
		data_config.categorie = data_config.getListCategorie();
		this.repaint();
		this.revalidate();
	}


}
