import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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

public class FenAcceuil extends JFrame implements ActionListener{
	private static final long serialVersionUID = 4358673801877559732L;
	@SuppressWarnings("unused")
	private Configuration data_config = null;

	private JPanel acceuil = new JPanel();
	private JPanel config = new JPanel();

	private JPanel ac_l1 = new JPanel();
	private JPanel ac_l2 = new JPanel();
	private JPanel ac_l3 = new JPanel();

	private JPanel cf_north = new JPanel();
	private JPanel cf_center = new JPanel();
	private JPanel cf_south= new JPanel();
	private JPanel cf_center_c1 = new JPanel();
	private JPanel cf_center_c2 = new JPanel();
	private JPanel cf_south_c1 = new JPanel();
	private JPanel cf_south_c2 = new JPanel();

	private JPanel modif_config = new JPanel();
	private JPanel modcf_center = new JPanel();
	private JPanel modcf_center_c1 = new JPanel();
	private JPanel modcf_center_c2 = new JPanel();
	private JPanel modcf_center_c2_center = new JPanel();
	private JPanel modcf_center_c2_center_l1 = new JPanel();
	private JPanel modcf_center_c2_center_l2 = new JPanel();
	private JPanel modcf_center_c2_center_l3 = new JPanel();
	private JPanel modcf_center_c2_south = new JPanel();
	private JPanel modcf_south = new JPanel();
	private JPanel modcf_south_c1 = new JPanel();
	private JPanel modcf_south_c2 = new JPanel();
	private JPanel modcf_south_c3 = new JPanel();

	private Boutton cf_btn_modifier = new Boutton("Modifier");
	private Boutton cf_btn_ac= new Boutton("Acceuil");
	private Boutton modcf_btn_ac= new Boutton("Acceuil");
	private Boutton cf_btn_pathOK = new Boutton("OK");
	private JTextField cf_txtf_path = new JTextField();

	private JTextField modcf_txtf_categorie = new JTextField();
	private JTextField modcf_txtf_nom = new JTextField();
	private JFormattedTextField modcf_txtf_prix = null;
	private Boutton modcf_btn_ok = new Boutton("OK");
	private Boutton modcf_btn_ajouter = new Boutton("Ajouter");
	private Boutton modcf_btn_valider = new Boutton("Valider");

	private Boutton ac_btn_config = new Boutton("Configuration");
	private Boutton ac_btn_course = new Boutton("Nouvelle feuille de course");
	private Boutton ac_btn_inventaire = new Boutton("Nouvelle feuille d'inventaire");

