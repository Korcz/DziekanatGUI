package korczak.jakub.panele.glowny;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import korczak.jakub.bazadanych.BazaDanych;
import korczak.jakub.panel.staystyka.PanelStatystyka;
import korczak.jakub.panele.student.PanelStudent;
import korczak.jakub.panele.tabela.PanelTabela;
import korczak.jakub.penele.uczelnia.PanelUniversity;
import korczak.jakub.wpis.PanelWpis;

public class MainPanel extends JPanel
{
	private PanelStudent panelStudent;
	private PanelUniversity panelUniversity;
	private PanelWpis panelWpis;
	private InfoPanel panelInfo;
	private PanelStatystyka panelStatystyka;
	private InfoPanel infoPanel;
	private PanelTabela panelTabela;
	
	public static int fontSize = 12;
	
	public MainPanel()
	{
		super(new CardLayout());
		
		panelStudent = new PanelStudent();
		panelUniversity = new PanelUniversity();
		panelWpis = new PanelWpis();
		// panelInfo = new InfoPanel();
		panelStatystyka = new PanelStatystyka();
		//panelTabela = new PanelTabela();
		
		//z kazdym panelem jest zwiazany napis
		add(panelStudent, "panelStudent");
		add(panelUniversity, "panelUniversity");
		add(panelWpis, "panelWpis");
	//	add(panelInfo, "panelInfo");
		add(panelStatystyka, "panelStatystyka");
		//add(panelTabela, "panelTabela");
	}
	
	public JMenuBar createMenu()
	{
		JPanel panel = this;
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuPanel = new JMenu("PANELE");
		menuPanel.setMnemonic(KeyEvent.VK_ALT);
		
		JMenuItem menuItemPanelStudent = new JMenuItem("STUDENT", new ImageIcon("szkola.png"));
		menuItemPanelStudent.setMnemonic('S');
		menuItemPanelStudent.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_MASK));
		menuItemPanelStudent.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				CardLayout cl = (CardLayout)getLayout();
				cl.show(panel, "panelStudent");
				
			}
		});
		
		JMenuItem menuItemPanelUniversity = new JMenuItem("UNIVERSITY", new ImageIcon("uczelnia.png"));
		menuItemPanelUniversity.setMnemonic('U');
		menuItemPanelUniversity.setAccelerator(KeyStroke.getKeyStroke('U', KeyEvent.CTRL_MASK));
		menuItemPanelUniversity.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				CardLayout cl = (CardLayout)getLayout();
				cl.show(panel, "panelUniversity");
			}
		});
		
		JMenuItem menuItemPanelRegister = new JMenuItem("REGISTER", new ImageIcon("register.png"));
		menuItemPanelRegister.setMnemonic('R');
		menuItemPanelRegister.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_MASK));
		menuItemPanelRegister.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panelWpis.initStudentTextFields(BazaDanych.selectStudent().get(0));
				panelWpis.initUniversityTextFields(BazaDanych.selectUczelnia().get(0));
				panelWpis.getModelCbStudent().updateCustomComboBoxModel(BazaDanych.selectStudentIds());
				panelWpis.getModelCbUczelnia().updateCustomComboBoxModel(BazaDanych.selectUniversityIds());
				//po zaktualizowaniu modelu nalezy jeszcze przerysowac komponenty combobox zeby sobie zaktualizowaly dane
				panelWpis.getCbStudent().updateUI();
				panelWpis.getCbUniversity().updateUI();
				CardLayout cl = (CardLayout)getLayout();
				cl.show(panel, "panelWpis");
			}
		});
		
		JMenuItem menuItemPanelStatystyka = new JMenuItem("STATISTICS", new ImageIcon("stat.png"));
		menuItemPanelStatystyka.setMnemonic('A');
		menuItemPanelStatystyka.setAccelerator(KeyStroke.getKeyStroke('A', KeyEvent.CTRL_MASK));
		menuItemPanelStatystyka.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panelStatystyka.fillTextFields();
				// TODO Auto-generated method stub
				CardLayout cl = (CardLayout)getLayout();
				cl.show(panel, "panelStatystyka");
				
			}
		});
		
		JMenuItem menuItemPanelTabela = new JMenuItem("TABELA", new ImageIcon("tabela.png"));
		menuItemPanelTabela.setMnemonic('T');
		menuItemPanelTabela.setAccelerator(KeyStroke.getKeyStroke('T', KeyEvent.CTRL_MASK));
		menuItemPanelTabela.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				CardLayout cl = (CardLayout)getLayout();
