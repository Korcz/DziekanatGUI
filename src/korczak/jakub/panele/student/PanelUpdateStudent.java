package korczak.jakub.panele.student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import korczak.jakub.bazadanych.BazaDanych;
import korczak.jakub.klasy.Student;

public class PanelUpdateStudent extends JPanel implements ActionListener
{
	private JTextField tfImie;
	private JTextField tfNazwisko;
	private JTextField tfWiek;
	private JTextField tfRokStudiow;

	private JLabel lImie;
	private JLabel lNazwisko;
	private JLabel lWiek;
	private JLabel lRokStudiow;

	private JButton btnUpdate;
	private JButton btnCancel;

	private Student s;

	public PanelUpdateStudent(Student s)
	{
		super(new BorderLayout());

		this.s = s;

		tfImie = new JTextField(10);
		tfImie.setText(s.getImie());
		tfNazwisko = new JTextField(10);
		tfNazwisko.setText(s.getNazwisko());
		tfWiek = new JTextField(10);
		tfWiek.setText(s.getWiek() + "");
		tfRokStudiow = new JTextField(10);
		tfRokStudiow.setText(s.getRokStudiow() + "");

		lImie = new JLabel("Imie");
		lNazwisko = new JLabel("Nazwisko");
		lWiek = new JLabel("Wiek");
		lRokStudiow = new JLabel("Rok studiów");

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this);
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);

		JPanel panelSrodek = new JPanel(new GridLayout(4, 2, 25, 10));
		JPanel panelDol = new JPanel(new GridLayout(1, 3, 10, 10));

		panelSrodek.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.ORANGE, 3, true),
				"UPDATE STUDENT", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("Courier New", Font.BOLD, 20), Color.BLACK));
		panelDol.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));

		panelSrodek.add(lImie);
		panelSrodek.add(tfImie);
		panelSrodek.add(lNazwisko);
		panelSrodek.add(tfNazwisko);
		panelSrodek.add(lWiek);
		panelSrodek.add(tfWiek);
		panelSrodek.add(lRokStudiow);
		panelSrodek.add(tfRokStudiow);
		panelDol.add(btnUpdate);
		panelDol.add(btnCancel);

		add(panelSrodek, BorderLayout.CENTER);
		add(panelDol, BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnUpdate)
		{
			String imie = tfImie.getText();
			String nazwisko = tfNazwisko.getText();
			int wiek = Integer.parseInt(tfWiek.getText());
			int rokStudiow = Integer.parseInt(tfRokStudiow.getText());
			

			BazaDanych.updateStudent(s.getId(), imie, nazwisko, wiek, rokStudiow);
		}
		if (e.getSource() == btnCancel)
		{
			((JDialog) this.getRootPane().getParent()).dispose();
		}

	}
	
	public void updateFonts(int newSize)
	{
		btnUpdate.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		btnCancel.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfImie.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfNazwisko.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfWiek.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfRokStudiow.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		lImie.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		lNazwisko.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		lWiek.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		lRokStudiow.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
	}
	

	public void updateColor(Color c)
	{
		btnUpdate.setForeground(c);
		btnCancel.setForeground(c);
		tfImie.setForeground(c);
		tfNazwisko.setForeground(c);
		tfWiek.setForeground(c);
		tfRokStudiow.setForeground(c);
		lImie.setForeground(c);
		lNazwisko.setForeground(c);
		lWiek.setForeground(c);
		lRokStudiow.setForeground(c);
		
	}

}