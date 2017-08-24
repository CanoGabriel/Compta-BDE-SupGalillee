package IHMComponent;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Default.Configuration;
import Default.FenAcceuil;
import Default.FeuilleCourse;

public class IHMCourse extends JPanel implements IHMBase{

	public static String BTN_AJOUTER_PRODUIT = "ajouter produit";
	public static String BTN_SUPPRIMER_PRODUIT = "supprimer produit";
	public static String BTN_AJOUTER_PACK = "ajouter pack";
	public static String BTN_SUPPRIMER_PACK = "supprimer pack";
	public static String BTN_ACCEUIL = "acceuil";
	public static String BTN_VALIDER = "valider";
	
	public static String JMI_OUVRIR = "ouvrir";
	public static String JMI_SAUVER = "sauver";
	public static String JMI_IMPORTER_PRODUIT = "impMod"; 
	public static String JMI_EXPORTER_PRODUIT = "expMod";
	
	private ArrayList<Boutton> listBoutton = new ArrayList<Boutton>();
	private ArrayList<JMenuItem> listMenuItem = new ArrayList<JMenuItem>();
	
	private FeuilleCourse data = null;
	
	private static final long serialVersionUID = -5825014758787535331L;
	private JPanel center = new JPanel();
	private JPanel center_c1 = new JPanel();
	private JPanel center_c1_center = new JPanel();
	private JPanel center_c1_south = new JPanel();
	private JPanel center_c1_south_c1 = new JPanel();
	private JPanel center_c1_south_c2 = new JPanel();
	private JPanel center_c2 = new JPanel();
	private JPanel center_c2_center = new JPanel();
	private JPanel center_c2_center_center = new JPanel();
	private JPanel center_c2_center_south = new JPanel();
	private JPanel center_c2_center_south_c1 = new JPanel();
	private JPanel center_c2_center_south_c2 = new JPanel();
	private JPanel center_c2_south = new JPanel();
	private JPanel center_c2_south_l1 = new JPanel();
	private JPanel center_c2_south_l2 = new JPanel();
	private JPanel center_c2_south_l3 = new JPanel();
	private JPanel south = new JPanel();
	private JPanel south_c1 = new JPanel();
	private JPanel south_c2 = new JPanel();

	private Boutton btn_AjouterProd = new Boutton("Ajouter produit");
	private Boutton btn_SupprimerProd = new Boutton("Supprimer produit");
	private Boutton btn_AjouterPack = new Boutton("Ajouter pack");
	private Boutton btn_SupprimerPack = new Boutton("Supprimer pack");
	private Boutton btn_Acceuil= new Boutton("Acceuil");
	private Boutton btn_valider= new Boutton("Valider");
	
	private JScrollPane viewProd = new JScrollPane();
	private JScrollPane viewInfo = new JScrollPane();
	
	private JLabel lab_date = new JLabel();
	private JLabel lab_total = new JLabel();
	private JFormattedTextField ftxtf_totalTicket = null;
	
	private JMenuBar menu = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier");
	private JMenuItem ouvrir = new JMenuItem("Ouvrir");
	private JMenuItem sauver = new JMenuItem("Sauvegarder");
	private JMenuItem impMod = new JMenuItem("Importer modele");
	private JMenuItem expMod = new JMenuItem("Exporter modele");
	
