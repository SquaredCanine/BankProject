package nl.project34.henk.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Toolkit;

public class LogoPanel extends JPanel
{
	public LogoPanel()
	{
		ImageIcon afbeelding = new ImageIcon("/Users/Martijn/Documents/IntelliJ/project34/client/src/main/java/nl/project34/henk/client/BankLogo-Cursief.png");
		JLabel label = new JLabel(afbeelding);
		this.setBackground(new Color(204, 0,1));
		
		this.add(label);
	}
}