package IHMComponent;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import Default.Configuration;
import Default.FenAcceuil;
import Default.FeuilleInventaire;
import Default.LigneInventaire;
import Default.Produit;

public class IHMInventaire extends JPanel{

	public static String BTN_AJOUTER_PRODUIT = "ajouter produit";
	public static String BTN_SUPPRIMER_PRODUIT = "supprimer produit";
	public static String BTN_ACCEUIL = "acceuil";
	public static String BTN_VALIDER = "valider";

	public static String JMI_OUVRIR = "ouvrir";
	public static String JMI_SAUVER = "sauver";
	public static String JMI_IMPORTER_MODELE = "impMod"; 
	public static String JMI_EXPORTER_MODELE = "expMod";

	private ArrayList<Boutton> listBoutton = new ArrayList<Boutton>();
	private ArrayList<JMenuItem> listMenuItem = new ArrayList<JMenuItem>();

	public FeuilleInventaire data = new FeuilleInventaire(new Date());
	private Configuration local_config = new Configuration("none");
	public LigneInventaire curLine = null;

	private static final long serialVersionUID = -5825014758787535331L;
	private JPanel center = new JPanel();
	private JPanel center_c1 = new JPanel();
	private JPanel center_c1_center = new JPanel();
	private JPanel center_c1_south = new JPanel();
	private JPanel center_c1_south_c1 = new JPanel();
	private JPanel center_c1_south_c2 = new JPanel();
	private JPanel center_c2 = new JPanel();
	private JPanel center_c2_north = new JPanel();
//	private JPanel center_c2_north = new JPanel();
//	private JPanel center_c2_north_south = new JPanel();
	private JPanel center_c2_north_n1 = new JPanel();
	private JPanel center_c2_north_n2 = new JPanel();
	private JPanel center_c2_north_n3 = new JPanel();
	private JPanel center_c2_north_n4 = new JPanel();
	private JPanel center_c2_south = new JPanel();
	private JPanel center_c2_south_l1 = new JPanel();
	private JPanel center_c2_south_l2 = new JPanel();
	private JPanel center_c2_south_l3 = new JPanel();
	private JPanel center_c2_south_l4 = new JPanel();
	private JPanel center_c2_south_l5 = new JPanel();
	private JPanel center_c2_south_l6 = new JPanel();
	private JPanel center_c2_south_l7 = new JPanel();
	private JPanel center_c2_south_l7_c1 = new JPanel();
	private JPanel center_c2_south_l7_c2 = new JPanel();
	private JPanel center_c2_south_l8 = new JPanel();
	private JPanel south = new JPanel();

	private Boutton btn_AjouterProd = new Boutton("Ajouter produit");
	private Boutton btn_SupprimerProd = new Boutton("Supprimer produit");
	private Boutton btn_Acceuil= new Boutton("Acceuil");
	private Boutton btn_valider= new Boutton("Valider");

	private JScrollPane viewProd = new JScrollPane();

	private JLabel lab_date = new JLabel();
	private JLabel lab_recetteTheorique = new JLabel();
	private JLabel lab_Difference = new JLabel();
	private JLabel lab_recetteReelle = new JLabel();
	private JFormattedTextField ftxtf_totalCaisse = null;
	private JFormattedTextField ftxtf_bonSnack = null;
	public JFormattedTextField getFtxtf_totalCaisse() {
		return ftxtf_totalCaisse;
	}

	public JFormattedTextField getFtxtf_fondPrecedent() {
		return ftxtf_fondPrecedent;
	}

	public JFormattedTextField getFtxtf_fondSuivant() {
		return ftxtf_fondSuivant;
	}

	private JFormattedTextField ftxtf_fondPrecedent = null;
	private JFormattedTextField ftxtf_fondSuivant = null;

	private JFormattedTextField ftxtf_qtPrecedente = null;
	private JFormattedTextField ftxtf_qtRestante = null;
	private JFormattedTextField ftxtf_qtCourse= null;
	private JLabel lab_qtVendu= new JLabel();

	private JMenuBar menu = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier");
	private JMenuItem ouvrir = new JMenuItem("Ouvrir");
	private JMenuItem sauver = new JMenuItem("Sauvegarder");
	private JMenuItem impMod = new JMenuItem("Importer modele");
	private JMenuItem expMod = new JMenuItem("Exporter modele");

