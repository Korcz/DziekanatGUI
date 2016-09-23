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

public class PanelUpdateUniversity extends JPanel implements ActionListener
{
	private JButton btnUpdate;
	private JButton btnCancel;

	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfPlace;
	private JTextField tfEstablishYear;
	private JTextField tfDeansName;

	private JLabel lName;
	private JLabel lPlace;
	private JLabel lEstablishYear;
	private JLabel lDeansName;

	private University u;

	public PanelUpdateUniversity(University u)
	{
		super(new BorderLayout());

		this.u = u;

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this);
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);

		tfName = new JTextField(10);
		tfName.setText(u.getName());
		tfPlace = new JTextField(10);
		tfPlace.setText(u.getPlace());
		tfEstablishYear = new JTextField(10);
		tfEstablishYear.setText(u.getEstablshYear() + "");
		tfDeansName = new JTextField(10);
		tfDeansName.setText(u.getDeansName());
		

		lName = new JLabel("Name");
		lPlace = new JLabel("Place");
		lEstablishYear = new JLabel("Establish Year");
		lDeansName = new JLabel("Dean's Name");

		JPanel panelCentral = new JPanel(new GridLayout(4, 2, 25, 10));
		JPanel panelLower = new JPanel(new GridLayout(1, 3, 10, 10));

		panelCentral.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.ORANGE, 3, true),
				"Update University", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
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
		panelLower.add(btnUpdate);
		panelLower.add(btnCancel);

		add(panelCentral, BorderLayout.CENTER);
		add(panelLower, BorderLayout.PAGE_END);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnUpdate)
		{
			String name = tfName.getText();
			String place = tfPlace.getText();
			int establishYear = Integer.parseInt(tfEstablishYear.getText());
			String deansName = tfDeansName.getText();
			
			BazaDanych.updateUczelnia(u.getId(), name, place, establishYear, deansName);
		}
		if (e.getSource() == btnCancel)
		{
			((JDialog)this.getRootPane().getParent()).dispose();
		}
	}
	
	public void updateFonts(int newSize)
	{
		btnUpdate.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
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
		btnUpdate.setForeground(c);
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

/*private JButton btnUpdate;
private JButton btnCancel;

private JTextField tfId;
private JTextField tfName;
private JTextField tfPlace;
private JTextField tfEstablishYear;
private JTextField tfDeansName;

private JLabel lName;
private JLabel lPlace;
private JLabel lEstablishYear;
private JLabel lDeansName;
*/