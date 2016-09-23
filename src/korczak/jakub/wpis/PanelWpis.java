package korczak.jakub.wpis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import korczak.jakub.bazadanych.BazaDanych;
import korczak.jakub.klasy.Konfiguracja;
import korczak.jakub.klasy.Laczona;
import korczak.jakub.klasy.Student;
import korczak.jakub.klasy.University;
import korczal.jakub.modele.CustomComboBoxModel;

public class PanelWpis extends JPanel implements ActionListener
{

	private JButton btnLeftArrow;
	private JButton btnRightArrow;
	private JButton btnInsert;
	private JButton btnDelete;

	private JTextField tfId;

	private JTextField tfName;
	private JTextField tfPlace;
	private JTextField tfEstablishYear;
	private JTextField tfDeansName;

	private JTextField tfName2;
	private JTextField tfPlace2;
	private JTextField tfEstablishYear2;
	private JTextField tfDeansName2;

	private JLabel lIdStudent;
	private JLabel lIdUniversity;
	private JLabel lName;
	private JLabel lPlace;
	private JLabel lEstablishYear;
	private JLabel lDeansName;

	private JLabel lIdStudent2;
	private JLabel lIdUniversity2;
	private JLabel lName2;
	private JLabel lPlace2;
	private JLabel lEstablishYear2;
	private JLabel lDeansName2;

	private JTextField tfImie;
	private JTextField tfNazwisko;
	private JTextField tfWiek;
	private JTextField tfRokStudiow;

	private JTextField tfImie2;
	private JTextField tfNazwisko2;
	private JTextField tfWiek2;
	private JTextField tfRokStudiow2;

	private JLabel lImie;
	private JLabel lNazwisko;
	private JLabel lWiek;
	private JLabel lRokStudiow;

	private JLabel lImie2;
	private JLabel lNazwisko2;
	private JLabel lWiek2;
	private JLabel lRokStudiow2;

	private CustomComboBoxModel modelCbStudent;
	private JComboBox<Integer> cbStudent;
	private CustomComboBoxModel modelCbUczelnia;
	private JComboBox<Integer> cbUniversity;
	
	private List<Laczona> lista;
	private int idx;

	public PanelWpis()
	{
		super(new GridLayout(2, 1, 5, 5));
		
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		btnLeftArrow = new JButton("<<<");
		btnLeftArrow.addActionListener(this);
		btnRightArrow = new JButton(">>>");
		btnRightArrow.addActionListener(this);
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);
		if (Konfiguracja.isAdmin())
		{
			btnInsert.setEnabled(true);
		}
		else
		{
			btnInsert.setEnabled(false);
		}
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(this);
		if (Konfiguracja.isAdmin())
		{
			btnDelete.setEnabled(true);
		}
		else
		{
			btnDelete.setEnabled(false);
		}
		
		tfId = new JTextField(5);
		tfId.setEditable(false);
		tfName = new JTextField(10);
		tfPlace = new JTextField(10);
		tfEstablishYear = new JTextField(10);
		tfDeansName = new JTextField(10);

		tfImie = new JTextField(10);
		tfNazwisko = new JTextField(10);
		tfWiek = new JTextField(10);
		tfRokStudiow = new JTextField(10);

		tfName2 = new JTextField(10);
		tfPlace2 = new JTextField(10);
		tfEstablishYear2 = new JTextField(10);
		tfDeansName2 = new JTextField(10);

		tfImie2 = new JTextField(10);
		tfNazwisko2 = new JTextField(10);
		tfWiek2 = new JTextField(10);
		tfRokStudiow2 = new JTextField(10);
		
		tfImie.setEnabled(false);
		tfNazwisko.setEnabled(false);
		tfWiek.setEnabled(false);
		tfRokStudiow.setEnabled(false);
		tfImie2.setEnabled(false);
		tfNazwisko2.setEnabled(false);
		tfWiek2.setEnabled(false);
		tfRokStudiow2.setEnabled(false);
		tfName.setEnabled(false);
		tfPlace.setEnabled(false);
		tfEstablishYear.setEnabled(false);
		tfDeansName.setEnabled(false);
		tfName2.setEnabled(false);
		tfPlace2.setEnabled(false);
		tfEstablishYear2.setEnabled(false);
		tfDeansName2.setEnabled(false);
		


