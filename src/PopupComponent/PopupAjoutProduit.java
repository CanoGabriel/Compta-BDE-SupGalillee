package PopupComponent;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Default.Configuration;
import Default.Produit;
import IHMComponent.Boutton;

public class PopupAjoutProduit extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8975193975616563754L;

	private ArrayList<Produit>	listProduit		= null;
	private ArrayList<String>	listCategorie	= null;

	private JComboBox<String>	cb_categorie	= new JComboBox<String>();
	private JComboBox<String>	cb_produit		= new JComboBox<String>();
	private Boutton				btn_ok			= new Boutton("ok");

	private JPanel	content		= new JPanel();
	private JPanel	north		= new JPanel();
	private JPanel	north_l1	= new JPanel();
	private JPanel	north_l2	= new JPanel();
	private JPanel	south		= new JPanel();

	private Produit				produit	= null;
	private final Configuration	data_config;

	public PopupAjoutProduit (Configuration data) {

		super((JFrame) null, "Choix du produit", true);

		this.setTitle("Saisir les informations du produit");
		this.setResizable(false);
		this.setSize(600, 170);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(content);

		data_config = data;
		listCategorie = data.getCategorie();
		listProduit = data.getProduitByCategorie(null);

		for (String i : listCategorie)
			cb_categorie.addItem(i);
		for (Produit i : data.getProduitByCategorie(cb_categorie.getItemAt(0)))
			cb_produit.addItem(i.getNom());

		btn_ok.addActionListener(this);

		content.setLayout(new BorderLayout());
		north.setLayout(new GridLayout(2, 1));
		north_l1.setLayout(new BorderLayout());
		north_l2.setLayout(new BorderLayout());

		north_l1.add(cb_categorie, BorderLayout.CENTER);
		north_l2.add(cb_produit, BorderLayout.CENTER);

		north.add(north_l1);
		north.add(north_l2);
		south.add(btn_ok);

		content.add(north, BorderLayout.NORTH);
		content.add(south, BorderLayout.SOUTH);

		north_l1.setBorder(BorderFactory.createTitledBorder("Selection de la categorie :"));
		north_l2.setBorder(BorderFactory.createTitledBorder("Selection du produit :"));

		cb_categorie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed (ActionEvent e) {

				if (e.getSource() == cb_categorie) listProduit = data.getProduitByCategorie(cb_categorie.getSelectedItem().toString());
				cb_produit.removeAllItems();
				for (Produit i : listProduit)
					cb_produit.addItem(i.getNom());
				repaint();
				revalidate();
			}
		});
	}

	@Override
	public void actionPerformed (ActionEvent e) {

		if (e.getSource() == btn_ok) {
			produit = data_config.shearchProduit(cb_categorie.getSelectedItem().toString(), cb_produit.getSelectedItem().toString());
			this.dispose();
		}
	}

	public Produit showDialog () {

		this.setVisible(true);
		return produit;
	}

	public Boutton getBtn_ok () {

		return btn_ok;
	}
}
