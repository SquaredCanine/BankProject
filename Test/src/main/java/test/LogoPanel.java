package test;

import javax.swing.*;
import java.awt.*;

public class LogoPanel extends JPanel
{
	public LogoPanel()
	{
		ImageIcon afbeelding = new ImageIcon("/Users/Martijn/Documents/IntelliJ/project34/client/src/main/java/nl/project34/henk/client/BankLogo-Cursief.png");
		JLabel label = new JLabel(afbeelding);
		this.setBackground(new Color(241,241,241));
		
		this.add(label);
	}
}