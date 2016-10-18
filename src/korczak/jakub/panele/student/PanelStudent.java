package korczak.jakub.panele.student;

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
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import korczak.jakub.bazadanych.BazaDanych;
import korczak.jakub.klasy.Konfiguracja;
import korczak.jakub.klasy.Student;
import korczak.jakub.panele.glowny.MainPanel;

public class PanelStudent extends JPanel implements ActionListener
{
	private JButton btnStrzalkaLewo;
	private JButton btnStrzalkaPrawo;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;

	private JTextField tfId;
	private JTextField tfImie;
	private JTextField tfNazwisko;
	private JTextField tfWiek;
	private JTextField tfRokStudiow;

	private JLabel lImie;
	private JLabel lNazwisko;
	private JLabel lWiek;
	private JLabel lRokStudiow;

	private List<Student> lista;
	int idx = 0;

	private PanelInsertStudent panelInsertStudent;
	
	

	public PanelStudent()
	{
		super(new BorderLayout());

		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		btnStrzalkaLewo = new JButton("<<<");
		btnStrzalkaLewo.addActionListener(this);
		btnStrzalkaPrawo = new JButton(">>>");
		btnStrzalkaPrawo.addActionListener(this);
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
		tfImie = new JTextField(10);
		tfImie.setEnabled(false);
		tfNazwisko = new JTextField(10);
		tfNazwisko.setEnabled(false);
		tfWiek = new JTextField(10);
		tfWiek.setEnabled(false);
		tfRokStudiow = new JTextField(10);
		tfRokStudiow.setEnabled(false);

		lImie = new JLabel("Imie");
		lNazwisko = new JLabel("Nazwisko");
		lWiek = new JLabel("Wiek");
		lRokStudiow = new JLabel("Rok studiów");

		JPanel panelGora = new JPanel(new GridLayout(1, 3, 10, 10));
		JPanel panelSrodek = new JPanel(new GridLayout(4, 2, 25, 10));
		JPanel panelDol = new JPanel(new GridLayout(1, 3, 10, 10));

		panelGora.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
		panelSrodek.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE, 3, true),
				"PANEL STUDENT", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("Courier New", Font.BOLD, 20), Color.BLACK));
		panelDol.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));

		panelGora.add(btnStrzalkaLewo);
		panelGora.add(tfId);
		panelGora.add(btnStrzalkaPrawo);
		panelSrodek.add(lImie);
		panelSrodek.add(tfImie);
		panelSrodek.add(lNazwisko);
		panelSrodek.add(tfNazwisko);
		panelSrodek.add(lWiek);
		panelSrodek.add(tfWiek);
		panelSrodek.add(lRokStudiow);
		panelSrodek.add(tfRokStudiow);
		panelDol.add(btnInsert);
		panelDol.add(btnUpdate);
		panelDol.add(btnDelete);

		add(panelGora, BorderLayout.PAGE_START);
		add(panelSrodek, BorderLayout.CENTER);
		add(panelDol, BorderLayout.PAGE_END);

		lista = BazaDanych.selectStudent();
		idx = 0;
		fill();
	}

	public void fill()
	{
		if (lista != null && !lista.isEmpty())
		{
			tfImie.setText(lista.get(idx).getImie());
			tfNazwisko.setText(lista.get(idx).getNazwisko());
			tfRokStudiow.setText(lista.get(idx).getRokStudiow() + "");
			tfWiek.setText(lista.get(idx).getWiek() + "");
			tfId.setText(lista.get(idx).getId() + "");
			
		} else
		{
			tfImie.setText("");
			tfNazwisko.setText("");
			tfRokStudiow.setText("");
			tfWiek.setText("");
			tfId.setText("");
			JOptionPane.showMessageDialog(null, "LISTA STUDENTOW JEST PUSTA");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnStrzalkaLewo)
		{
			lista = BazaDanych.selectStudent();
			idx--;
			if (idx < 0)
			{
				idx = lista.size() - 1;
			}
			fill();
		}

		if (e.getSource() == btnStrzalkaPrawo)
		{
			lista = BazaDanych.selectStudent();
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
			dialog.setLocationRelativeTo(null);
			dialog.setModal(true);
			dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // jak
																		// dasz
																		// EXIT_ON_CLOSE
																		// to po
																		// nacisnieciu
																		// krzyzyka
																		// zamkna
																		// sie
																		// wszystkie
																		// okna
																		// w tym
																		// glowne
			panelInsertStudent = new PanelInsertStudent();
			panelInsertStudent.updateFonts(MainPanel.fontSize);
			SwingUtilities.updateComponentTreeUI(this.getRootPane().getParent());
			panelInsertStudent.setVisible(true);
			dialog.setContentPane(panelInsertStudent);
			dialog.pack();
			dialog.setVisible(true);
			int xFrame = ((JFrame) this.getRootPane().getParent()).getX();
			int yFrame = ((JFrame) this.getRootPane().getParent()).getY();
			int widthFrame = ((JFrame) this.getRootPane().getParent()).getWidth();
			int heighFrame = ((JFrame) this.getRootPane().getParent()).getHeight();

			dialog.setLocation(200, 200);
		}

		if (e.getSource() == btnUpdate)
		{
			String imie = tfImie.getText();
			String nazwisko = tfNazwisko.getText();
			int rokStudiow = Integer.parseInt(tfRokStudiow.getText());
			int wiek = Integer.parseInt(tfWiek.getText());

			Student su = new Student(lista.get(idx).getId(), imie, nazwisko, rokStudiow, wiek);

			JDialog dialog = new JDialog();
			dialog.setLocationRelativeTo(null);
			dialog.setModal(true);
			dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // jak
																		// dasz
																		// EXIT_ON_CLOSE
																		// to po
																		// nacisnieciu
																		// krzyzyka
																		// zamkna
																		// sie
																		// wszystkie
																		// okna
																		// w tym
																		// glowne
			PanelUpdateStudent panel = new PanelUpdateStudent(su);
			panel.setVisible(true);
			dialog.setContentPane(panel);
			dialog.pack();
			dialog.setVisible(true);
			int xFrame = ((JFrame) this.getRootPane().getParent()).getX();
			int yFrame = ((JFrame) this.getRootPane().getParent()).getY();
			int widthFrame = ((JFrame) this.getRootPane().getParent()).getWidth();
			int heighFrame = ((JFrame) this.getRootPane().getParent()).getHeight();

			dialog.setLocation(200, 200);
		}

		if (e.getSource() == btnDelete)
		{
			if (lista != null && !lista.isEmpty())
			{
				int id = Integer.parseInt(tfId.getText());
				BazaDanych.deleteStudent(id);
				idx = 0;
				lista = BazaDanych.selectStudent();
				fill();
			}
		}
	}

	public void updateFont(int newSize)
	{
		btnStrzalkaLewo.setFont(new Font("Times New Roman", Font.PLAIN, newSize));
		btnStrzalkaPrawo.setFont(new Font("Times New Roman", Font.PLAIN, newSize));
		btnInsert.setFont(new Font("Times New Roman", Font.PLAIN, newSize));
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, newSize));
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, newSize));

		tfId.setFont(new Font("Times New Roman", Font.PLAIN, newSize));
		tfImie.setFont(new Font("Times New Roman", Font.PLAIN, newSize));
		tfNazwisko.setFont(new Font("Times New Roman", Font.PLAIN, newSize));
		tfWiek.setFont(new Font("Times New Roman", Font.PLAIN, newSize));
		tfRokStudiow.setFont(new Font("Times New Roman", Font.PLAIN, newSize));

		lImie.setFont(new Font("Times New Roman", Font.PLAIN, newSize));
		lNazwisko.setFont(new Font("Times New Roman", Font.PLAIN, newSize));
		lWiek.setFont(new Font("Times New Roman", Font.PLAIN, newSize));
		lRokStudiow.setFont(new Font("Times New Roman", Font.PLAIN, newSize));
	}

	public void updateColor(Color c)
	{
		btnStrzalkaLewo.setForeground(c);
		btnStrzalkaPrawo.setForeground(c);
		btnInsert.setForeground(c);
		btnUpdate.setForeground(c);
		btnDelete.setForeground(c);
		tfId.setForeground(c);
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

// btnStrzalkaLewo = new JButton("<<<");
// btnStrzalkaLewo.addActionListener(this);
// btnStrzalkaPrawo = new JButton(">>>");
// btnStrzalkaPrawo.addActionListener(this);
// btnInsert = new JButton("Insert");
// btnInsert.addActionListener(this);
// btnUpdate = new JButton("Update");
// btnUpdate.addActionListener(this);
// btnDelete = new JButton("Delete");
// btnDelete.addActionListener(this);
//
// tfId = new JTextField(5);
// tfId.setEditable(false);
// tfImie = new JTextField(10);
// tfNazwisko = new JTextField(10);
// tfWiek = new JTextField(10);
// tfRokStudiow = new JTextField(10);
//
// lImie = new JLabel("Imie");
// lNazwisko = new JLabel("Nazwisko");
// lWiek = new JLabel("Wiek");
// lRokStudiow = new JLabel("Rok studiów");
