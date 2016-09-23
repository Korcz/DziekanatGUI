package korczak.jakub.panel.staystyka;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import korczak.jakub.bazadanych.BazaDanych;
import korczal.jakub.modele.CustomComboBoxModel;
import korczal.jakub.modele.CustomComboBoxModelMiasto;

public class PanelStatystyka extends JPanel
{
	private JLabel lNajstrszyStudent;
	private JLabel lNajmlodyszyStudent;
	private JLabel lNajstarszaUczelnia;
	private JLabel lNajmlodyszaUczelnia;
	private JLabel lAvgWiekStudent;
	private JLabel lNajczesciejUczeszczanyRocznik;
	
	
	
	private JTextField tfNajmlodyszyStudent;
	private JTextField tfNajstarszyStudent;
	private JTextField tfNajstarszaUczelnia;
	private JTextField tfNajmlodyszaUczelnia;
	private JTextField tfAvgWiekStudent;
	private JTextField tfNajczesciejUczeszczanyRocznik;
	private JTextField tfMiastoIleStudentow; //?
	
	private CustomComboBoxModel modelCbMiastoIleStudentow;
	private JComboBox<String> cbMiasto;
	
	public PanelStatystyka()
	{
		super(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(); //zmienna ktora bedzie zarzadzala komponentem w siatce
		//robimy jedna zmienna dla wszystkich komponentow w danym GridBagLayout i tylko zmieniamy jej odpowiednie parametry
		
		//dwa ponizsze parametry powoduja ze komponenty sie rozchodza
		//gbc.weightx = 1;
		//gbc.weighty = 1;
		
		
		
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK, 3, true), "Statystyka", TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION, new Font("Courier New", Font.BOLD, 15), Color.ORANGE));
		
		
		gbc.insets = new Insets(20, 20, 20, 20);
		
		gbc.gridx = 0; //numer kolumny
		gbc.gridy = 0; //numer wiersza
		lNajstrszyStudent = new JLabel("Najstrszy Student:");
		add(lNajstrszyStudent, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		lNajmlodyszyStudent = new JLabel("Najmlodszy Student:");
		add(lNajmlodyszyStudent, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		lNajstarszaUczelnia = new JLabel("Najstrsza Uczelnia:");
		add(lNajstarszaUczelnia, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		lNajmlodyszaUczelnia = new JLabel("Najmlodzsza Uczelnia:");
		add(lNajmlodyszaUczelnia, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		lAvgWiekStudent = new JLabel("AvgWiek Studenta:");
		add(lAvgWiekStudent, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		lNajczesciejUczeszczanyRocznik = new JLabel("Najczesciej Uczeszczany Rocznik:");
		add(lNajczesciejUczeszczanyRocznik, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		Map<String, Integer> citiesMap = BazaDanych.miastoIleStudentow();
		Set<String> cities = citiesMap.keySet();
		CustomComboBoxModelMiasto cbm = new CustomComboBoxModelMiasto(new ArrayList<>(cities));
		cbMiasto = new JComboBox<>(cbm);
		cbMiasto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tfMiastoIleStudentow.setText(citiesMap.get(cbMiasto.getSelectedItem()) + "");
				
			}
		});
		add(cbMiasto, gbc); //?????????????????????
		
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 40; //rozszera komponent z lewej i prawej strony o 40 px
		gbc.ipady = 20; //rozszera komponent z gory i dolu strony o 40 px
		gbc.fill = GridBagConstraints.BOTH;//autowypelnia obszar przydzielony komponentowi w poziomie i pionie
		tfNajstarszyStudent = new JTextField(10);
		tfNajstarszyStudent.setEditable(false);
		add(tfNajstarszyStudent, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL; //autowypelnia obszar przydzielony komponentowi w poziomie		tfNajmlodyszyStudent = new JTextField(10);
		tfNajmlodyszyStudent = new JTextField(10);
		tfNajmlodyszyStudent.setEditable(false);
		add(tfNajmlodyszyStudent, gbc);
		
		
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.VERTICAL;//autowypelnia obszar przydzielony komponentowi w pionie
		tfNajstarszaUczelnia = new JTextField(10);
		tfNajstarszaUczelnia.setEditable(false);
		add(tfNajstarszaUczelnia, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.NONE;//autowypelnia obszaru wylaczone
		gbc.anchor = GridBagConstraints.PAGE_START;
		tfNajmlodyszaUczelnia = new JTextField(10);
		tfNajmlodyszaUczelnia.setEditable(false);
		add(tfNajmlodyszaUczelnia, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridwidth = 4;
		tfAvgWiekStudent = new JTextField(10);
		tfAvgWiekStudent.setEditable(false);
		add(tfAvgWiekStudent, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 0;
		tfNajczesciejUczeszczanyRocznik = new JTextField(10);
		tfNajczesciejUczeszczanyRocznik.setEditable(false);
		add(tfNajczesciejUczeszczanyRocznik, gbc);
		
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		tfMiastoIleStudentow = new JTextField(10);
		tfMiastoIleStudentow.setEditable(false);
		tfMiastoIleStudentow.setText(citiesMap.get(new ArrayList<>(cities).get(0)) + "");
		add(tfMiastoIleStudentow, gbc);  //??????????????????????
				
	}
	
	public void fillTextFields()
	{
		//tutaj bedzie wywolywal kolejne metody z bazy danych i je ladowal do poszczegolnych text fieldow
		tfNajstarszyStudent.setText(BazaDanych.najstarszStudent().getImie() + " " + BazaDanych.najstarszStudent().getNazwisko() + " - lat: " + BazaDanych.najstarszStudent().getWiek());
		tfNajmlodyszyStudent.setText(BazaDanych.najmlodszyStudent().getImie() + " " + BazaDanych.najmlodszyStudent().getNazwisko() + " - lat: " + BazaDanych.najmlodszyStudent().getWiek());
		tfNajstarszaUczelnia.setText(BazaDanych.najstarszaUczelnia().getName() + " - " + BazaDanych.najstarszaUczelnia().getEstablshYear() + "r.");
		tfNajmlodyszaUczelnia.setText(BazaDanych.najmlodszaUczelnia().getName() + " - " + BazaDanych.najmlodszaUczelnia().getEstablshYear() + "r.");
		tfAvgWiekStudent.setText(String.format("%.2f" , BazaDanych.avgWiekStudent()) + " lat");
		tfNajczesciejUczeszczanyRocznik.setText("Rocznik: " + BazaDanych.najczsciejUczeszczanyRocznik() + "");
		
	}
}
