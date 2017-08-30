package PopupComponent;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import IHMComponent.Boutton;

public class PopupDate extends JDialog implements ActionListener {

	private Date				d					= new Date();
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6011373255368842196L;

	private JPanel	content	= new JPanel();
	private JPanel	north	= new JPanel();
	private JPanel	south	= new JPanel();
	private JPanel	c1		= new JPanel();
	private JPanel	c2		= new JPanel();
	private JPanel	c3		= new JPanel();

	private JComboBox<Integer>	cb_day		= new JComboBox<Integer>();
	private JComboBox<Integer>	cb_month	= new JComboBox<Integer>();
	private JComboBox<Integer>	cb_year		= new JComboBox<Integer>();
	private Boutton				validation	= new Boutton("Valider choix");

	private SimpleDateFormat	sdf	= new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
	private GregorianCalendar	cal	= new GregorianCalendar();
	private int []				dayPerMonth;

	public PopupDate () {

		super((JFrame) null, "Ajout d'un produit", true);
		this.setTitle("Saisir les informations du produit");
		this.setResizable(false);
		this.setSize(600, 200);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(content);

		content.setLayout(new BorderLayout());
		north.setLayout(new GridLayout(1, 3));
		c1.setLayout(new BorderLayout());
		c2.setLayout(new BorderLayout());
		c3.setLayout(new BorderLayout());

		south.add(validation);
		c1.add(cb_year);
		c2.add(cb_month);
		c3.add(cb_day);

		content.add(north, BorderLayout.NORTH);
		north.add(c1);
		north.add(c2);
		north.add(c3);
		content.add(south, BorderLayout.SOUTH);

		for (int i = 0 ; i < 2 ; i++)
			cb_year.addItem(new Integer(Calendar.YEAR + i));
		dayPerMonth = new int [] {31 , ((cal.isLeapYear(Calendar.YEAR)) ? 29 : 28) , 31 , 30 , 31 , 30 , 31 , 31 , 30 , 31 , 30 , 31};
		for (int i = 1 ; i <= 12 ; i++)
			cb_month.addItem(new Integer(i));
		for (int i = 1 ; i <= dayPerMonth[0] ; i++)
			cb_day.addItem(new Integer(i));

		c1.setBorder(BorderFactory.createTitledBorder("Annee"));
		c2.setBorder(BorderFactory.createTitledBorder("Mois"));
		c3.setBorder(BorderFactory.createTitledBorder("Jour"));
		north.setBorder(BorderFactory.createTitledBorder("Choix de la date : "));

	}

	public Date showDialog () {

		this.setVisible(true);
		return this.d;
	}

	@Override
	public void actionPerformed (ActionEvent e) {

		if (e.getSource() == validation) {
			String d1 = sdf.format(d);
			String d2 = "" + cb_year.getSelectedItem() + "," + cb_month.getSelectedItem() + "," + cb_day.getSelectedItem();
			d2 += "-" + d1.split("-")[3];
			d2 += "-" + d1.split("-")[4];
			d2 += "-" + d1.split("-")[5];
			Date temp = null;
			try {
				temp = sdf.parse(d2);
			}
			catch (ParseException e1) {
				e1.printStackTrace();
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			int option = JOptionPane.showConfirmDialog(null, "Valider-vous la date suivante ?\n" + sdf1.format(temp), "Validation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
				d = temp;
				dispose();
			}
		}
		else if (e.getSource() == cb_year || e.getSource() == cb_month) {
			dayPerMonth = new int [] {31 , ((cal.isLeapYear(Calendar.YEAR)) ? 29 : 28) , 31 , 30 , 31 , 30 , 31 , 31 , 30 , 31 , 30 , 31};
			int day = cb_day.getSelectedIndex();
			for (int i = 1 ; i <= dayPerMonth[day] ; i++)
				cb_day.addItem(new Integer(i));
			if (day < dayPerMonth[cb_month.getSelectedIndex()])
				cb_day.setSelectedIndex(day);
			else cb_day.setSelectedIndex(0);
		}
	}
}
