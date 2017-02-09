package nl.project34.henk.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Toolkit;

public class ControlPanel extends JPanel
{
	private Color myBackgroundColor = new Color(204, 0,1);
	
	public ControlPanel()
	{
		// BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		GridLayout layout = new GridLayout(2,2);
		this.setLayout(layout);
		//this.setBackground(myBackgroundColor);
		
		
	}
}