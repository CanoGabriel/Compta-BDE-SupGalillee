package IHMComponent;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import Default.Configuration;
import Default.FenAcceuil;

public class IHMConfigOverview extends JPanel implements IHMBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7599039485321765934L;

	public static String	BTN_MODIFIER	= "modifier";
	public static String	BTN_ACCEUIL		= "acceuil";
	public static String	BTN_PATHOK		= "pathOK";

	// public JTextField getTxtf_path () {
	//
	// return txtf_path;
	// }

	private JPanel	north		= new JPanel();
	private JPanel	center		= new JPanel();
	private JPanel	south		= new JPanel();
	private JPanel	center_c1	= new JPanel();
	private JPanel	center_c2	= new JPanel();
	// private JPanel south_c1 = new JPanel();
	// private JPanel south_c2 = new JPanel();

	private JPanel north_east = new JPanel();

	private Boutton	btn_modifier	= new Boutton("Modifier/Ajouter");
	private Boutton	btn_ac			= new Boutton("Retour a l'acceuil");
	// private Boutton btn_pathOK = new Boutton("OK");
	// private JTextField txtf_path = new JTextField();

	private JScrollPane			catTree		= new JScrollPane();
	private JScrollPane			prodTree	= new JScrollPane();
	private ArrayList<Boutton>	listBoutton	= new ArrayList<Boutton>();

	public IHMConfigOverview (FenAcceuil parent) {

		btn_ac.addActionListener(parent);
		btn_ac.setName(BTN_ACCEUIL);
		listBoutton.add(btn_ac);
		// btn_pathOK.addActionListener(parent);
		// btn_pathOK.setName(BTN_PATHOK);
		// listBoutton.add(btn_pathOK);
		btn_modifier.addActionListener(parent);
		btn_modifier.setName(BTN_MODIFIER);
		listBoutton.add(btn_modifier);

		this.setLayout(new BorderLayout());
		north.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(1, 2));
		center_c1.setLayout(new BorderLayout());
		center_c2.setLayout(new BorderLayout());
		south.setLayout(new BorderLayout());
		// south.setLayout(new GridLayout(1, 2));
		// south_c1.setLayout(new BorderLayout());
		// south_c2.setLayout(new BorderLayout());

		// north.add(txtf_path, BorderLayout.CENTER);
		// north_east.add(btn_pathOK);
		north.add(btn_ac, BorderLayout.CENTER);

		center_c1.add(catTree, BorderLayout.CENTER);
		center_c1.setBorder(BorderFactory.createTitledBorder("Categories"));
		center_c2.add(prodTree, BorderLayout.CENTER);
		center_c2.setBorder(BorderFactory.createTitledBorder("Produit"));

		// south_c1.add(btn_ac, BorderLayout.CENTER);
		// south_c2.add(btn_modifier, BorderLayout.CENTER);
		south.add(btn_modifier, BorderLayout.CENTER);

		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);

		north.add(north_east, BorderLayout.EAST);
		center.add(center_c1);
		center.add(center_c2);

		// south.add(south_c1);
		// south.add(south_c2);

		// txtf_path.setBorder(BorderFactory.createTitledBorder("Chemin par
		// default pour l'enregistrement des fichiers :"));

	}

	public void buildArbre (Configuration data) {

		JTree temp = data.buildTree(null, false);
		temp.setRootVisible(false);
		temp.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged (TreeSelectionEvent event) {

				JTree temp2;
				if (temp.getLastSelectedPathComponent() != null) {
					temp2 = data.buildTree(temp.getLastSelectedPathComponent().toString(), true);
					temp2.setRootVisible(false);
					prodTree = new JScrollPane(temp2);
					center_c2.removeAll();
					center_c2.add(prodTree, BorderLayout.CENTER);
					actualiser();
				}
				else {
					prodTree = new JScrollPane(new JTree());
				}
			}
		});
		catTree = new JScrollPane(temp);
		center_c1.removeAll();
		center_c1.add(catTree, BorderLayout.CENTER);
	}

	public void reInitArbre () {

		prodTree = new JScrollPane();
		center_c2.removeAll();
		center_c2.add(prodTree, BorderLayout.CENTER);
		actualiser();
	}

	public void actualiser () {

		this.repaint();
		this.revalidate();
	}

	public Boutton getBoutton (String name) {

		for (Boutton i : listBoutton) {
			if (name.equals(i.getName())) return i;
		}
		return null;
	}
}
