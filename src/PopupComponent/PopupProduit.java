package PopupComponent;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Default.Configuration;
import Default.Produit;
import IHMComponent.Boutton;

public class PopupProduit extends JDialog implements ActionListener{
	private Produit p = null;
	/**
	 * 
	 */
	private JPanel content = new JPanel();
	private JComboBox<String> cb_cat = new JComboBox<String>(); 
	private JComboBox<String> cb_nom = new JComboBox<String>(); 
	private JFormattedTextField prix = null;
	private Boutton validation = new Boutton("Valider Saisie");
	private final Configuration d;

	private static final long serialVersionUID = 6011373255368842196L;

	public PopupProduit(Configuration data) {
		super((JFrame)null,"Ajout d'un produit",true);
		d = data;
		this.setTitle("Saisir les informations du produit");
		this.setResizable(false);
		this.setSize(600, 200);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(content);
		NumberFormat nf=  NumberFormat.getNumberInstance();
		nf.setMinimumIntegerDigits(0);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		nf.setMaximumIntegerDigits(2);
		prix = new JFormattedTextField(nf);
		
		for(String i : data.categorie)
			cb_cat.addItem(i);
		cb_cat.addActionListener(this);
		cb_cat.setEditable(true);
		
		cb_nom.addItem("");
		for(Produit i : data.getProduitByCategorie((String) cb_cat.getSelectedItem()))
			cb_nom.addItem(i.getNom());
		cb_nom.setEditable(true);
		
		content.setLayout(new GridLayout(4, 1));
		content.add(cb_cat);
		content.add(cb_nom);
		content.add(prix);
		content.add(validation);
		cb_nom.setBorder(BorderFactory.createTitledBorder("Entrer le nom du produit : "));
		cb_cat.setBorder(BorderFactory.createTitledBorder("Entrer la categorie du produit : "));
		prix.setBorder(BorderFactory.createTitledBorder("Entrer le prix du produit : "));
		validation.addActionListener(this);

	}

	
	public Produit showDialog(){
		this.setVisible(true);      
		return this.p;      
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == validation) {
			String cat = (String) cb_cat.getSelectedItem();
			String n = (String) cb_nom.getSelectedItem();
			try {
				prix.commitEdit();
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(null, "Problème de convertion...","Erreur",JOptionPane.WARNING_MESSAGE);
			}
			Object p = prix.getValue();
			if (p instanceof Long)
				this.p = new Produit(cat, n, Math.abs(((Long) p).doubleValue()));
			else if (p instanceof java.lang.Double)
				this.p = new Produit(cat, n,Math.abs((double)p));
			else
				JOptionPane.showMessageDialog(null, "Prix : Donnee invalide","Erreur",JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
		else if (e.getSource() == cb_cat) {
			cb_nom.removeAllItems();
			cb_nom.addItem("");
			for(Produit i : d.getProduitByCategorie((String) cb_cat.getSelectedItem()))
				cb_nom.addItem(i.getNom());
		}
	}

}