	public IHMInventaire(FenAcceuil parent) {
		NumberFormat nf1=  NumberFormat.getNumberInstance();
		NumberFormat nf2=  NumberFormat.getNumberInstance();
		NumberFormat nf3=  NumberFormat.getNumberInstance();
		NumberFormat nf4=  NumberFormat.getNumberInstance();
		nf1.setParseIntegerOnly(false);
		nf1.setMinimumFractionDigits(1);
		nf2.setParseIntegerOnly(false);
		nf2.setMinimumFractionDigits(1);
		nf3.setParseIntegerOnly(false);
		nf3.setMinimumFractionDigits(1);
		nf4.setParseIntegerOnly(false);
		nf4.setMinimumFractionDigits(1);
		ftxtf_totalCaisse = new JFormattedTextField(nf1);
		ftxtf_fondPrecedent = new JFormattedTextField(nf2);
		ftxtf_fondSuivant = new JFormattedTextField(nf3);
		ftxtf_bonSnack = new JFormattedTextField(nf4);
		
		NumberFormat nfint1=  NumberFormat.getNumberInstance();
		NumberFormat nfint2=  NumberFormat.getNumberInstance();
		NumberFormat nfint3=  NumberFormat.getNumberInstance();
		nfint1.setParseIntegerOnly(true);
		nfint2.setParseIntegerOnly(true);
		nfint3.setParseIntegerOnly(true);
		ftxtf_qtCourse = new JFormattedTextField(nfint1);
		ftxtf_qtPrecedente = new JFormattedTextField(nfint2);
		ftxtf_qtRestante = new JFormattedTextField(nfint3);

		btn_Acceuil.addActionListener(parent);
		listBoutton.add(btn_Acceuil);
		btn_Acceuil.setName(BTN_ACCEUIL);

		btn_AjouterProd.addActionListener(parent);
		listBoutton.add(btn_AjouterProd);
		btn_AjouterProd.setName(BTN_AJOUTER_PRODUIT);

		btn_SupprimerProd.addActionListener(parent);
		listBoutton.add(btn_SupprimerProd);
		btn_SupprimerProd.setName(BTN_SUPPRIMER_PRODUIT);
		
		btn_valider.addActionListener(parent);
		listBoutton.add(btn_valider);
		btn_valider.setName(BTN_VALIDER);

		this.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(1, 2));
		center_c1.setLayout(new BorderLayout());
		center_c1_center.setLayout(new BorderLayout());
		center_c1_south.setLayout(new GridLayout(1, 2));
		center_c2.setLayout(new BorderLayout());
		center_c2_north.setLayout(new GridLayout(2, 2));
//		center_c2_north.setLayout(new GridLayout(2, 2));
		center_c2_north_n1.setLayout(new BorderLayout());
		center_c2_north_n2.setLayout(new BorderLayout());
		center_c2_north_n3.setLayout(new BorderLayout());
		center_c2_north_n4.setLayout(new BorderLayout());
//		center_c2_north_south.setLayout(new BorderLayout());
		center_c2_south.setLayout(new GridLayout(8, 1));
		center_c2_south_l1.setLayout(new BorderLayout());
		center_c2_south_l2.setLayout(new BorderLayout());
		center_c2_south_l3.setLayout(new BorderLayout());
		center_c2_south_l4.setLayout(new BorderLayout());
		center_c2_south_l5.setLayout(new BorderLayout());
		center_c2_south_l6.setLayout(new BorderLayout());
		center_c2_south_l7.setLayout(new GridLayout(1, 2));
		center_c2_south_l7_c1.setLayout(new BorderLayout());
		center_c2_south_l7_c2.setLayout(new BorderLayout());
//		center_c2_south_l8.setLayout(new BorderLayout());
		south.setLayout(new BorderLayout());
		center_c1_center.add(viewProd, BorderLayout.CENTER);
		center_c1_south_c1.add(btn_AjouterProd, BorderLayout.CENTER);
		center_c1_south_c2.add(btn_SupprimerProd, BorderLayout.CENTER);



		center_c2_north_n1.add(ftxtf_qtPrecedente,BorderLayout.CENTER);
		center_c2_north_n2.add(ftxtf_qtCourse,BorderLayout.CENTER);
		center_c2_north_n3.add(ftxtf_qtRestante,BorderLayout.CENTER);
		center_c2_north_n4.add(lab_qtVendu,BorderLayout.CENTER);
		
//		center_c2_north_south.add(btn_valider, BorderLayout.CENTER);

		center_c2_south_l1.add(lab_date,BorderLayout.CENTER);
		center_c2_south_l2.add(ftxtf_fondPrecedent,BorderLayout.CENTER);
		center_c2_south_l3.add(ftxtf_totalCaisse,BorderLayout.CENTER);
		center_c2_south_l4.add(ftxtf_fondSuivant,BorderLayout.CENTER);
		center_c2_south_l5.add(lab_recetteTheorique,BorderLayout.CENTER);
		center_c2_south_l6.add(lab_recetteReelle,BorderLayout.CENTER);
		center_c2_south_l7_c1.add(lab_Difference,BorderLayout.CENTER);
		center_c2_south_l7_c2.add(ftxtf_bonSnack, BorderLayout.CENTER);
		center_c2_south_l8.add(btn_valider,BorderLayout.CENTER);


