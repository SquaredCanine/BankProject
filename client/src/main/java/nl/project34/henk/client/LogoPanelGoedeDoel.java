package nl.project34.henk.client;

import javax.swing.*;
import java.awt.*;

public class LogoPanelGoedeDoel extends JPanel
{
	public LogoPanelGoedeDoel()
	{
		ImageIcon afbeelding = new ImageIcon("/Users/Martijn/Documents/IntelliJ/project34/client/src/main/java/nl/project34/henk/client/Happy-Place-Thin-Line-Logo-Design.jpg");
		JLabel label = new JLabel(afbeelding);
		this.setBackground(new Color(204, 0,1));
		
		this.add(label);
	}
}