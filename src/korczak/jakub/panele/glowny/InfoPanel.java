package korczak.jakub.panele.glowny;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class InfoPanel extends JPanel
{
	private JTextArea taText;

	public InfoPanel()
	{

		super(new BorderLayout());
		
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		StringBuilder sb = new StringBuilder();

		BufferedReader br = null;

		try
		{

			String sCurrentLine;

			br = new BufferedReader(new FileReader("C:\\tekst.txt"));

			while ((sCurrentLine = br.readLine()) != null)
			{
				sb.append(sCurrentLine + " \n");
				//System.out.println(sCurrentLine);
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (br != null)
					br.close();
			} catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	
		taText = new JTextArea(sb.toString(), 10, 30);
		taText.setFont(new Font("Arial", 3, 14));
		taText.setEditable(false);
		taText.setLineWrap(true);
		taText.setWrapStyleWord(true);
		
		/*
		JTextPane textPane = new JTextPane();
		textPane.setText(sb.toString());
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		*/
		JScrollPane scrollPane = new JScrollPane(taText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JPanel panelInfo = new JPanel(new GridLayout(1, 1));

		panelInfo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED, 3, true),
				"INSTUCTION", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("Courier New", Font.BOLD, 20), Color.BLACK));

	//	panelInfo.add(taText);
		panelInfo.add(scrollPane);

		add(panelInfo, BorderLayout.CENTER);

	}
}