//				cl.show(panel, "panelTabela");
//				
				JDialog dialog = new JDialog();
		//		dialog.setLocationRelativeTo(null);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				panelTabela = new PanelTabela();
				//infoPanel.updateFonts(MainPanel.fontSize);
				//SwingUtilities.updateComponentTreeUI(this.getRootPane().getParent());
				panelTabela.setVisible(true);
				dialog.setContentPane(panelTabela);
				dialog.pack();
				dialog.setVisible(true);
				
			}
		});
		
		menuPanel.add(menuItemPanelStudent);
		menuPanel.addSeparator();
		menuPanel.add(menuItemPanelUniversity);
		menuPanel.addSeparator();
		menuPanel.add(menuItemPanelRegister);
		menuPanel.addSeparator();
		menuPanel.add(menuItemPanelStatystyka);
		menuPanel.addSeparator();
		menuPanel.add(menuItemPanelTabela);
		
		
		//kolejne menu bedzie od czcionki
		//FontSize -> czcionka 12, 14, 16, 18
		//FontColor -> kolor red blue black green, przy kazdym kolorze ikonka np prostokat w wybarnym kolorze
		
		JMenu menuOptions = new JMenu ("OPTIONS");
		
		JMenu fontSize = new JMenu("Size");
		JMenu fontColor = new JMenu("Color");
		
		JMenuItem menuItemSize12 = new JMenuItem("12");
		menuItemSize12.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panelStudent.updateFont(12);
				panelUniversity.updateFonts(12);
				panelWpis.updateFonts(12);
				//ta linijka powoduje odswiezanie wszystkich komponentow w panelach, czyli zaladowanie nowej wartosci rozmiaru czcionki
		        SwingUtilities.updateComponentTreeUI(panel.getRootPane().getParent());
		        //dopasowanie rozmiarow okna do nowych rozmiarow komponentow
		        JFrame frame = (JFrame)panel.getRootPane().getParent(); //w taki sposob mozesz pobrac referencje do JFrame, w ktorym znajduje sie Twoj panel, potrzebujesz jej do wywolania metody pack()
		        frame.pack();
		        MainPanel.fontSize = 12;
			}
		});
		
		JMenuItem menuItemSize14 = new JMenuItem("14");
		menuItemSize14.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panelStudent.updateFont(14);
				panelUniversity.updateFonts(14);
				panelWpis.updateFonts(14);
				//ta linijka powoduje odswiezanie wszystkich komponentow w panelach, czyli zaladowanie nowej wartosci rozmiaru czcionki
		        SwingUtilities.updateComponentTreeUI(panel.getRootPane().getParent());
		        //dopasowanie rozmiarow okna do nowych rozmiarow komponentow
		        JFrame frame = (JFrame)panel.getRootPane().getParent(); //w taki sposob mozesz pobrac referencje do JFrame, w ktorym znajduje sie Twoj panel, potrzebujesz jej do wywolania metody pack()
		        frame.pack();
		        MainPanel.fontSize = 14;
			}
		});
		JMenuItem menuItemSize16 = new JMenuItem("16");
		menuItemSize16.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panelStudent.updateFont(16);
				panelUniversity.updateFonts(16);
				panelWpis.updateFonts(16);
				//ta linijka powoduje odswiezanie wszystkich komponentow w panelach, czyli zaladowanie nowej wartosci rozmiaru czcionki
		        SwingUtilities.updateComponentTreeUI(panel.getRootPane().getParent());
		        //dopasowanie rozmiarow okna do nowych rozmiarow komponentow
		        JFrame frame = (JFrame)panel.getRootPane().getParent(); //w taki sposob mozesz pobrac referencje do JFrame, w ktorym znajduje sie Twoj panel, potrzebujesz jej do wywolania metody pack()
		        frame.pack();
		        MainPanel.fontSize = 16;
			}
		});
		JMenuItem menuItemSize18 = new JMenuItem("18");
		menuItemSize18.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panelStudent.updateFont(18);
				panelUniversity.updateFonts(18);
				panelWpis.updateFonts(18);
				
				//ta linijka powoduje odswiezanie wszystkich komponentow w panelach, czyli zaladowanie nowej wartosci rozmiaru czcionki
		        SwingUtilities.updateComponentTreeUI(panel.getRootPane().getParent());
		        //dopasowanie rozmiarow okna do nowych rozmiarow komponentow
		        JFrame frame = (JFrame)panel.getRootPane().getParent(); //w taki sposob mozesz pobrac referencje do JFrame, w ktorym znajduje sie Twoj panel, potrzebujesz jej do wywolania metody pack()
		        frame.pack();
		        MainPanel.fontSize = 18;
			}
		});
		
		fontSize.add(menuItemSize12);
		fontSize.add(menuItemSize14);
		fontSize.add(menuItemSize16);
		fontSize.add(menuItemSize18);
		
		JMenuItem menuItemColorRed = new JMenuItem("RED", new ImageIcon("red.png"));
		menuItemColorRed.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panelStudent.updateColor(Color.RED);
				panelUniversity.updateColor(Color.RED);
				panelWpis.updateColor(Color.RED);
				
				  SwingUtilities.updateComponentTreeUI(panel.getRootPane().getParent());
			        //dopasowanie rozmiarow okna do nowych rozmiarow komponentow
			        JFrame frame = (JFrame)panel.getRootPane().getParent(); //w taki sposob mozesz pobrac referencje do JFrame, w ktorym znajduje sie Twoj panel, potrzebujesz jej do wywolania metody pack()
			        frame.pack();
				
			}
		});
		JMenuItem menuItemColorBlue = new JMenuItem("BLUE", new ImageIcon("blue.jpg"));
		menuItemColorBlue.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panelStudent.updateColor(Color.BLUE);
				panelUniversity.updateColor(Color.BLUE);
				panelWpis.updateColor(Color.BLUE);
				
				  SwingUtilities.updateComponentTreeUI(panel.getRootPane().getParent());
			        //dopasowanie rozmiarow okna do nowych rozmiarow komponentow
			        JFrame frame = (JFrame)panel.getRootPane().getParent(); //w taki sposob mozesz pobrac referencje do JFrame, w ktorym znajduje sie Twoj panel, potrzebujesz jej do wywolania metody pack()
			        frame.pack();
				
			}
		});
		JMenuItem menuItemColorBlack = new JMenuItem("BLACK", new ImageIcon("black.jpg"));
		menuItemColorBlack.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panelStudent.updateColor(Color.BLACK);
				panelUniversity.updateColor(Color.BLACK);
				panelWpis.updateColor(Color.BLACK);
				
				  SwingUtilities.updateComponentTreeUI(panel.getRootPane().getParent());
			        //dopasowanie rozmiarow okna do nowych rozmiarow komponentow
			        JFrame frame = (JFrame)panel.getRootPane().getParent(); //w taki sposob mozesz pobrac referencje do JFrame, w ktorym znajduje sie Twoj panel, potrzebujesz jej do wywolania metody pack()
			        frame.pack();
				
			}
		});
		JMenuItem menuItemColorGreen = new JMenuItem("GREEN", new ImageIcon("green.jpg"));
		menuItemColorGreen.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panelStudent.updateColor(Color.GREEN);
				panelUniversity.updateColor(Color.GREEN);
				panelWpis.updateColor(Color.GREEN);
				
				  SwingUtilities.updateComponentTreeUI(panel.getRootPane().getParent());
			        //dopasowanie rozmiarow okna do nowych rozmiarow komponentow
			        JFrame frame = (JFrame)panel.getRootPane().getParent(); //w taki sposob mozesz pobrac referencje do JFrame, w ktorym znajduje sie Twoj panel, potrzebujesz jej do wywolania metody pack()
			        frame.pack();
				
			}
		});
		fontColor.add(menuItemColorRed);
		fontColor.add(menuItemColorBlue);
		fontColor.add(menuItemColorGreen);
		fontColor.add(menuItemColorBlack);
		
		
		JMenu menuHelp = new JMenu("HELP");
		
		JMenuItem menuInfo = new JMenuItem("INFO", new ImageIcon("question.jpg"));
		menuInfo.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//JOptionPane.showMessageDialog(null, "Program created by Korcz");
				//CardLayout cl = (CardLayout)getLayout();
				//cl.show(panel, "panelInfo");
				
				JDialog dialog = new JDialog();
				dialog.setLocationRelativeTo(null);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				infoPanel = new InfoPanel();
				//infoPanel.updateFonts(MainPanel.fontSize);
				//SwingUtilities.updateComponentTreeUI(this.getRootPane().getParent());
				infoPanel.setVisible(true);
				dialog.setContentPane(infoPanel);
				dialog.pack();
				dialog.setVisible(true);
				

//				dialog.setLocation(200, 200);
			
			}
		});
		JMenuItem menuExit = new JMenuItem("EXIT", new ImageIcon("close.png"));
		menuExit.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				((JFrame)panel.getRootPane().getParent()).dispose();
			}
		});
		
		menuOptions.add(fontSize);
		menuOptions.add(fontColor);
		
		menuHelp.add(menuInfo);
		menuHelp.add(menuExit);
		
		menuBar.add(menuPanel);
		menuBar.add(menuOptions);
		menuBar.add(menuHelp);
		return menuBar;
	}
	
}