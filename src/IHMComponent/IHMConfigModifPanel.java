package IHMComponent;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import Default.Configuration;
import Default.FenAcceuil;
import Default.Produit;

public class IHMConfigModifPanel extends JPanel implements IHMBase {

	/**
	 * 
	 */
	private ArrayList<Boutton> listBoutton = new ArrayList<Boutton>();

	public Produit curProduit = null;

	public static String	BTN_OK		= "ok";
	public static String	BTN_AJOUTER	= "ajouter";
	public static String	BTN_ACCEUIL	= "acceuil";
	public static String	BTN_VALIDER	= "valider";

	private static final long	serialVersionUID	= -4948756785830503708L;
	private JPanel				center				= new JPanel();
	private JPanel				center_c1			= new JPanel();
	private JPanel				center_c2			= new JPanel();
	private JPanel				center_c2_north		= new JPanel();
	private JPanel				center_c2_north_l1	= new JPanel();
	private JPanel				center_c2_north_l2	= new JPanel();
	private JPanel				center_c2_north_l3	= new JPanel();
	private JPanel				center_c2_south		= new JPanel();
	private JPanel				south				= new JPanel();
	private JPanel				south_c1			= new JPanel();
	private JPanel				south_c2			= new JPanel();
	private JPanel				south_c3			= new JPanel();

	private Boutton btn_ac = new Boutton("Retour à l'acceuil (sauvegarde)");

	private JTextField			txtf_categorie	= new JTextField();
	private JTextField			txtf_nom		= new JTextField();
	private JFormattedTextField	txtf_prix		= null;
	private Boutton				btn_ok			= new Boutton("Valider modification");
	private Boutton				btn_ajouter		= new Boutton("Ajouter un produit");
	private Boutton				btn_valider		= new Boutton("Retour au menu precedent (sauvegarde)");

	private JScrollPane completTree = new JScrollPane();

	public IHMConfigModifPanel (FenAcceuil parent) {

		btn_ac.addActionListener(parent);
		btn_ac.setName(BTN_ACCEUIL);
		listBoutton.add(btn_ac);
		btn_ajouter.addActionListener(parent);
		btn_ajouter.setName(BTN_AJOUTER);
		listBoutton.add(btn_ajouter);
		btn_ok.addActionListener(parent);
		btn_ok.setName(BTN_OK);
		listBoutton.add(btn_ok);
		btn_valider.addActionListener(parent);
		btn_valider.setName(BTN_VALIDER);
		listBoutton.add(btn_valider);

		this.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(1, 2));
		center_c1.setLayout(new BorderLayout());
		center_c2.setLayout(new BorderLayout());
		center_c2_north.setLayout(new GridLayout(3, 1));
		center_c2_north_l1.setLayout(new BorderLayout());
		center_c2_north_l2.setLayout(new BorderLayout());
		center_c2_north_l3.setLayout(new BorderLayout());
		// center_c2_south.setLayout(new BorderLayout());
		south.setLayout(new GridLayout(1, 3));
		south_c1.setLayout(new BorderLayout());
		south_c2.setLayout(new BorderLayout());
		south_c3.setLayout(new BorderLayout());

		center_c1.add(new JScrollPane(completTree), BorderLayout.CENTER);
		center_c2_north_l1.add(txtf_categorie, BorderLayout.CENTER);
		center_c2_north_l2.add(txtf_nom, BorderLayout.CENTER);

		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setParseIntegerOnly(false);
		txtf_prix = new JFormattedTextField(nf);

		txtf_categorie.setEnabled(false);
		txtf_nom.setEnabled(false);
		txtf_prix.setEnabled(false);

		center_c2_north_l3.add(txtf_prix, BorderLayout.CENTER);
		center_c2_south.add(btn_ok, BorderLayout.CENTER);
		south_c1.add(btn_ac, BorderLayout.CENTER);
		south_c2.add(btn_valider, BorderLayout.CENTER);
		south_c3.add(btn_ajouter, BorderLayout.CENTER);

		this.add(center, BorderLayout.CENTER);
		center.add(center_c1);
		center.add(center_c2);
		center_c2.add(center_c2_north, BorderLayout.NORTH);
		center_c2_north.add(center_c2_north_l1);
		center_c2_north.add(center_c2_north_l2);
		center_c2_north.add(center_c2_north_l3);
		center_c2.add(center_c2_south, BorderLayout.SOUTH);
		this.add(south, BorderLayout.SOUTH);
		south.add(south_c1);
		south.add(south_c2);
		south.add(south_c3);

		center_c1.setBorder(BorderFactory.createTitledBorder("Arbre des produits classes par categorie :"));
		center_c2_north.setBorder(BorderFactory.createTitledBorder("Descriptif de la selection :"));
		txtf_categorie.setBorder(BorderFactory.createTitledBorder("Categorie :"));
		txtf_categorie.setSize(center_c2.getSize());
		txtf_nom.setBorder(BorderFactory.createTitledBorder("Nom du produit :"));
		txtf_nom.setPreferredSize(new Dimension(1000, 50));
		txtf_prix.setBorder(BorderFactory.createTitledBorder("Prix :"));
		txtf_prix.setPreferredSize(new Dimension(1000, 50));
	}

	public JTextField getTxtf_categorie () {

		return txtf_categorie;
	}

	public JTextField getTxtf_nom () {

		return txtf_nom;
	}

	public JFormattedTextField getTxtf_prix () {

		return txtf_prix;
	}

	public void buildArbre (Configuration data) {

		JTree temp = data.buildTree(null, true);
		temp.setRootVisible(false);
		temp.addTreeSelectionListener(new TreeSelectionListener() {

			String	histParentPath	= null;
			String	histCurrentPath	= null;

			public void valueChanged (TreeSelectionEvent e) {

				if (histCurrentPath != null && histParentPath != null) {
					Produit t = data.shearchProduit(histParentPath, histCurrentPath);
					if (t != null) {
						t.setCategorie(txtf_categorie.getText());
						t.setNom(txtf_nom.getText());
						t.setPrixUnitaire((double) txtf_prix.getValue());
					}
				}
				String parentPath = e.getPath().getParentPath().getLastPathComponent().toString();
				String currentPath = e.getPath().getLastPathComponent().toString();
				if (parentPath.equals("root")) {
					txtf_categorie.setText(currentPath);
					txtf_nom.setText("");
					txtf_prix.setText("");
					txtf_categorie.setEnabled(false);
					txtf_nom.setEnabled(false);
					txtf_prix.setEnabled(false);
					curProduit = null;
				}

				else {
					Produit t = data.shearchProduit(parentPath, currentPath);
					System.out.println(t);
					if (t != null) {
						txtf_categorie.setEnabled(true);
						txtf_nom.setEnabled(true);
						txtf_prix.setEnabled(true);
						txtf_categorie.setText(t.getCategorie());
						txtf_prix.setValue(t.getPrixUnitaire());
						txtf_nom.setText(t.getNom());
					}
					curProduit = t;
				}
			}
		});
		int row = 0;
		while (row < temp.getRowCount()) {
			temp.expandRow(row);
			row++;
		}
		completTree = new JScrollPane(temp);
		center_c1.removeAll();
		center_c1.add(completTree, BorderLayout.CENTER);
	}

	public void actualiser (Configuration data) {

		data.categorie = data.getListCategorie();
		buildArbre(data);
		repaint();
		revalidate();
	}

	public Boutton getBoutton (String name) {

		for (Boutton i : listBoutton) {
			if (name.equals(i.getName())) return i;
		}
		return null;
	}
}
