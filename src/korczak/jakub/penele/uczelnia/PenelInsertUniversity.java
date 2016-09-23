package korczak.jakub.penele.uczelnia;

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
import korczak.jakub.klasy.University;

public class PenelInsertUniversity extends JPanel implements ActionListener
{
	private JButton btnInsert;
	private JButton btnCancel;

	private JTextField tfName;
	private JTextField tfPlace;
	private JTextField tfEstablishYear;
	private JTextField tfDeansName;

	private JLabel lName;
	private JLabel lPlace;
	private JLabel lEstablishYear;
	private JLabel lDeansName;

	private University u;

	public PenelInsertUniversity()
	{
		super(new BorderLayout());
		
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		this.u = u;

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);

		tfName = new JTextField(10);
		tfPlace = new JTextField(10);
		tfEstablishYear = new JTextField(10);
		tfDeansName = new JTextField(10);

		lName = new JLabel("Name");
		lPlace = new JLabel("Place");
		lEstablishYear = new JLabel("Establish Year");
		lDeansName = new JLabel("Dean's Name");

		JPanel panelCentral = new JPanel(new GridLayout(4, 2, 25, 10));
		JPanel panelLower = new JPanel(new GridLayout(1, 3, 10, 10));

		panelCentral.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GREEN, 3, true),
				"Insert University", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("Courier New", Font.BOLD, 20), Color.BLACK));
		panelLower.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));

		panelCentral.add(lName);
		panelCentral.add(tfName);
		panelCentral.add(lPlace);
		panelCentral.add(tfPlace);
		panelCentral.add(lEstablishYear);
		panelCentral.add(tfEstablishYear);
		panelCentral.add(lDeansName);
		panelCentral.add(tfDeansName);
		panelLower.add(btnInsert);
		panelLower.add(btnCancel);

		add(panelCentral, BorderLayout.CENTER);
		add(panelLower, BorderLayout.PAGE_END);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnInsert)
		{
			String name = tfName.getText();
			String place = tfPlace.getText();
			int establishYear = Integer.parseInt(tfEstablishYear.getText());
			String deansName = tfDeansName.getText();
			
			BazaDanych.insertUczelnia(name, place, establishYear, deansName);
			
			tfName.setText("");
			tfPlace.setText("");
			tfEstablishYear.setText("");
			tfDeansName.setText("");
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
		tfName.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfPlace.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfEstablishYear.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		tfDeansName.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		lName.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		lPlace.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		lEstablishYear.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		lDeansName.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
	}
	

	public void updateColor(Color c)
	{
		btnInsert.setForeground(c);
		btnCancel.setForeground(c);
		tfName.setForeground(c);
		tfPlace.setForeground(c);
		tfEstablishYear.setForeground(c);
		tfDeansName.setForeground(c);
		lName.setForeground(c);
		lPlace.setForeground(c);
		lEstablishYear.setForeground(c);
		lDeansName.setForeground(c);
	}
	
}