	private JScrollPane catTree = null;
	private ArrayList<DefaultMutableTreeNode> cf_list_cat_prod = new ArrayList<DefaultMutableTreeNode>();
	private JScrollPane prodTree = new JScrollPane();
	private JTree completTree = null;



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
		// TODO Auto-generated constructor stub
	}

	public void initAll() {
		initModConfigIHM();
		initArbre();
		initConfigIHM();
		initAcceuil();
	}

	private void initConfigIHM() {
		cf_btn_ac.addActionListener(this);
		modcf_btn_ac.addActionListener(this);
		cf_btn_pathOK.addActionListener(this);
		cf_btn_modifier.addActionListener(this);
		modcf_btn_valider.addActionListener(this);
		modcf_btn_ok.addActionListener(this);
		modcf_btn_ajouter.addActionListener(this);

		config.setLayout(new BorderLayout());
		cf_north.setLayout(new BorderLayout());
		cf_center.setLayout(new GridLayout(1, 2));
		cf_center_c1.setLayout(new BorderLayout());
		cf_center_c2.setLayout(new BorderLayout());
		cf_south.setLayout(new GridLayout(1, 2));
		cf_south_c1.setLayout(new BorderLayout());
		cf_south_c2.setLayout(new BorderLayout());

		cf_north.add(cf_txtf_path, BorderLayout.CENTER);
		cf_north.add(cf_btn_pathOK, BorderLayout.EAST);

		cf_center_c1.add(catTree,BorderLayout.CENTER);
		cf_center_c1.setBorder(BorderFactory.createTitledBorder("Categories"));
		cf_center_c2.add(prodTree,BorderLayout.CENTER);
		cf_center_c2.setBorder(BorderFactory.createTitledBorder("Produit"));

		cf_south_c1.add(cf_btn_ac, BorderLayout.CENTER);
		cf_south_c2.add(cf_btn_modifier,BorderLayout.CENTER);


		config.add(cf_north, BorderLayout.NORTH);
		config.add(cf_center, BorderLayout.CENTER);
		config.add(cf_south, BorderLayout.SOUTH);

		cf_center.add(cf_center_c1);
		cf_center.add(cf_center_c2);

		cf_south.add(cf_south_c1);
		cf_south.add(cf_south_c2);

		cf_txtf_path.setBorder(BorderFactory.createTitledBorder("Chemin par default pour l'enregistrement des fichiers :"));
	}

	private void initAcceuil() {
		ac_btn_config.addActionListener(this);

		acceuil.setLayout(new GridLayout(3, 1));
		ac_l1.setLayout(new BorderLayout());
		ac_l2.setLayout(new BorderLayout());
		ac_l3.setLayout(new BorderLayout());


		ac_l1.add(ac_btn_config, BorderLayout.CENTER);
		ac_l2.add(ac_btn_course, BorderLayout.CENTER);
		ac_l3.add(ac_btn_inventaire, BorderLayout.CENTER);

		acceuil.add(ac_l1);
		acceuil.add(ac_l2);
		acceuil.add(ac_l3);
	}

	private void initModConfigIHM() {
		modif_config.setLayout(new BorderLayout());
		modcf_center.setLayout(new GridLayout(1, 2));
		modcf_center_c1.setLayout(new BorderLayout());
		modcf_center_c2.setLayout(new BorderLayout());
		modcf_center_c2_center.setLayout(new GridLayout(3, 1));
		modcf_center_c2_center_l1.setLayout(new BorderLayout());
		modcf_center_c2_center_l2.setLayout(new BorderLayout());
		modcf_center_c2_center_l3.setLayout(new BorderLayout());
		modcf_center_c2_south.setLayout(new BorderLayout());
		modcf_south.setLayout(new GridLayout(1, 3));
		modcf_south_c1.setLayout(new BorderLayout());
		modcf_south_c2.setLayout(new BorderLayout());
		modcf_south_c3.setLayout(new BorderLayout());
		
		modcf_center_c1.add(new JScrollPane(completTree),BorderLayout.CENTER);
		modcf_center_c2_center_l1.add(modcf_txtf_categorie, BorderLayout.CENTER);
		modcf_center_c2_center_l2.add(modcf_txtf_nom, BorderLayout.CENTER);
		NumberFormat nf=  NumberFormat.getNumberInstance();
		nf.setMinimumIntegerDigits(1);
		nf.setMinimumFractionDigits(0);
		nf.setMaximumFractionDigits(2);
		nf.setMaximumIntegerDigits(3);
		modcf_txtf_prix = new JFormattedTextField(nf);
		modcf_center_c2_center_l3.add(modcf_txtf_prix, BorderLayout.CENTER);
		modcf_center_c2_south.add(modcf_btn_ok, BorderLayout.CENTER);
		modcf_south_c1.add(modcf_btn_ac,BorderLayout.CENTER);
		modcf_south_c2.add(modcf_btn_valider,BorderLayout.CENTER);
		modcf_south_c3.add(modcf_btn_ajouter,BorderLayout.CENTER);
		
		modif_config.add(modcf_center, BorderLayout.CENTER);
		modcf_center.add(modcf_center_c1);
		modcf_center.add(modcf_center_c2);
		modcf_center_c2.add(modcf_center_c2_center, BorderLayout.CENTER);
		modcf_center_c2_center.add(modcf_center_c2_center_l1);
		modcf_center_c2_center.add(modcf_center_c2_center_l2);
		modcf_center_c2_center.add(modcf_center_c2_center_l3);
		modcf_center_c2.add(modcf_center_c2_south, BorderLayout.SOUTH);
		modif_config.add(modcf_south, BorderLayout.SOUTH);
		modcf_south.add(modcf_south_c1);
		modcf_south.add(modcf_south_c2);
		modcf_south.add(modcf_south_c3);
		
		modcf_center_c1.setBorder(BorderFactory.createTitledBorder("Arbre des produit classe par categorie :"));
		modcf_txtf_categorie.setBorder(BorderFactory.createTitledBorder("Categorie :"));
		modcf_txtf_categorie.setSize(modcf_center_c2.getSize());
		modcf_txtf_nom.setBorder(BorderFactory.createTitledBorder("Nom du produit :"));
		modcf_txtf_nom.setPreferredSize(new Dimension(1000, 50));
		modcf_txtf_prix.setBorder(BorderFactory.createTitledBorder("Prix :"));
		modcf_txtf_prix.setPreferredSize(new Dimension(1000, 50));
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cf_btn_ac || e.getSource() == modcf_btn_ac) {
			this.setContentPane(acceuil);
			data_config.write();
			actualiser();
		}
		else if(e.getSource() == ac_btn_config) {
			this.setContentPane(config);
			data_config = new Configuration();
			System.out.println(data_config);
			cf_txtf_path.setText(data_config.getDefaultSaveFilePath());
			actualiser();
		}
		else if(e.getSource() == cf_btn_pathOK) {
			data_config.setDefaultSaveFilePath(cf_txtf_path.getText());
//			System.out.println(data_config.getDefaultSaveFilePath());
		}
		else if (e.getSource() == cf_btn_modifier) {
			this.setContentPane(modif_config);
			actualiser();
		}
		else if (e.getSource() == modcf_btn_valider) {
			this.setContentPane(config);
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
			initArbre();
			actualiser();
			
			System.out.println(data_config.categorie.toString());
		}
	}

	private void actualiser() {
		this.repaint();
		this.revalidate();
	}


	private void initArbre() {
		for(String i : data_config.categorie) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(i); 
			for(Produit j : data_config.listProduit) {
				if(j.getCategorie().equals(i)) {
					node.add(new DefaultMutableTreeNode(j.getNom()));
					System.out.println("initArbre : "+j);
				}
			}
			cf_list_cat_prod.add(node);
		}
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode("root");
		for(String i : data_config.categorie)
			racine.add(new DefaultMutableTreeNode(i));
		JTree temp = new JTree(racine);
		temp.setRootVisible(false);
		temp.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent event) {
				DefaultMutableTreeNode racine2 = new DefaultMutableTreeNode("root");
				if(temp.getLastSelectedPathComponent() != null){
					for(Produit i : data_config.getProduitByCategorie(temp.getLastSelectedPathComponent().toString())) {
						racine2.add(new DefaultMutableTreeNode(i.nom +"("+i.prixUnitaire+"€ )"));
					}
					JTree temp2 = new JTree(racine2);
					temp2.setRootVisible(false);
					prodTree = new JScrollPane(temp2);
					cf_center_c2.removeAll();
					cf_center_c2.add(prodTree, BorderLayout.CENTER);
					actualiser();
		        }
		      }
		});
		catTree = new JScrollPane(temp);
		cf_center_c1.removeAll();
		cf_center_c1.add(catTree,BorderLayout.CENTER);
	}

	private class Boutton extends JButton implements MouseListener{

		/**
		 * 
		 */
		private static final long serialVersionUID = -4372214834361000899L;

		public Boutton(String string) {
			super(string);
		}

		@SuppressWarnings("unused")
		public Boutton() {
			super();
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
