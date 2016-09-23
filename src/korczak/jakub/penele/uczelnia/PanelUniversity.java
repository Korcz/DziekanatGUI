package korczak.jakub.penele.uczelnia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import korczak.jakub.bazadanych.BazaDanych;
import korczak.jakub.klasy.Konfiguracja;
import korczak.jakub.klasy.University;

public class PanelUniversity extends JPanel implements ActionListener
{

	private JButton btnLeftArrow;
	private JButton btnRightArrow;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;

	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfPlace;
	private JTextField tfEstablishYear;
	private JTextField tfDeansName;

	private JLabel lName;
	private JLabel lPlace;
	private JLabel lEstablishYear;
	private JLabel lDeansName;

	private List<University> lista;
	int idx = 0;
	
	PenelInsertUniversity panelInsertUniversity;

	public PanelUniversity()
	{
		super(new BorderLayout());
		
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
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this);
		if (Konfiguracja.isAdmin())
		{
			btnUpdate.setEnabled(true);
		}
		else
		{
			btnUpdate.setEnabled(false);
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
		
		tfName.setEnabled(false);
		tfPlace.setEnabled(false);
		tfEstablishYear.setEnabled(false);
		tfDeansName.setEnabled(false);
		

		lName = new JLabel("Name");
		lPlace = new JLabel("Place");
		lEstablishYear = new JLabel("Establish Year");
		lDeansName = new JLabel("Dean's Name");

		JPanel panelUpper = new JPanel(new GridLayout(1, 3, 10, 10));
		JPanel panelCentral = new JPanel(new GridLayout(4, 2, 25, 10));
		JPanel panelLower = new JPanel(new GridLayout(1, 3, 10, 10));

		panelUpper.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
		panelCentral.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.yellow, 3, true),
				"Panel University", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("Courier New", Font.BOLD, 20), Color.BLACK));
		panelLower.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));

		panelUpper.add(btnLeftArrow);
		panelUpper.add(tfId);
		panelUpper.add(btnRightArrow);
		panelCentral.add(lName);
		panelCentral.add(tfName);
		panelCentral.add(lPlace);
		panelCentral.add(tfPlace);
		panelCentral.add(lEstablishYear);
		panelCentral.add(tfEstablishYear);
		panelCentral.add(lDeansName);
		panelCentral.add(tfDeansName);
		panelLower.add(btnInsert);
		panelLower.add(btnUpdate);
		panelLower.add(btnDelete);

		add(panelUpper, BorderLayout.PAGE_START);
		add(panelCentral, BorderLayout.CENTER);
		add(panelLower, BorderLayout.PAGE_END);

		lista = BazaDanych.selectUczelnia();
		idx = 0;
		fill();
	}
	
	public void fill()
	{
		if (lista != null && !lista.isEmpty())
		{
			tfName.setText(lista.get(idx).getName());
			tfPlace.setText(lista.get(idx).getPlace());
			tfEstablishYear.setText(lista.get(idx).getEstablshYear() + "");
			tfDeansName.setText(lista.get(idx).getDeansName());
			tfId.setText(lista.get(idx).getId() + "");
			
		}
		else
		{
			tfName.setText("");
			tfPlace.setText("");
			tfEstablishYear.setText("");
			tfDeansName.setText("");
			tfId.setText("");
			JOptionPane.showMessageDialog(null, "LISTA UNIWERSYTET JEST JUZ PUSTA");
		}

	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnLeftArrow)
		{
			lista = BazaDanych.selectUczelnia();
			idx--;
			if (idx < 0)
			{
				idx = lista.size() - 1;
			}
			fill();
		}
		if (e.getSource() == btnRightArrow)
		{
			lista = BazaDanych.selectUczelnia();
			idx++;
			if (idx >= lista.size())
			{
				idx = 0;
			}
			fill();
		}
		if (e.getSource() == btnInsert)
		{
			JDialog dialog = new JDialog();
			dialog.setModal(true);
			dialog.setLocationRelativeTo(null);
			dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			panelInsertUniversity = new PenelInsertUniversity();
			panelInsertUniversity.setVisible(true);
			dialog.setContentPane(panelInsertUniversity);
			dialog.pack();
			dialog.setVisible(true);
			int xFrame = ((JFrame)this.getRootPane().getParent()).getX();
			int yFrame = ((JFrame)this.getRootPane().getParent()).getY();
			int widthFrame = ((JFrame)this.getRootPane().getParent()).getWidth();
			int heighFrame = ((JFrame)this.getRootPane().getParent()).getHeight();
			
			dialog.setLocation(200,200);
		}
		if (e.getSource() == btnUpdate)
		{
			String name = tfName.getText();
			String place = tfPlace.getText();
			int establishYear = Integer.parseInt(tfEstablishYear.getText());
			String deansName = tfDeansName.getText();
			
			University uu = new University(lista.get(idx).getId(), name, place, establishYear, deansName);
			
			JDialog dialog = new JDialog();
			//JFrame dialog = new JFrame();
			dialog.setModal(true); //tylko jedno aktywne okno
			dialog.setLocationRelativeTo(null); // lokalizacja okna
			dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			PanelUpdateUniversity panel = new PanelUpdateUniversity(uu);
			panel.setVisible(true);
			dialog.setContentPane(panel);
			dialog.pack(); //dzieki tej metodzie okno dopasowywuje swoj rozmiar do komponentow w nim zawartych
			//zalecany sposob ustawiania rozmiaru okna
			dialog.setVisible(true);
			int xFrame = ((JFrame)this.getRootPane().getParent()).getX();
			int yFrame = ((JFrame)this.getRootPane().getParent()).getY();
			int widthFrame = ((JFrame)this.getRootPane().getParent()).getWidth();
			int heighFrame = ((JFrame)this.getRootPane().getParent()).getHeight();
			
			//dialog.setLocationRelativeTo(this);
			//dialog.setLocation(200,200);
			dialog.setLocationRelativeTo(((JFrame)this.getRootPane().getParent()));
			
		}
		if (e.getSource() == btnDelete)
		{
			if (lista != null && !lista.isEmpty())
			{
				int id = Integer.parseInt(tfId.getText());
				BazaDanych.deleteUczelnia(id);
				idx = 0;
				lista = BazaDanych.selectUczelnia();
				fill();
			}
		}
	}
	
	public void updateFonts(int newSize)
	{
		btnLeftArrow.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		btnRightArrow.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		btnInsert.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		btnUpdate.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		btnDelete.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
		
		tfId.setFont(new Font ("Times New Roman", Font.PLAIN, newSize));
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
		btnRightArrow.setForeground(c);
		btnInsert.setForeground(c);
		btnUpdate.setForeground(c);
		btnDelete.setForeground(c);
		tfId.setForeground(c);
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