		this.add(center, BorderLayout.CENTER);
		center.add(center_c1);
		center_c1.add(center_c1_center, BorderLayout.CENTER);
		center_c1.add(center_c1_south, BorderLayout.SOUTH);
		center_c1_south.add(center_c1_south_c1);
		center_c1_south.add(center_c1_south_c2);
		center.add(center_c2);
		center_c2.add(center_c2_north, BorderLayout.NORTH);
//		center_c2_north.add(center_c2_north,BorderLayout.CENTER);
		center_c2_north.add(center_c2_north_n1);
		center_c2_north.add(center_c2_north_n2);
		center_c2_north.add(center_c2_north_n3);
		center_c2_north.add(center_c2_north_n4);
//		center_c2_north.add(center_c2_north_south,BorderLayout.SOUTH);

		center_c2.add(center_c2_south, BorderLayout.SOUTH);
		center_c2_south.add(center_c2_south_l1);
		center_c2_south.add(center_c2_south_l2);
		center_c2_south.add(center_c2_south_l3);
		center_c2_south.add(center_c2_south_l4);
		center_c2_south.add(center_c2_south_l5);
		center_c2_south.add(center_c2_south_l6);
		center_c2_south.add(center_c2_south_l7);
		center_c2_south_l7.add(center_c2_south_l7_c1);
		center_c2_south_l7.add(center_c2_south_l7_c2);
		center_c2_south.add(center_c2_south_l8);
		this.add(south, BorderLayout.SOUTH);
		south.add(btn_Acceuil,BorderLayout.CENTER);

		center_c2_south.setBorder(BorderFactory.createTitledBorder("Information general :"));
		center_c2_north.setBorder(BorderFactory.createTitledBorder("Information produit :"));
		center_c2_north_n1.setBorder(BorderFactory.createTitledBorder("Quantite precedente :"));
		center_c2_north_n2.setBorder(BorderFactory.createTitledBorder("Quantite course :"));
		center_c2_north_n3.setBorder(BorderFactory.createTitledBorder("Quantite restante :"));
		center_c2_north_n4.setBorder(BorderFactory.createTitledBorder("Quantite vendu :"));
		center_c2_south_l1.setBorder(BorderFactory.createTitledBorder("Date :"));
		center_c2_south_l2.setBorder(BorderFactory.createTitledBorder("Fond de caisse precedent :"));
		center_c2_south_l3.setBorder(BorderFactory.createTitledBorder("Total caisse :"));
		center_c2_south_l4.setBorder(BorderFactory.createTitledBorder("Fond de caisse suivant :"));
		center_c2_south_l5.setBorder(BorderFactory.createTitledBorder("Recette theorique :"));
		center_c2_south_l6.setBorder(BorderFactory.createTitledBorder("Recette reelle :"));
		center_c2_south_l7_c1.setBorder(BorderFactory.createTitledBorder("Difference :"));
		center_c2_south_l7_c2.setBorder(BorderFactory.createTitledBorder("Bon Snac :"));

