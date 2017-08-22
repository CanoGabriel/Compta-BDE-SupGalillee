package IHMComponent;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import Default.FenAcceuil;


public class IHMAcceuil extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5191241887032231923L;
	public static String BTN_CONFIG = "configuration";
	public static String BTN_COURSE = "course";
	public static String BTN_INVENTAIRE = "inventaire";
	
	private JPanel l1 = new JPanel();
	private JPanel l2 = new JPanel();
	private JPanel l3 = new JPanel();
	
	private Boutton btn_config = new Boutton("Configuration");
	private Boutton btn_course = new Boutton("Nouvelle feuille de course");
	private Boutton btn_inventaire = new Boutton("Nouvelle feuille d'inventaire");
	private ArrayList<Boutton> listBoutton = new ArrayList<Boutton>();
	
	public IHMAcceuil(FenAcceuil parent ){
		btn_config.addActionListener(parent);

		this.setLayout(new GridLayout(3, 1));
		l1.setLayout(new BorderLayout());
		l2.setLayout(new BorderLayout());
		l3.setLayout(new BorderLayout());


		l1.add(btn_config, BorderLayout.CENTER);
		btn_config.setName(BTN_CONFIG);
		listBoutton.add(btn_config);
		l2.add(btn_course, BorderLayout.CENTER);
		btn_course.setName(BTN_COURSE);
		listBoutton.add(btn_course);
		l3.add(btn_inventaire, BorderLayout.CENTER);
		btn_inventaire.setName(BTN_INVENTAIRE);
		listBoutton.add(btn_inventaire);

		this.add(l1);
		this.add(l2);
		this.add(l3);
	}
	
	public Boutton getBoutton(String name){
		for(Boutton i : listBoutton){
			if(name.equals(i.getName()))
				return i;
		}
		return null;
	}
}