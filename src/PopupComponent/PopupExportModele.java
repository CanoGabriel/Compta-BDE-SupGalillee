package PopupComponent;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Default.FeuilleCourse;
import Default.FeuilleInventaire;
import IHMComponent.Boutton;

public class PopupExportModele extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7059908656710301688L;
	
	public static boolean COURSE = true;
	public static boolean INVENTAIRE = false;
	
	private Boutton btn_ok = new Boutton("OK");
	private JFormattedTextField nom = null;
	private JLabel ext = new JLabel(".mod");
	
	private JPanel north = new JPanel();
	private JPanel north_center = new JPanel();
	private JPanel north_east = new JPanel();
	
	private final boolean type;
	
	private final FeuilleCourse data_course;
	private final FeuilleInventaire data_inventaire;
	
	public PopupExportModele(FeuilleCourse config,boolean type) {
		super((JFrame)null,"Saisie",true);
		data_course = config;
		data_inventaire = null;
		init();
		this.type = type;
	}
	
	private void init() {
		this.setResizable(false);
		this.setSize(600, 200);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		
		btn_ok.addActionListener(this);
		
		Pattern pt = Pattern.compile("\\w*");
		nom = new JFormattedTextField(pt);
		nom.setText("");
		
		this.setLayout(new BorderLayout());
		north.setLayout(new BorderLayout());
		north_center.setLayout(new BorderLayout());
		
		this.add(btn_ok,BorderLayout.SOUTH);
		north_center.add(nom);
		north_east.add(ext, FlowLayout.LEFT);
		
		this.add(north, BorderLayout.NORTH);
		north.add(north_center, BorderLayout.CENTER);
		north.add(north_east, BorderLayout.EAST);
		
		north.setBorder(BorderFactory.createTitledBorder("Entrer le nom du modele :"));
		
	}
	public PopupExportModele(FeuilleInventaire config,boolean type) {
		super((JFrame)null,"Saisie",true);
		data_inventaire = config;
		data_course = null;
		init();
		this.type = type;
	}
	

	public void showDialog(){
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_ok){
			try {
				String temp = (type == PopupExportModele.COURSE)?"Modele course":"Modele inventaire";
				nom.commitEdit();
				File f = new File(temp);
				if(!f.exists())
					f.mkdir();
				String message = "Nom : " + nom.getText() +"\n Path : " + f.getPath()+"/"+nom.getText()+".mod"; 
				int option = JOptionPane.showConfirmDialog(null, message,"Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(option == JOptionPane.OK_OPTION){
					if(type == PopupExportModele.COURSE)
						data_course.writeModele(nom.getText());
					else
						data_inventaire.writeModele(nom.getText());
					dispose();
				}
				else
					dispose();
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur ", JOptionPane.ERROR_MESSAGE);
				
			}
		}
	}

}