		lName = new JLabel("Name");
		lPlace = new JLabel("Place");
		lEstablishYear = new JLabel("Establish Year");
		lDeansName = new JLabel("Dean's Name");
		lIdStudent = new JLabel("Id Student");
		lIdUniversity = new JLabel("Id University");

		lImie = new JLabel("Imie");
		lNazwisko = new JLabel("Nazwisko");
		lWiek = new JLabel("Wiek");
		lRokStudiow = new JLabel("Rok studiów");

		lName2 = new JLabel("Name");
		lPlace2 = new JLabel("Place");
		lEstablishYear2 = new JLabel("Establish Year");
		lDeansName2 = new JLabel("Dean's Name");
		lIdStudent2 = new JLabel("Id Student");
		lIdUniversity2 = new JLabel("Id University");

		lImie2 = new JLabel("Imie");
		lNazwisko2 = new JLabel("Nazwisko");
		lWiek2 = new JLabel("Wiek");
		lRokStudiow2 = new JLabel("Rok studiów");

		modelCbStudent = new CustomComboBoxModel(BazaDanych.selectStudentIds());
		modelCbUczelnia = new CustomComboBoxModel(BazaDanych.selectUniversityIds());
		
		
		
		cbStudent = new JComboBox<>(modelCbStudent);
		if (Konfiguracja.isAdmin())
		{
			cbStudent.setEnabled(true);;
		}
		else
		{
			cbStudent.setEnabled(false);
		}
		
