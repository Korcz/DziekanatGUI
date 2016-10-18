package korczak.jakub.main;

import java.awt.Rectangle;

import javax.swing.JFrame;

import korczak.jakub.bazadanych.BazaDanych;
import korczak.jakub.panele.login.PanelLogin;


import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.JFrame;

import korczak.jakub.bazadanych.BazaDanych;
import korczak.jakub.klasy.Login;
import korczak.jakub.klasy.Student;
import korczak.jakub.klasy.University;
import korczak.jakub.klasy.Wpis;
import korczak.jakub.panele.login.PanelLogin;

public class Main
{
	public static void createAndShowGui()
	{
		JFrame frame = new JFrame("Office");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//PanelStudent panel = new PanelStudent();
		//PanelUniversity panel = new PanelUniversity();
		//PanelWpis panel = new PanelWpis();
		//MainPanel panel = new MainPanel();
		PanelLogin panel = new PanelLogin();
		//InfoPanel panel = new InfoPanel();
		panel.setVisible(true);
		frame.setContentPane(panel);
		//frame.setJMenuBar(panel.createMenu());
		frame.setMaximizedBounds(new Rectangle(50, 50, 100, 100));
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null); //ta metoda ustawia okienko wzgledem nadrzednego w centralnej czesci, a jak dasz null to wzgledem ekranu
	}
	
	

	public static void main(String[] args)
	{
		BazaDanych.connect();
		BazaDanych.createTables();
		
		List<Integer> lista = BazaDanych.selectRokStudiow();
		
		
	/*	for (Integer s : lista)
		{
			System.out.println(s);
		}*/
		
		/*BazaDanych.insertLogin("a", "a", "ADMIN");
		BazaDanych.insertLogin("b", "b", "ADMIN");
		BazaDanych.insertLogin("c", "c", "USER");
		BazaDanych.insertLogin("d", "d", "USER");
		
		BazaDanych.insertStudent("Brad", "Pitt", 54, 2);
		BazaDanych.insertStudent("Leo", "DiCaprio", 52, 2);
		BazaDanych.insertStudent("Johny", "Deep", 57, 2);
		BazaDanych.insertStudent("Matt", "Daemon", 54, 3);
		BazaDanych.insertStudent("Dustin", "Hoffman", 54, 1);
		BazaDanych.insertStudent("Al", "PaCino", 54, 5);
		
		
		
		BazaDanych.insertUczelnia("Harvard", "Newtowne", 1436, "Jackson");
		BazaDanych.insertUczelnia("Oxford", "London", 1636, "Jackson");
		BazaDanych.insertUczelnia("Cambridge", "Manchester", 1636, "Jackson");
		BazaDanych.insertUczelnia("PW", "Warszawa", 1636, "Jackson");
		BazaDanych.insertUczelnia("AGH", "Krakow", 1636, "Jackson");
		BazaDanych.insertUczelnia("SWPS", "Warszawa", 1636, "Jackson");
		
		
		BazaDanych.insertWpis(1, 1);
		BazaDanych.insertWpis(2, 3);*/
		
		/*
		List<Student> listaS = BazaDanych.selectStudent();
		List<University> listaU = BazaDanych.selectUczelnia();
		
		
		for (University uczelnia : listaU)
		{
			System.out.println(uczelnia);
		}
		
		for (Student student : listaS)
		{
			System.out.println(student);
		}

		List<Wpis> lista = BazaDanych.selectWpis();
		
		for (Wpis wpis : lista)
		{
			System.out.println(wpis);
		}
		
		
		List<Login> logins = BazaDanych.selectLogin();
		for (Login l : logins)
		{
			System.out.println(l);
		}

	*/
		
		//BazaDanych.najczsciejUczeszczanyRocznik();
		BazaDanych.miastoIleStudentow();
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			
			
			public void run()
			{
				createAndShowGui();
				
			}
		});
	}

}

//FUNPACK 7
//zad 2
//zad 3
//zad 4

