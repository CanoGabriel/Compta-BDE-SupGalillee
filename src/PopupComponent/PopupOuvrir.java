package PopupComponent;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Default.FeuilleCourse;
import Default.FeuilleInventaire;
import IHMComponent.Boutton;
import IHMComponent.DirReader;

public class PopupOuvrir extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2600075951652289368L;
	public static boolean		COURSE				= true;
	public static boolean		INVENTAIRE			= false;

	private JComboBox<String>	choix			= new JComboBox<String>();
	private Boutton				btn_ok			= new Boutton("OK");
	private FeuilleCourse		data_course		= null;
	private FeuilleInventaire	data_inventaire	= null;

	private final boolean type;

	private JPanel north = new JPanel();

	public PopupOuvrir (boolean type) {

		super((JFrame) null, "Choix du fichier", true);

		this.type = type;

		DirReader dr = new DirReader();
		String temp = (type == PopupOuvrir.COURSE) ? "Course" : "Inventaire";

		for (File i : dr.listFiles(temp)) {
			choix.addItem(i.getName());
		}

		btn_ok.addActionListener(this);

		this.setResizable(false);
		this.setSize(300, 100);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setLayout(new BorderLayout());
		north.setLayout(new BorderLayout());

		this.add(north, BorderLayout.CENTER);
		this.add(btn_ok, BorderLayout.SOUTH);
		north.add(choix, BorderLayout.CENTER);
	}

	public FeuilleCourse showDialogCourse () {

		this.setVisible(true);
		return data_course;
	}

	public FeuilleInventaire showDialogInventaire () {

		this.setVisible(true);
		return data_inventaire;
	}

	@Override
	public void actionPerformed (ActionEvent e) {

		if (e.getSource() == btn_ok) {
			if (type == PopupImportModele.COURSE) {
				String path = "Course/" + (String) choix.getSelectedItem();
				data_course = new FeuilleCourse(new Date());
				data_course.readXLS(new File(path));
				dispose();
			}
			else {
				String path = "Inventaire/" + (String) choix.getSelectedItem();
				data_inventaire = new FeuilleInventaire(new Date());
				data_inventaire.readXLS(new File(path));
				dispose();
			}
		}
	}

}
