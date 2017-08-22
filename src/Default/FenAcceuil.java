package Default;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

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

//	private JPanel modif_config = new JPanel();
//	private JPanel modcf_center = new JPanel();
//	private JPanel modcf_center_c1 = new JPanel();
//	private JPanel modcf_center_c2 = new JPanel();
//	private JPanel modcf_center_c2_center = new JPanel();
//	private JPanel modcf_center_c2_center_l1 = new JPanel();
//	private JPanel modcf_center_c2_center_l2 = new JPanel();
//	private JPanel modcf_center_c2_center_l3 = new JPanel();
//	private JPanel modcf_center_c2_south = new JPanel();
//	private JPanel modcf_south = new JPanel();
//	private JPanel modcf_south_c1 = new JPanel();
//	private JPanel modcf_south_c2 = new JPanel();
//	private JPanel modcf_south_c3 = new JPanel();
//
//	private Boutton modcf_btn_ac= new Boutton("Acceuil");
//
//	private JTextField modcf_txtf_categorie = new JTextField();
//	private JTextField modcf_txtf_nom = new JTextField();
//	private JFormattedTextField modcf_txtf_prix = null;
//	private Boutton modcf_btn_ok = new Boutton("OK");
//	private Boutton modcf_btn_ajouter = new Boutton("Ajouter");
//	private Boutton modcf_btn_valider = new Boutton("Valider");

//	private JTree completTree = null;

	public FenAcceuil() {
		data_config = new Configuration();
		this.setTitle("Comptabilite BDE 2017");
		this.setResizable(true);
		this.setSize(600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		initAll();
		this.setContentPane(acceuil);
		this.setVisible(true);
	}

	public void initAll() {
//		initModConfigIHM();
	}

//	private void initModConfigIHM() {
		
//		modcf_btn_ac.addActionListener(this);
//		modcf_btn_ajouter.addActionListener(this);
//		modcf_btn_ok.addActionListener(this);
//		modcf_btn_valider.addActionListener(this);
//		modif_config.setLayout(new BorderLayout());
//		modcf_center.setLayout(new GridLayout(1, 2));
//		modcf_center_c1.setLayout(new BorderLayout());
//		modcf_center_c2.setLayout(new BorderLayout());
//		modcf_center_c2_center.setLayout(new GridLayout(3, 1));
//		modcf_center_c2_center_l1.setLayout(new BorderLayout());
//		modcf_center_c2_center_l2.setLayout(new BorderLayout());
//		modcf_center_c2_center_l3.setLayout(new BorderLayout());
//		modcf_center_c2_south.setLayout(new BorderLayout());
//		modcf_south.setLayout(new GridLayout(1, 3));
//		modcf_south_c1.setLayout(new BorderLayout());
//		modcf_south_c2.setLayout(new BorderLayout());
//		modcf_south_c3.setLayout(new BorderLayout());
//		
//		modcf_center_c1.add(new JScrollPane(completTree),BorderLayout.CENTER);
//		modcf_center_c2_center_l1.add(modcf_txtf_categorie, BorderLayout.CENTER);
//		modcf_center_c2_center_l2.add(modcf_txtf_nom, BorderLayout.CENTER);
//		NumberFormat nf=  NumberFormat.getNumberInstance();
//		nf.setMinimumIntegerDigits(1);
//		nf.setMinimumFractionDigits(0);
//		nf.setMaximumFractionDigits(2);
//		nf.setMaximumIntegerDigits(3);
//		modcf_txtf_prix = new JFormattedTextField(nf);
//		modcf_center_c2_center_l3.add(modcf_txtf_prix, BorderLayout.CENTER);
//		modcf_center_c2_south.add(modcf_btn_ok, BorderLayout.CENTER);
//		modcf_south_c1.add(modcf_btn_ac,BorderLayout.CENTER);
//		modcf_south_c2.add(modcf_btn_valider,BorderLayout.CENTER);
//		modcf_south_c3.add(modcf_btn_ajouter,BorderLayout.CENTER);
//		
//		modif_config.add(modcf_center, BorderLayout.CENTER);
//		modcf_center.add(modcf_center_c1);
//		modcf_center.add(modcf_center_c2);
//		modcf_center_c2.add(modcf_center_c2_center, BorderLayout.CENTER);
//		modcf_center_c2_center.add(modcf_center_c2_center_l1);
//		modcf_center_c2_center.add(modcf_center_c2_center_l2);
//		modcf_center_c2_center.add(modcf_center_c2_center_l3);
//		modcf_center_c2.add(modcf_center_c2_south, BorderLayout.SOUTH);
//		modif_config.add(modcf_south, BorderLayout.SOUTH);
//		modcf_south.add(modcf_south_c1);
//		modcf_south.add(modcf_south_c2);
//		modcf_south.add(modcf_south_c3);
//		
//		modcf_center_c1.setBorder(BorderFactory.createTitledBorder("Arbre des produit classe par categorie :"));
//		modcf_txtf_categorie.setBorder(BorderFactory.createTitledBorder("Categorie :"));
//		modcf_txtf_categorie.setSize(modcf_center_c2.getSize());
//		modcf_txtf_nom.setBorder(BorderFactory.createTitledBorder("Nom du produit :"));
//		modcf_txtf_nom.setPreferredSize(new Dimension(1000, 50));
//		modcf_txtf_prix.setBorder(BorderFactory.createTitledBorder("Prix :"));
//		modcf_txtf_prix.setPreferredSize(new Dimension(1000, 50));
//	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(((Boutton)e.getSource()).getName());
		if(e.getSource() == config.getBoutton(IHMConfigOverview.BTN_ACCEUIL) || e.getSource() == modcf_btn_ac) {
			this.setContentPane(acceuil);
			data_config.setDefaultSaveFilePath(config.getTxtf_path().getText());
			data_config.write();
			actualiser();
		}
		else if(e.getSource() == acceuil.getBoutton(IHMAcceuil.BTN_CONFIG)) {
			this.setContentPane(config);
			data_config = new Configuration();
			System.out.println(data_config);
			config.getTxtf_path().setText(data_config.getDefaultSaveFilePath());
			config.buildArbre(data_config);
			actualiser();
		}
		else if(e.getSource() == config.getBoutton(IHMConfigOverview.BTN_PATHOK)) {
			data_config.setDefaultSaveFilePath(config.getTxtf_path().getText());
//			System.out.println(data_config.getDefaultSaveFilePath());
		}
		else if (e.getSource() == config.getBoutton(IHMConfigOverview.BTN_MODIFIER)) {
			this.setContentPane(modif_config);
			actualiser();
		}
		else if (e.getSource() == modcf_btn_valider) {
			this.setContentPane(config);
			config.buildArbre(data_config);
			actualiser();
		}
		else if(e.getSource() == modcf_btn_ajouter) {
			Produit t = null;
			JOptionPane jop = new JOptionPane();
			PopupProduit pop = new PopupProduit(null,"test popup",true);
			t = pop.showDialog();
			int i = jop.showConfirmDialog(null,t.toString(), "Confirmer la saisie !!!",JOptionPane.YES_NO_OPTION);
			while(i != 0) {
				t = pop.showDialog();
				i = jop.showConfirmDialog(null,t.toString(), "Confirmer la saisie !!!",JOptionPane.YES_NO_OPTION);
			}
			data_config.addProduit(t);
			actualiser();
			
			System.out.println(data_config.categorie.toString());
		}
	}

	private void actualiser() {
		this.repaint();
		this.revalidate();
	}


}
