package IHMComponent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;

import Default.FenAcceuil;

public class IHMConfigModifPanel extends JPanel implements IHMBase{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4948756785830503708L;
	private JPanel center = new JPanel();
	private JPanel center_c1 = new JPanel();
	private JPanel center_c2 = new JPanel();
	private JPanel center_c2_center = new JPanel();
	private JPanel center_c2_center_l1 = new JPanel();
	private JPanel center_c2_center_l2 = new JPanel();
	private JPanel center_c2_center_l3 = new JPanel();
	private JPanel center_c2_south = new JPanel();
	private JPanel south = new JPanel();
	private JPanel south_c1 = new JPanel();
	private JPanel south_c2 = new JPanel();
	private JPanel south_c3 = new JPanel();

	private Boutton btn_ac= new Boutton("Acceuil");

	private JTextField txtf_categorie = new JTextField();
	private JTextField txtf_nom = new JTextField();
	private JFormattedTextField txtf_prix = null;
	private Boutton btn_ok = new Boutton("OK");
	private Boutton btn_ajouter = new Boutton("Ajouter");
	private Boutton btn_valider = new Boutton("Valider");
	
	private JTree completTree = null;


	public IHMConfigModifPanel(FenAcceuil parent){
		btn_ac.addActionListener(parent);
		btn_ajouter.addActionListener(parent);
		btn_ok.addActionListener(parent);
		btn_valider.addActionListener(parent);
		this.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(1, 2));
		center_c1.setLayout(new BorderLayout());
		center_c2.setLayout(new BorderLayout());
		center_c2_center.setLayout(new GridLayout(3, 1));
		center_c2_center_l1.setLayout(new BorderLayout());
		center_c2_center_l2.setLayout(new BorderLayout());
		center_c2_center_l3.setLayout(new BorderLayout());
		center_c2_south.setLayout(new BorderLayout());
		south.setLayout(new GridLayout(1, 3));
		south_c1.setLayout(new BorderLayout());
		south_c2.setLayout(new BorderLayout());
		south_c3.setLayout(new BorderLayout());
		
		center_c1.add(new JScrollPane(completTree),BorderLayout.CENTER);
		center_c2_center_l1.add(txtf_categorie, BorderLayout.CENTER);
		center_c2_center_l2.add(txtf_nom, BorderLayout.CENTER);
		NumberFormat nf=  NumberFormat.getNumberInstance();
		nf.setMinimumIntegerDigits(1);
		nf.setMinimumFractionDigits(0);
		nf.setMaximumFractionDigits(2);
		nf.setMaximumIntegerDigits(3);
		txtf_prix = new JFormattedTextField(nf);
		center_c2_center_l3.add(txtf_prix, BorderLayout.CENTER);
		center_c2_south.add(btn_ok, BorderLayout.CENTER);
		south_c1.add(btn_ac,BorderLayout.CENTER);
		south_c2.add(btn_valider,BorderLayout.CENTER);
		south_c3.add(btn_ajouter,BorderLayout.CENTER);
		
		this.add(center, BorderLayout.CENTER);
		center.add(center_c1);
		center.add(center_c2);
		center_c2.add(center_c2_center, BorderLayout.CENTER);
		center_c2_center.add(center_c2_center_l1);
		center_c2_center.add(center_c2_center_l2);
		center_c2_center.add(center_c2_center_l3);
		center_c2.add(center_c2_south, BorderLayout.SOUTH);
		this.add(south, BorderLayout.SOUTH);
		south.add(south_c1);
		south.add(south_c2);
		south.add(south_c3);
		
		center_c1.setBorder(BorderFactory.createTitledBorder("Arbre des produit classe par categorie :"));
		txtf_categorie.setBorder(BorderFactory.createTitledBorder("Categorie :"));
		txtf_categorie.setSize(center_c2.getSize());
		txtf_nom.setBorder(BorderFactory.createTitledBorder("Nom du produit :"));
		txtf_nom.setPreferredSize(new Dimension(1000, 50));
		txtf_prix.setBorder(BorderFactory.createTitledBorder("Prix :"));
		txtf_prix.setPreferredSize(new Dimension(1000, 50));
	}


	@Override
	public void actualiser() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Boutton getBoutton(String name) {
		for(Boutton i : listBoutton ){
			if(name.equals(i.getName()))
				return i;
		}
		return null;
	}
}
