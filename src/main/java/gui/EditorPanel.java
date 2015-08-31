package gui;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.*;

import javax.swing.*;
//import java.awt.*;
import model.ModelInterface;

public class EditorPanel extends JPanel
{
	private ModelInterface mi;
	private ImageIcon ii[];
	
	public EditorPanel(ModelInterface pointer)
	{
		mi = pointer;
		ii = new ImageIcon[7];
		for(int i=0;i<7;i++)
		{
			try{
				URL imgURL = this.getClass().getResource("/res/"+i+".png");
				ii[i] = new ImageIcon(imgURL);
			}catch(Exception e)
			{
				System.out.println("Error reading file: \"res/"+i+".png\" - Error: " + e);
                throw e;
			}
		}
		MouseListener listener = new MouseListener();
		this.addMouseListener(listener);
	}
	
	private void FieldClicked(int x,int y)
	{
		if((x<300)&&(y<600))
		mi.drawOnModel(x/50,y/50);
	}
	
    @Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i=0;i<6+1;i++)
			g.drawLine(i*50,0,i*50,600);
		for(int i=0;i<12+1;i++)
			g.drawLine(0,i*50,300,i*50);
		for(int i=0; i<12;i++)
			for(int j=0; j<6;j++)
			{
				if(mi.getColor(j,i)>-1)
					ii[mi.getColor(j,i)].paintIcon(this,g,50*j,50*i);
			}
		if(mi.getActiveBoardNr()!=-1)
			g.drawString("Active Puzzle: "+mi.getActiveBoardNr(),320,20);
		else
		{
			g.setColor(Color.RED);
			g.drawString("No active puzzle (Tip: Click \"Next\")",320,20);
			g.setColor(Color.BLACK);
		}
		g.drawString("Slected acton: ",320,40);
		if((mi.getSelectedColor()<7)&&(mi.getSelectedColor()>-1))
		{
			ii[mi.getSelectedColor()].paintIcon(this,g,320,60);
		}
		else
		{
			if(mi.getSelectedColor()==7)
				g.drawString("Move up",320,60);
			if(mi.getSelectedColor()==8)
				g.drawString("Move down",320,60);
			if(mi.getSelectedColor()==-1)
				g.drawString("Clear",320,60);
		}
		g.drawString("Number of boards in file: "+mi.getNumberOfBoards(),320,140);
		if(mi.getNumberOfMoves()==0)
			g.setColor(Color.RED);
		g.drawString("Number of moves: "+mi.getNumberOfMoves(),320,160);
		g.setColor(Color.RED);
		if(!mi.exists())
		{
			g.drawString("No active file, choose \"Create New ",320,180);
			g.drawString("File\" or \"Load From File\" first",320,200);
		}
		if(!mi.fileIsSaved())
		{
			g.drawString("WARNING: File has not been saved!",320,220);
		}
		if(!mi.isSaved())
		{
			g.drawString("WARNING: Current puzzle has not ",320,240);
			g.drawString("been saved!",320,260);
		}
		g.setColor(Color.BLACK);
	}
	
	private class MouseListener extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			//The left mouse button has been pressed
			if (SwingUtilities.isLeftMouseButton(e))
			{
				FieldClicked(e.getX(),e.getY());
			}	
			repaint();
		}
	}
}