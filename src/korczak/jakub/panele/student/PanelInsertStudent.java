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

public class PanelInsertStudent extends JPanel implements ActionListener
{
	private JTextField tfImie;
	private JTextField tfNazwisko;
	private JTextField tfWiek;
	private JTextField tfRokStudiow;

	private JLabel lImie;
	private JLabel lNazwisko;
	private JLabel lWiek;
	private JLabel lRokStudiow;

	private JButton btnInsert;
	private JButton btnCancel;

	public PanelInsertStudent()
	{
		super(new BorderLayout());
		
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		tfImie = new JTextField(10);
		tfNazwisko = new JTextField(10);
		tfWiek = new JTextField(10);
		tfRokStudiow = new JTextField(10);

		lImie = new JLabel("Imie");
		lNazwisko = new JLabel("Nazwisko");
		lWiek = new JLabel("Wiek");
		lRokStudiow = new JLabel("Rok studiów");

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);

		JPanel panelSrodek = new JPanel(new GridLayout(4, 2, 25, 10));
		JPanel panelDol = new JPanel(new GridLayout(1, 3, 10, 10));

		panelSrodek.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GREEN, 3, true),
				"NOWY STUDENT", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
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
		panelDol.add(btnInsert);
		panelDol.add(btnCancel);

		add(panelSrodek, BorderLayout.CENTER);
		add(panelDol, BorderLayout.PAGE_END);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnInsert)
		{
			String imie = tfImie.getText();
			String nazwisko = tfNazwisko.getText();
			int rokStudiow = Integer.parseInt(tfRokStudiow.getText());
			int wiek = Integer.parseInt(tfWiek.getText());

			BazaDanych.insertStudent(imie, nazwisko, wiek, rokStudiow);

			tfImie.setText("");
			tfNazwisko.setText("");
			tfRokStudiow.setText("");
			tfWiek.setText("");

		}
		if (e.getSource() == btnCancel)
		{
			((JDialog) this.getRootPane().getParent()).dispose();
		}

	}
	
	public void updateFonts(int newSize)
	{
		btnInsert.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
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
		btnInsert.setForeground(c);
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

/*lImie = new JLabel("Imie");
lNazwisko = new JLabel("Nazwisko");
lWiek = new JLabel("Wiek");
lRokStudiow = new JLabel("Rok studiów");

btnInsert = new JButton("Insert");
btnInsert.addActionListener(this);
btnCancel = new JButton("Cancel");
btnCancel.addActionListener(this);

*/