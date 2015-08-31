package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.ModelInterface;

public class ColorPanel extends JPanel
{
	private JButton b0, b1, b2, b3, b4, b5, b6, b7;
	private ModelInterface mi;
	private EditorPanel ep;
	
	public ColorPanel(ModelInterface mi,EditorPanel ep)
	{
		try{
		this.mi = mi;
		this.ep = ep;
		ButtonListener listener = new ButtonListener();
		setLayout(new GridLayout(3,3));
		b0 = new JButton(new ImageIcon(this.getClass().getResource("/res/0.png")));
		add(b0);
		b1 = new JButton(new ImageIcon(this.getClass().getResource("/res/1.png")));
		add(b1);
		b2 = new JButton(new ImageIcon(this.getClass().getResource("/res/2.png")));
		add(b2);
		b3 = new JButton(new ImageIcon(this.getClass().getResource("/res/3.png")));
		add(b3);
		b4 = new JButton(new ImageIcon(this.getClass().getResource("/res/4.png")));
		add(b4);
		b5 = new JButton(new ImageIcon(this.getClass().getResource("/res/5.png")));
		add(b5);
		b6 = new JButton(new ImageIcon(this.getClass().getResource("/res/6.png")));
		add(b6);
		b7 = new JButton("Clear");
		add(b7);
		
		b0.addActionListener(listener);
		b1.addActionListener(listener);
		b2.addActionListener(listener);
		b3.addActionListener(listener);
		b4.addActionListener(listener);
		b5.addActionListener(listener);
		b6.addActionListener(listener);
		b7.addActionListener(listener);
		}catch(Exception e)
		{
			
		}
	}
	
	private class ButtonListener implements ActionListener
	{
        @Override
		public void actionPerformed(ActionEvent evt)
		{
			Object source = evt.getSource();
			
			if (source == b0) {
				mi.selectColor(0);
            }
			if (source == b1) {
				mi.selectColor(1);
            }
			if (source == b2) {
				mi.selectColor(2);
            }
			if (source == b3) {
				mi.selectColor(3);
            }
			if (source == b4) {
				mi.selectColor(4);
            }
			if (source == b5) {
				mi.selectColor(5);
            }
			if (source == b6) {
				mi.selectColor(6);
            }
			if (source == b7) {
				mi.selectColor(-1);
            }
			ep.repaint();
		}
	}
}