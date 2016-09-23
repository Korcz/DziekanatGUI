package korczak.jakub.panele.tabela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import korczak.jakub.bazadanych.BazaDanych;
import korczal.jakub.modele.CustomComboBoxModel;
import korczal.jakub.modele.CustomJListModel;
import korczal.jakub.modele.CustomTableModel;

public class PanelTabela extends JPanel implements ActionListener{
	JTable table;
	
	private JButton btnDelete;
	private JButton btnFiltr;
	private JButton btnReset;
	
	private JCheckBox cbImie;
	private JCheckBox cbNazwisko;
	private JCheckBox cbNazwaUczelni;
	
	private JCheckBox cbWiek;
	private JCheckBox cbRokStudiow;
	
	private CustomJListModel lImieModel;
	private CustomJListModel lNazwiskoModel;
	private CustomJListModel lNazwaUczelniaModel;
	private JList<String> lImie;
	private JList<String> lNazwisko;
	private JList<String> lNazwaUczelni;
	
	private JLabel labelFromWiek;
	private JLabel labelFromRok;
	private JLabel labelToWiek;
	private JLabel labelToRok;
	
	private CustomComboBoxModel modelCbWiekFrom;
	private JComboBox<Integer> cboxWiekFrom;
	private CustomComboBoxModel modelCbWiekTo;
	private JComboBox<Integer> cboxWiekTo;
	