	public IHMCourse(FenAcceuil parent) {
		NumberFormat nf=  NumberFormat.getNumberInstance();
		nf.setParseIntegerOnly(false);
		ftxtf_totalTicket = new JFormattedTextField(nf);
		
		btn_Acceuil.addActionListener(parent);
		listBoutton.add(btn_Acceuil);
		btn_Acceuil.setName(BTN_ACCEUIL);
		
		btn_AjouterPack.addActionListener(parent);
		listBoutton.add(btn_AjouterPack);
		btn_AjouterPack.setName(BTN_AJOUTER_PACK);
		
		btn_AjouterProd.addActionListener(parent);
		listBoutton.add(btn_AjouterProd);
		btn_AjouterProd.setName(BTN_AJOUTER_PRODUIT);
		
		btn_SupprimerPack.addActionListener(parent);
		listBoutton.add(btn_SupprimerPack);
		btn_SupprimerPack.setName(BTN_SUPPRIMER_PACK);
		
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
//		center_c1_south_c1.setLayout(new BorderLayout());
//		center_c1_south_c2.setLayout(new BorderLayout());
		center_c2.setLayout(new BorderLayout());
		center_c2_center.setLayout(new BorderLayout());
		center_c2_center_center.setLayout(new BorderLayout());
		center_c2_center_south.setLayout(new GridLayout(1, 2));
		center_c2_south.setLayout(new GridLayout(3, 1));
		center_c2_south_l1.setLayout(new BorderLayout());
		center_c2_south_l2.setLayout(new BorderLayout());
		center_c2_south_l3.setLayout(new BorderLayout());
		south.setLayout(new GridLayout(1, 2));
		south_c1.setLayout(new BorderLayout());
		south_c2.setLayout(new BorderLayout());
		
		center_c1_center.add(viewProd, BorderLayout.CENTER);
		center_c1_south_c1.add(btn_AjouterProd, BorderLayout.CENTER);
		center_c1_south_c2.add(btn_SupprimerProd, BorderLayout.CENTER);
		
		center_c2_center_center.add(viewInfo, BorderLayout.CENTER);
		center_c2_center_south_c1.add(btn_AjouterPack, BorderLayout.CENTER);
		center_c2_center_south_c2.add(btn_SupprimerPack, BorderLayout.CENTER);
		
		center_c2_south_l1.add(lab_date,BorderLayout.CENTER);
		center_c2_south_l2.add(lab_total,BorderLayout.CENTER);
		center_c2_south_l3.add(ftxtf_totalTicket,BorderLayout.CENTER);
		
		south_c1.add(btn_Acceuil);
		south_c2.add(btn_valider);
		
		this.add(center, BorderLayout.CENTER);
		center.add(center_c1);
		center_c1.add(center_c1_center, BorderLayout.CENTER);
		center_c1.add(center_c1_south, BorderLayout.SOUTH);
		center_c1_south.add(center_c1_south_c1);
		center_c1_south.add(center_c1_south_c2);
		center.add(center_c2);
		center_c2.add(center_c2_center, BorderLayout.CENTER);
		center_c2_center.add(center_c2_center_center,BorderLayout.CENTER);
		center_c2_center.add(center_c2_center_south,BorderLayout.SOUTH);
		center_c2_center_south.add(center_c2_center_south_c1);
		center_c2_center_south.add(center_c2_center_south_c2);
		center_c2.add(center_c2_south, BorderLayout.SOUTH);
		center_c2_south.add(center_c2_south_l1);
		center_c2_south.add(center_c2_south_l2);
		center_c2_south.add(center_c2_south_l3);
//		center_c2_south.add(center_c2_south_c1);
//		center_c2_south.add(center_c2_south_c2);
		this.add(south, BorderLayout.SOUTH);
		south.add(south_c1);
		south.add(south_c2);
		
		center_c2_south.setBorder(BorderFactory.createTitledBorder("Information general :"));
		center_c2_center.setBorder(BorderFactory.createTitledBorder("Gestion des pack :"));
		center_c2_south_l1.setBorder(BorderFactory.createTitledBorder("Date :"));
		center_c2_south_l2.setBorder(BorderFactory.createTitledBorder("Total :"));
		center_c2_south_l3.setBorder(BorderFactory.createTitledBorder("Total Ticket :"));
		
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
		impMod.setName(JMI_IMPORTER_PRODUIT);
		listMenuItem.add(impMod);
		expMod.addActionListener(parent);
		expMod.setName(JMI_EXPORTER_PRODUIT);
		listMenuItem.add(expMod);
	}

	public Boutton getBoutton(String name) {
		for(Boutton i : listBoutton ){
			if(name.equals(i.getName()))
				return i;
		}
		return null;
	}
	
	public JMenuItem getJMenuItem(String name) {
		for(JMenuItem i : listMenuItem){
			if(name.equals(i.getName()))
				return i;
		}
		return null;
	}

}