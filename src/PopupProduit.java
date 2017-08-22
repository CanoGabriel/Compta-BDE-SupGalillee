import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		nf.setMinimumIntegerDigits(1);
		nf.setMinimumFractionDigits(0);
		nf.setMaximumFractionDigits(2);
		nf.setMaximumIntegerDigits(3);
		prix = new JFormattedTextField(nf);

		content.setLayout(new GridLayout(4, 1));
		content.add(categorie);
		content.add(nom);
		content.add(prix);
		content.add(validation);
		validation.addActionListener(this);

	}

	private class Boutton extends JButton implements MouseListener{

		/**
		 * 
		 */
		private static final long serialVersionUID = -4372214834361000899L;

		public Boutton(String string) {
			super(string);
		}

		public void mouseClicked(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

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
			double p = ((Long) prix.getValue()).doubleValue(); 
			this.p= new Produit(cat, n, p);
			this.dispose();
		}
	}

}
