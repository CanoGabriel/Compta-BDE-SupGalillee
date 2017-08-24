package IHMComponent;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Default.Produit;

public class PopupProduit extends JDialog implements ActionListener{
	private Produit p = null;
	/**
	 * 
	 */
	private JPanel content = new JPanel();
	private JTextField categorie = new JTextField();
	private JTextField nom = new JTextField();
	private JFormattedTextField prix = null;
	private Boutton validation = new Boutton("Valider Saisie");

	private static final long serialVersionUID = 6011373255368842196L;

	public PopupProduit(JFrame parent,String title,boolean modal) {
		super(parent,title,modal);
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

		content.setLayout(new GridLayout(4, 1));
		content.add(categorie);
		content.add(nom);
		content.add(prix);
		content.add(validation);
		nom.setBorder(BorderFactory.createTitledBorder("Entrer le nom du produit : "));
		categorie.setBorder(BorderFactory.createTitledBorder("Entrer la categorie du produit : "));
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
			String cat = categorie.getText();
			String n = nom.getText();
//			double p = ((Long) prix.getValue()).doubleValue(); 
			try {
				prix.commitEdit();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showConfirmDialog(null, e1.getMessage());
			}
			Object p = prix.getValue();
			if (p instanceof Long)
				this.p= new Produit(cat, n, ((Long) p).doubleValue());
			else if (p instanceof java.lang.Double)
				this.p=new Produit(cat, n,(double)p);
			else
				JOptionPane.showConfirmDialog(null, "Cast Exception");
			this.dispose();
		}
	}

}