	private CustomComboBoxModel modelCbRokStudiowFrom;
	private JComboBox<Integer> cboxRokFrom;
	private CustomComboBoxModel modelCbRokStudiowTo;
	private JComboBox<Integer> cboxRokTo;
	
	
	
	
	public PanelTabela()
	{
		
		super(new GridLayout(2, 1, 5, 5));
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(this);
		btnFiltr = new JButton("Filtr");
		btnFiltr.addActionListener(this);
		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		
		cbImie = new JCheckBox("Imie");
		cbImie.addActionListener(this);
		cbNazwisko = new JCheckBox("Nazwisko");
		cbNazwisko.addActionListener(this);
		cbNazwaUczelni = new JCheckBox("Nazwa Uczelni");
		cbNazwaUczelni.addActionListener(this);
		
		cbWiek = new JCheckBox("Wiek");
		cbRokStudiow = new JCheckBox("Rok Studiow");
		
		lNazwaUczelniaModel = new CustomJListModel(BazaDanych.selectUczelniaNazwa());
		lNazwiskoModel = new CustomJListModel(BazaDanych.selectNazwislko());
		lImieModel = new CustomJListModel(BazaDanych.selectImie());
		lImie = new JList<>(lImieModel);
		lImie.setEnabled(false);
		lNazwisko = new JList<>(lNazwiskoModel);
		lNazwisko.setEnabled(false);
		lNazwaUczelni = new JList<>(lNazwaUczelniaModel);
		lNazwaUczelni.setEnabled(false);
		
		labelFromWiek = new JLabel("From:");
		labelToWiek = new JLabel("To:");
		labelFromRok = new JLabel("From:");
		labelToRok = new JLabel("To:");

		modelCbRokStudiowFrom = new CustomComboBoxModel(BazaDanych.selectRokStudiow());
		modelCbRokStudiowTo = new CustomComboBoxModel(BazaDanych.selectRokStudiow());
		modelCbWiekFrom = new CustomComboBoxModel(BazaDanych.selectWiek());
		modelCbWiekTo = new CustomComboBoxModel(BazaDanych.selectWiek());
		cboxWiekFrom = new JComboBox<>(modelCbWiekFrom);
		cboxWiekTo = new JComboBox<>(modelCbWiekTo);
		cboxRokFrom = new JComboBox<>(modelCbRokStudiowFrom);
		cboxRokTo = new JComboBox<>(modelCbRokStudiowTo);
		
		cboxRokFrom.addActionListener(e -> {
			//int rok = (Integer)cboxRokFrom.getSelectedItem());
			modelCbRokStudiowTo.updateCustomComboBoxModel(BazaDanych.selectRokStudiowGreaterThan(2));
			cboxRokTo.updateUI();
		});
		
		CustomTableModel tableModel = new CustomTableModel(
				BazaDanych.innerJoin(),
				Arrays.asList(new String[]{"Id", "Imie", "Nazwisko", "Wiek ", "Rok Sudiow", 
						"Name", "Place", "Dean's name", "Establish Year"})
		);
		table = new JTable(tableModel);
		
		
		JPanel panelUp = new JPanel(new GridLayout(1, 3, 10, 10));
		panelUp.add(table);
		panelUp.add(new JScrollPane(table));
		
		panelUp.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.GREEN, 3, true), "TABELA", TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION, new Font("Courier New", Font.BOLD, 12), Color.CYAN));
		
		JPanel panelLower = new JPanel(new BorderLayout());
		
		JPanel panelLowerUpper = new JPanel(new GridLayout(1, 6, 10, 10));
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		
		panelLowerUpper.add(cbImie);
		panelLowerUpper.add(new JScrollPane(lImie));
		panelLowerUpper.add(cbNazwisko);
		panelLowerUpper.add(new JScrollPane(lNazwisko));
		panelLowerUpper.add(cbNazwaUczelni);
		panelLowerUpper.add(new JScrollPane(lNazwaUczelni));
	

		JPanel panelLowerCenter = new JPanel(new GridLayout(2, 5, 10, 10));
		
		panelLowerCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelLowerCenter.add(cbWiek);
		panelLowerCenter.add(labelFromWiek);
		panelLowerCenter.add(cboxWiekFrom);
		panelLowerCenter.add(labelToWiek);
		panelLowerCenter.add(cboxWiekTo);
		panelLowerCenter.add(cbRokStudiow);
		panelLowerCenter.add(labelFromRok);
		panelLowerCenter.add(cboxRokFrom);
		panelLowerCenter.add(labelToRok);
		panelLowerCenter.add(cboxRokTo);
		
		
		JPanel panelLowerLower = new JPanel(new GridLayout(1, 3, 10, 10));
					
		
		panelLowerLower.add(btnDelete);
		panelLowerLower.add(btnFiltr);
		panelLowerLower.add(btnReset);
		
		JPanel panelCenter = new JPanel(new BorderLayout());
		panelCenter.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK, 3, true), "FILTROWANIE", TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION, new Font("Courier New", Font.BOLD, 15), Color.MAGENTA));
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		panelCenter.add(panelLowerUpper, BorderLayout.CENTER);
		panelCenter.add(panelLowerCenter, BorderLayout.PAGE_END);
		
		
		panelLower.add(panelCenter, BorderLayout.CENTER);
		panelLower.add(panelLowerLower, BorderLayout.PAGE_END);
		
		add(panelUp);
		add(panelLower);
				
		}



	private void filtruj()
	{
		List<String> imiona = null;
		if (cbImie.isSelected())
		{
			imiona = lImie.getSelectedValuesList();
		}
		
		List<String> nazwiska = null;
		if (cbNazwisko.isSelected())
		{
			nazwiska = lNazwisko.getSelectedValuesList();
		}
		
		List<String> nazwy = null;
		if (cbNazwaUczelni.isSelected())
		{
			nazwy = lNazwaUczelni.getSelectedValuesList();
		}
		
		int[] wiekTab = new int[2];
		if (cbWiek.isSelected())
		{
			wiekTab[0] = (Integer)cboxWiekFrom.getSelectedItem();
			wiekTab[1] = (Integer)cboxWiekTo.getSelectedItem();
		}
		
		int[] rokStudiowTab = new int[2];
		if (cbRokStudiow.isSelected())
		{
			rokStudiowTab[0] = (Integer)cboxRokFrom.getSelectedItem();
			rokStudiowTab[1] = (Integer)cboxRokTo.getSelectedItem();
		}
		
		System.out.println(imiona);
		System.out.println(nazwiska);
		System.out.println(nazwy);
		System.out.println(Arrays.toString(rokStudiowTab));
		System.out.println(Arrays.toString(wiekTab));
		
		BazaDanych.pobierzDaneFiltrowanie(imiona, nazwiska, nazwy, rokStudiowTab, wiekTab, 
				cbImie.isSelected(), cbNazwisko.isSelected(), cbNazwaUczelni.isSelected(), 
				cbRokStudiow.isSelected(), cbWiek.isSelected());
		
	
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (cbImie == e.getSource())
		{
			if (cbImie.isSelected())
			{
				lImie.setEnabled(true);
			}
			else
			{
				lImie.setEnabled(false);
			}
		}
		
		else if (cbNazwisko == e.getSource())
		{
			if (cbNazwisko.isSelected())
			{
				lNazwisko.setEnabled(true);
			}
			else
			{
				lNazwisko.setEnabled(false);
			}
		}
		
		else if (cbNazwaUczelni == e.getSource())
		{
			if (cbNazwaUczelni.isSelected())
			{
				lNazwaUczelni.setEnabled(true);
			}
			else
			{
				lNazwaUczelni.setEnabled(false);
			}
		}
		else if (btnDelete == e.getSource())
		{
			
		}
		else if (btnFiltr == e.getSource())
		{
			filtruj();
		}
		else if (btnReset == e.getSource())
		{
			
		}
		
	}
	
	
}