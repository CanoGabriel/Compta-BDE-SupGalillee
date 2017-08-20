import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
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
	private JPanel modcf_south = new JPanel();
	private JPanel modcf_south_c1 = new JPanel();
	private JPanel modcf_south_c2 = new JPanel();
	
	private Boutton cf_btn_modifier = new Boutton("Modifier");
	private Boutton cf_btn_ac= new Boutton("Acceuil");
	private Boutton cf_btn_pathOK = new Boutton("OK");
	private JTextField cf_txtf_path = new JTextField();

	private Boutton ac_btn_config = new Boutton("Configuration");
	private Boutton ac_btn_course = new Boutton("Nouvelle feuille de course");
	private Boutton ac_btn_inventaire = new Boutton("Nouvelle feuille d'inventaire");

	private JTree catTree = null;
	private ArrayList<DefaultMutableTreeNode> cf_list_cat_prod = new ArrayList<DefaultMutableTreeNode>();
	private JTree prodTree = null;
	
	

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
		initArbre();
		initAcceuil();
		initConfigIHM();
	}
	public void initConfigIHM() {
		cf_btn_ac.addActionListener(this);
		cf_btn_pathOK.addActionListener(this);
		cf_btn_modifier.addActionListener(this);

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

		cf_center_c1.add(new JScrollPane(catTree),BorderLayout.CENTER);
		cf_center_c1.setBorder(BorderFactory.createTitledBorder("Categories"));
		cf_center_c2.add(new JScrollPane(prodTree),BorderLayout.CENTER);
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
	public void initAcceuil() {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cf_btn_ac) {
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
			System.out.println(data_config.getDefaultSaveFilePath());
		}
	}

	private void actualiser() {
		this.repaint();
		this.revalidate();
	}


	private void initArbre() {
		for(String i : data_config.categorie) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(i); 
			for(InnerTemp j : data_config.list) {
				if(j.categorie.equals(i)) {
					node.add(new DefaultMutableTreeNode(j.produit.getNom()));
				}
			}
			cf_list_cat_prod.add(node);
		}
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode("root");
		for(String i : data_config.categorie)
			racine.add(new DefaultMutableTreeNode(i));
		catTree = new JTree(racine);
		catTree.setRootVisible(false);
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
