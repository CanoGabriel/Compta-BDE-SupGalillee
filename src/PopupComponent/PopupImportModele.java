package PopupComponent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Default.FeuilleCourse;
import Default.FeuilleInventaire;
import IHMComponent.Boutton;
import IHMComponent.DirReader;

public class PopupImportModele extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7587147658973211346L;

	public static boolean COURSE = true;
	public static boolean INVENTAIRE = false;

	private JComboBox<String> choix = new JComboBox<String>();
	private Boutton btn_ok = new Boutton("OK");
	private FeuilleCourse data_course = null;
	private FeuilleInventaire data_inventaire = null;
	
	private final boolean type;

	private JPanel north = new JPanel();
	public PopupImportModele(boolean type) {
		super((JFrame)null,"Choix du fichier",true);

		this.type = type;
		
		DirReader dr = new DirReader();
		String temp = (type == PopupImportModele.COURSE)?"Modele course":"Modele inventaire";

		for (File i : dr.listFiles(temp)){
			choix.addItem(i.getName());
		}

		btn_ok.addActionListener(this);

		this.setResizable(false);
		this.setSize(300, 100);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setLayout(new BorderLayout());
		north.setLayout(new BorderLayout());

		this.add(north,BorderLayout.CENTER);
		this.add(btn_ok, BorderLayout.SOUTH);
		north.add(choix, BorderLayout.CENTER);
	}

	public FeuilleCourse showDialogCourse(){
		this.setVisible(true);
		return data_course;
	}
	public FeuilleInventaire showDialogInventaire(){
		this.setVisible(true);
		return data_inventaire;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_ok){
			if(type == PopupImportModele.COURSE) {
				String path = "Modele course/"+(String) choix.getSelectedItem();
				data_course = new FeuilleCourse(new File(path));
				dispose();
			}
			else {
				String path = "Modele inventaire/"+(String) choix.getSelectedItem();
				data_inventaire = new FeuilleInventaire(new File(path));
				dispose();
			}
		}
	}
}
