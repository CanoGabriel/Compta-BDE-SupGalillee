package Default;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import IHMComponent.Boutton;
import IHMComponent.DirReader;

public class PopupImportModele extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7587147658973211346L;

	private JComboBox<String> choix = new JComboBox<String>();
	private Boutton btn_ok = new Boutton("OK");
	private FeuilleCourse data = null;
	
	private JPanel north = new JPanel();
	public PopupImportModele() {
		super((JFrame)null,"Choix du fichier",true);
		
		DirReader dr = new DirReader();
		for (File i : dr.listFiles("Modele")){
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
	
	public FeuilleCourse showDialog(){
		this.setVisible(true);
		return data;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_ok){
			String path = "Modele/"+(String) choix.getSelectedItem();
			data = new FeuilleCourse(new File(path));
			dispose();
		}
	}
}
