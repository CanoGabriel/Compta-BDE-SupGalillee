package IHMComponent;

import java.awt.BorderLayout;
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

import Default.Pack;

public class PopupAjouterPack extends JDialog implements ActionListener{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8975193975616563754L;

	private JFormattedTextField nbPack = null;
	private JFormattedTextField nbProd = null;
	private JFormattedTextField prix = null;

	private Boutton btn_ok = new Boutton("ok");

	Pack pack = null;

	private JPanel content = new JPanel();
	private JPanel center = new JPanel();
	private JPanel south = new JPanel();
	private JPanel l1 = new JPanel();
	private JPanel l2 = new JPanel();
	private JPanel l3 = new JPanel();

	public PopupAjouterPack() {
		super((JFrame)null,"Saisie des information du pack",true);

		btn_ok.addActionListener(this);

		NumberFormat nf1=  NumberFormat.getNumberInstance();
		NumberFormat nf2=  NumberFormat.getNumberInstance();
		NumberFormat nf3=  NumberFormat.getNumberInstance();
		nf1.setParseIntegerOnly(true);
		nf2.setParseIntegerOnly(true);
//		nf3.setParseIntegerOnly(false);
		nf3.setMinimumIntegerDigits(0);
		nf3.setMinimumFractionDigits(2);
		nf3.setMaximumFractionDigits(2);
		nf3.setMaximumIntegerDigits(2);
		
		nbPack = new JFormattedTextField(nf1);
		nbProd = new JFormattedTextField(nf2);
		prix = new JFormattedTextField(nf3);
		
		this.setResizable(false);
		this.setSize(600, 200);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(content);

		content.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(3, 1));
		l1.setLayout(new BorderLayout());
		l2.setLayout(new BorderLayout());
		l3.setLayout(new BorderLayout());

		l1.add(nbPack);
		l2.add(nbProd);
		l3.add(prix);

		content.add(center, BorderLayout.CENTER);
		center.add(l1);
		center.add(l2);
		center.add(l3);
		content.add(south, BorderLayout.SOUTH);
		south.add(btn_ok);

		l1.setBorder(BorderFactory.createTitledBorder("Nombre de pack :"));
		l2.setBorder(BorderFactory.createTitledBorder("Nombre de produit dans le pack :"));
		l3.setBorder(BorderFactory.createTitledBorder("Prix du pack :"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_ok) {
			
			try {
				nbPack.commitEdit();
				nbProd.commitEdit();
				prix.setText(prix.getText().replaceAll("[.]", ","));
				prix.commitEdit();
				if(prix.getValue() instanceof Long)
					pack = new Pack(((Long)nbPack.getValue()).intValue(),((Long) nbProd.getValue()).intValue(),((Long) prix.getValue()).doubleValue());
				else if(prix.getValue() instanceof Double)
					pack = new Pack(((Long)nbPack.getValue()).intValue(),((Long) nbProd.getValue()).intValue(),((Double) prix.getValue()).doubleValue());
				else
					JOptionPane.showMessageDialog(null, "Donnée invalide");
					
				this.dispose();
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(null, "Donnée invalide");
			}
			
		}
	}

	public Pack showDialog() {
		this.setVisible(true);
		return pack;
	}


	public Boutton getBtn_ok() {
		return btn_ok;
	}

}