		menu.add(fichier);
		fichier.add(ouvrir);
		fichier.add(sauver);
		fichier.addSeparator();
		fichier.add(impMod);
		fichier.add(expMod);
		this.add(menu, BorderLayout.NORTH);
		ouvrir.addActionListener(parent);
		ouvrir.setName(JMI_OUVRIR);
		listMenuItem.add(ouvrir);
		sauver.addActionListener(parent);
		sauver.setName(JMI_SAUVER);
		listMenuItem.add(sauver);
		impMod.addActionListener(parent);
		impMod.setName(JMI_IMPORTER_MODELE);
		listMenuItem.add(impMod);
		expMod.addActionListener(parent);
		expMod.setName(JMI_EXPORTER_MODELE);
		listMenuItem.add(expMod);
	}

	public JLabel getLab_recetteReelle() {
		return lab_recetteReelle;
	}

	public JLabel getLab_Difference() {
		return lab_Difference;
	}

	public JPanel getCenter_c2_north() {
		return center_c2_north;
	}

	public void setCenter_c2_north(JPanel center_c2_north) {
		this.center_c2_north = center_c2_north;
	}

	public JLabel getLab_qtVendu() {
		return lab_qtVendu;
	}

	public JFormattedTextField getFtxtf_qtPrecedente() {
		return ftxtf_qtPrecedente;
	}

	public JFormattedTextField getFtxtf_qtRestante() {
		return ftxtf_qtRestante;
	}

	public JFormattedTextField getFtxtf_qtCourse() {
		return ftxtf_qtCourse;
	}

	public Boutton getBoutton(String name) {
		for(Boutton i : listBoutton ){
			if(name.equals(i.getName()))
				return i;
		}
		return null;
	}

	public void addProduit(Produit p) {
		if(data == null)
			data = new FeuilleInventaire(new Date());
		data.ajouterLigne(new LigneInventaire(p));
		local_config.addProduit(p);
	}

	public JMenuItem getJMenuItem(String name) {
		for(JMenuItem i : listMenuItem){
			if(name.equals(i.getName()))
				return i;
		}
		return null;
	}

	public void actualiserChamp() {
		lab_date.setText((new SimpleDateFormat("dd/MM/yyyy , HH:mm:ss").format(data.getDateCreation())));
		ftxtf_fondPrecedent.setText(""+data.FondCaissePrecedent);
		ftxtf_totalCaisse.setText(""+data.getTotaleCaisse());
		ftxtf_fondSuivant.setText(""+data.FondCaisseSuivant);
		ftxtf_bonSnack.setText(""+data.getBonSnac());
		lab_recetteTheorique.setText(""+data.getRecetteTheorique());
		lab_recetteReelle.setText(""+data.getRecetteReelle());
		lab_Difference.setText(""+data.getDifference());
		try {
			ftxtf_fondPrecedent.commitEdit();
			ftxtf_totalCaisse.commitEdit();
			ftxtf_fondSuivant.commitEdit();
			ftxtf_bonSnack.commitEdit();

		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	public JFormattedTextField getFtxtf_bonSnack() {
		return ftxtf_bonSnack;
	}

	public void actualiserArbre() {
		JTree temp = local_config.buildTree(null, true);
		temp.addTreeSelectionListener(new TreeSelectionListener() {

			String histParentPath = null;
			String histCurrentPath = null;
			public void valueChanged(TreeSelectionEvent e) {
				if(histCurrentPath != null && histParentPath != null) {
					LigneInventaire t = data.shearchLine(histParentPath,histCurrentPath);
					if(t != null) {
						lab_date.setText((new SimpleDateFormat("jj/MM/aaaa , HH:mm:ss").format(data.getDateCreation())));
						ftxtf_fondPrecedent.setText(""+data.FondCaissePrecedent);
						ftxtf_totalCaisse.setText(""+data.getTotaleCaisse());
						ftxtf_fondSuivant.setText(""+data.FondCaisseSuivant);
						lab_recetteTheorique.setText(""+data.getRecetteTheorique());
						lab_recetteReelle.setText(""+data.getRecetteReelle());
						lab_Difference.setText(""+data.getDifference());
						lab_Difference.setText(""+data.getDifference());
						try {
							ftxtf_fondPrecedent.commitEdit();
							ftxtf_totalCaisse.commitEdit();
							ftxtf_fondSuivant.commitEdit();
						} catch (ParseException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
							e1.printStackTrace();
						}
					}
				}
				String parentPath = e.getPath().getParentPath().getLastPathComponent().toString();
				String currentPath = e.getPath().getLastPathComponent().toString();
				if(parentPath.equals("root")){
					ftxtf_qtPrecedente.setText("");
					ftxtf_qtCourse.setText("");
					ftxtf_qtRestante.setText("");
					lab_qtVendu.setText("");
					ftxtf_qtPrecedente.setEnabled(false);
					ftxtf_qtCourse.setEnabled(false);
					ftxtf_qtRestante.setEnabled(false);
					lab_qtVendu.setEnabled(false);
					//					viewInfo = new JScrollPane();
					//					center_c2_north.removeAll();
					//					center_c2_north.add(viewInfo, BorderLayout.CENTER);
//					actualiserChamp();
//					repaint();
//					revalidate();
					curLine = null;
				}
				else {
					LigneInventaire t = data.shearchLine(parentPath,currentPath);
					//					System.out.println(t);
					if(t != null) {
						//on charge le tableau
						ftxtf_qtPrecedente.setText(""+t.qtPrecedent);
						ftxtf_qtCourse.setText(""+t.qtCourse);
						ftxtf_qtRestante.setText(""+t.qtRestante);
						lab_qtVendu.setText(""+t.getQtVendu());
						
						ftxtf_qtPrecedente.setEnabled(true);
						ftxtf_qtCourse.setEnabled(true);
						ftxtf_qtRestante.setEnabled(true);
						lab_qtVendu.setEnabled(true);
						try {
							ftxtf_qtPrecedente.commitEdit();
							ftxtf_qtCourse.commitEdit();
							ftxtf_qtRestante.commitEdit();
						} catch (ParseException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
						}
					}
					curLine = t;
				}
				actualiserChamp();
			}
		});
		viewProd = new JScrollPane(temp);
		center_c1_center.removeAll();
		center_c1_center.add(viewProd, BorderLayout.CENTER);
		repaint();
		revalidate();
	}

	public void setLocal_config(Configuration local_config) {
		this.local_config = local_config;
	}

}
