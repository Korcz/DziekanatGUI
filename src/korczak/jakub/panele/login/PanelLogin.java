package korczak.jakub.panele.login;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import korczak.jakub.bazadanych.BazaDanych;
import korczak.jakub.klasy.Konfiguracja;
import korczak.jakub.klasy.Login;
import korczak.jakub.panele.glowny.MainPanel;

public class PanelLogin extends JPanel implements ActionListener
{
	private JButton btnLogin;
	private JButton btnCancel;

	private JTextField tfLogin;
	private JPasswordField tfPassword;

	private JLabel lLogin;
	private JLabel lPassword;

	public PanelLogin()
	{
		
		super(new BorderLayout());
		
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);

		tfLogin = new JTextField(10);
		tfPassword = new JPasswordField(10);

		lLogin = new JLabel("Login");
		lPassword = new JLabel("Password");

		JPanel panelMain = new JPanel(new GridLayout(3, 2, 10, 10));

		panelMain.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.BLACK, 3, true), "Logowanie", TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION, new Font("Courier New", Font.BOLD, 15), Color.RED));
		
		panelMain.add(lLogin);
		panelMain.add(tfLogin);
		panelMain.add(lPassword);
		panelMain.add(tfPassword);
		panelMain.add(btnLogin);
		panelMain.add(btnCancel);
		
		add(panelMain, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnLogin)
		{
			//1. pobierz z bazy wszystkie loginy
			//2. pobierz dane z pola login oraz password
			//3. przejrzyj pobrane dane i sprawdz czy jest tam para user - haslo ktore pobrales z pola jezeli tak to wejdziemy do okna z panelami
			
			List<Login> logins = BazaDanych.selectLogin();
			
			String login = tfLogin.getText();
			String password = String.valueOf(tfPassword.getPassword()); //String.valueOf zamienia tablice dowolna na napis
			String role = null;
			boolean isLoginDataValid = false;
			
			for (Login l : logins)
			{
				if (l.getUsername().equals(login) && l.getPassword().equals(password))
				{
					role = l.getRole();
					isLoginDataValid = true;
					break;
				}
			}
			
			if (isLoginDataValid)
			{
				if (role.equals("ADMIN"))
				{
					Konfiguracja.setAdmin(true);
				}
				else
				{
					Konfiguracja.setAdmin(false);
				}
				
				JFrame frame = new JFrame("Office");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				MainPanel panel = new MainPanel();
				//InfoPanel panel = new InfoPanel();
				panel.setVisible(true);
				frame.setContentPane(panel);
				frame.setJMenuBar(panel.createMenu());

				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				
				((JFrame)(this.getRootPane().getParent())).dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "BLAD LOGOWANIA");
			}
		}
		else if (e.getSource() == btnCancel) 
		{
			((JFrame) this.getRootPane().getParent()).dispose();
			
		}

	}

}