		cbStudent.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				fillCbStudent(BazaDanych.selectOneStudentById((Integer)cbStudent.getSelectedItem()));
			}
		});
		
		cbUniversity = new JComboBox<>(modelCbUczelnia);
		if (Konfiguracja.isAdmin())
		{
			cbUniversity.setEnabled(true);;
		}
		else
		{
			cbUniversity.setEnabled(false);
		}
		cbUniversity.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				fillCbUniversity(BazaDanych.selectOneUniversityById((Integer)cbUniversity.getSelectedItem()));
			}
		});

		JPanel panelUpperUpper = new JPanel(new GridLayout(1, 3, 10, 10));
		
		panelUpperUpper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panelUpperUpper.add(btnLeftArrow);
		panelUpperUpper.add(tfId);
		panelUpperUpper.add(btnRightArrow);

		JPanel panelMiddleUpper = new JPanel(new GridLayout(4, 4, 10, 10));
		
		//w ten sposob dodajesz do panelu ramki, ta co ponizej dodaje pusta ramke, okreslasz jako aprametry odsuniecie
		//panelu od poszczegolnych stron
		// panelMiddleUpper.setBorder(BorderFactory.createEmptyBorder(10, 10,
		// 10, 10));
		
		//ponizsza ramka jest najbardziej zaawansowana ze wszystkich, umozliwia nadawanie tytulu panelowi, wybor obramowania oraz kilka innych opcji
		//upiekszajjacych ramke panelu
		panelMiddleUpper.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK, 3, true), "WPISY", TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION, new Font("Courier New", Font.BOLD, 15), Color.RED));

		panelMiddleUpper.add(lImie);
		panelMiddleUpper.add(tfImie);
		panelMiddleUpper.add(lName);
		panelMiddleUpper.add(tfName);
		panelMiddleUpper.add(lNazwisko);
		panelMiddleUpper.add(tfNazwisko);
		panelMiddleUpper.add(lPlace);
		panelMiddleUpper.add(tfPlace);
		panelMiddleUpper.add(lWiek);
		panelMiddleUpper.add(tfWiek);
		panelMiddleUpper.add(lEstablishYear);
		panelMiddleUpper.add(tfEstablishYear);
		panelMiddleUpper.add(lRokStudiow);
		panelMiddleUpper.add(tfRokStudiow);
		panelMiddleUpper.add(lDeansName);
		panelMiddleUpper.add(tfDeansName);

		JPanel panelLowerUpper = new JPanel();
		panelLowerUpper.add(btnDelete);

		JPanel panelUpper = new JPanel(new BorderLayout());
		panelUpper.add(panelUpperUpper, BorderLayout.PAGE_START);
		panelUpper.add(panelMiddleUpper, BorderLayout.CENTER);
		panelUpper.add(panelLowerUpper, BorderLayout.PAGE_END);

		JPanel panelIdsLower = new JPanel(new BorderLayout());
		JPanel panelIdsLowerCenter = new JPanel(new GridLayout(5, 4, 10, 10));
		JPanel panelIdsLowerLower = new JPanel();
		
		panelIdsLowerCenter.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK, 3, true), "DATA", TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION, new Font("Courier New", Font.BOLD, 15), Color.GREEN));


		
		panelIdsLowerCenter.add(lIdStudent2);
		panelIdsLowerCenter.add(cbStudent);
		panelIdsLowerCenter.add(lIdUniversity2);
		panelIdsLowerCenter.add(cbUniversity);
		panelIdsLowerCenter.add(lImie2);
		panelIdsLowerCenter.add(tfImie2);
		panelIdsLowerCenter.add(lName2);
		panelIdsLowerCenter.add(tfName2);
		panelIdsLowerCenter.add(lNazwisko2);
		panelIdsLowerCenter.add(tfNazwisko2);
		panelIdsLowerCenter.add(lPlace2);
		panelIdsLowerCenter.add(tfPlace2);
		panelIdsLowerCenter.add(lWiek2);
		panelIdsLowerCenter.add(tfWiek2);
		panelIdsLowerCenter.add(lEstablishYear2);
		panelIdsLowerCenter.add(tfEstablishYear2);
		panelIdsLowerCenter.add(lRokStudiow2);
		panelIdsLowerCenter.add(tfRokStudiow2);
		panelIdsLowerCenter.add(lDeansName2);
		panelIdsLowerCenter.add(tfDeansName2);

		panelIdsLowerLower.add(btnInsert);

		panelIdsLower.add(panelIdsLowerCenter, BorderLayout.CENTER);
		panelIdsLower.add(panelIdsLowerLower, BorderLayout.PAGE_END);

		add(panelUpper);
		add(panelIdsLower);
		
		lista = BazaDanych.innerJoin();
		idx = 0;
		fillInnerJoinPanel();

	}
	
	public void initStudentTextFields(Student s)
	{
		tfImie2.setText(s.getImie());
		tfNazwisko2.setText(s.getNazwisko());
		tfWiek2.setText(s.getWiek()  + "");
		tfRokStudiow2.setText(s.getRokStudiow() + "");
	}
	
	public void initUniversityTextFields(University u)
	{
		tfName2.setText(u.getName());
		tfPlace2.setText(u.getPlace());
		tfDeansName2.setText(u.getDeansName());
		tfEstablishYear2.setText(u.getEstablshYear() + "");
	}
	
	private void fillInnerJoinPanel()
	{
		if (lista == null || lista.isEmpty())
		{
			tfId.setText("");
			tfImie.setText("");
			tfNazwisko.setText("");
			tfWiek.setText("");
			tfPlace.setText("");
			tfRokStudiow.setText("");
			tfName.setText("");
			tfEstablishYear.setText("");
			tfDeansName.setText("");
			JOptionPane.showMessageDialog(null, "LISTA WPISOW JEST JUZ PUSTA");
			return;
		}
		tfId.setText(lista.get(idx).getId() + "");
		tfImie.setText(lista.get(idx).getImie());
		tfNazwisko.setText(lista.get(idx).getNazwisko());
		tfWiek.setText(lista.get(idx).getWiek() + "");
		tfPlace.setText(lista.get(idx).getPlace());
		tfRokStudiow.setText(lista.get(idx).getRokStudiow() + "");
		tfName.setText(lista.get(idx).getName());
		tfEstablishYear.setText(lista.get(idx).getEstablishYear() + "");
		tfDeansName.setText(lista.get(idx).getDeansName());
		
	}

	private void fillCbStudent(Student s)
	{
		tfImie2.setText(s.getImie());
		tfNazwisko2.setText(s.getNazwisko());
		tfWiek2.setText(s.getWiek() + "");
		tfRokStudiow2.setText(s.getRokStudiow() + "");
	}
	
	private void fillCbUniversity(University u)
	{
		tfName2.setText(u.getName());
		tfPlace2.setText(u.getPlace());
		tfDeansName2.setText(u.getDeansName());
		tfEstablishYear2.setText(u.getEstablshYear() + "");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (btnInsert == e.getSource())
		{
			//1. pobieramy id z dwoch comboboxow
			int studentId = (Integer)cbStudent.getSelectedItem();
			int universityId = (Integer)cbUniversity.getSelectedItem();
			BazaDanych.insertWpis(studentId, universityId);
			lista = BazaDanych.innerJoin();
			idx = lista.size()-1;
			fillInnerJoinPanel();
		}
		else if (btnDelete == e.getSource())
		{
			if (lista == null || lista.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "LISTA WPISOW JEST PUSTA");
				return;
			}
			int removeId = Integer.parseInt(tfId.getText());
			BazaDanych.deleteWpis(removeId);
			lista = BazaDanych.innerJoin(); //jak juz usuniesz to musisz ponownie zaladowac liste zeby ja
			//zaktualizowac
			//jak usuniesz dobrze jest np ustawic sie na pierwszym miejscu w liscie
			idx = 0;
			fillInnerJoinPanel();
		}
		else if (btnLeftArrow == e.getSource())
		{
			idx--;
			if (idx < 0)
			{
				idx = lista.size() - 1;
			}
			fillInnerJoinPanel();
		}
		else if (btnRightArrow == e.getSource())
		{
			idx++;
			if (idx == lista.size())
			{
				idx = 0;
			}
			fillInnerJoinPanel();
		}

	}
	
	public void updateFonts(int newSize)
	{
		btnLeftArrow.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		btnRightArrow.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		btnInsert.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		btnDelete.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		
		tfId.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfName.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfPlace.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfEstablishYear.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfDeansName.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		
		tfImie.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfNazwisko.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfWiek.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfRokStudiow.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		
		tfName2.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfPlace2.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfEstablishYear2.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfDeansName2.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		
		tfImie2.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfNazwisko2.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfWiek2.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfRokStudiow2.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		
		lName.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		lPlace.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		lEstablishYear.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		lDeansName.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		lIdStudent.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		lIdUniversity.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
	}
	
	public void updateColor(Color c)
	{
		btnLeftArrow.setForeground(c);
		btnRightArrow.setForeground(c);
		btnInsert.setForeground(c);
		btnDelete.setForeground(c);
		
		tfId.setForeground(c);
		tfName.setForeground(c);
		tfPlace.setForeground(c);
		tfEstablishYear.setForeground(c);
		tfDeansName.setForeground(c);
		
		tfImie.setForeground(c);
		tfNazwisko.setForeground(c);
		tfWiek.setForeground(c);
		tfRokStudiow.setForeground(c);
		
		tfName2.setForeground(c);
		tfPlace2.setForeground(c);
		tfEstablishYear2.setForeground(c);
		tfDeansName2.setForeground(c);
		
		tfImie2.setForeground(c);
		tfNazwisko2.setForeground(c);
		tfWiek2.setForeground(c);
		tfRokStudiow2.setForeground(c);
		
		lName.setForeground(c);
		lPlace.setForeground(c);
		lEstablishYear.setForeground(c);
		lDeansName.setForeground(c);
		lIdStudent.setForeground(c);
		lIdUniversity.setForeground(c);
		
		lImie.setForeground(c);
		lNazwisko.setForeground(c);
		lWiek.setForeground(c);
		lRokStudiow.setForeground(c);
		
		lName2.setForeground(c);
		lPlace2.setForeground(c);
		lEstablishYear2.setForeground(c);
		lDeansName2.setForeground(c);
		lIdStudent2.setForeground(c);
		lIdUniversity2.setForeground(c);
		
		lImie2.setForeground(c);
		lNazwisko2.setForeground(c);
		lWiek2.setForeground(c);
		lRokStudiow2.setForeground(c);
	}

	public JComboBox<Integer> getCbStudent()
	{
		return cbStudent;
	}

	public JComboBox<Integer> getCbUniversity()
	{
		return cbUniversity;
	}

	public CustomComboBoxModel getModelCbStudent()
	{
		return modelCbStudent;
	}

	public CustomComboBoxModel getModelCbUczelnia()
	{
		return modelCbUczelnia;
	}
	
	
	
}