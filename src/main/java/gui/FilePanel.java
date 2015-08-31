package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;

import javax.swing.*;

import model.ModelInterface;

public class FilePanel extends JPanel
{
	private final JButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;
	private final ModelInterface mi;
	private final EditorPanel ep;
	
	private boolean saveNow()
	{
		if(mi.isSaved())
			return true;
		int option = JOptionPane.showConfirmDialog(null,"Save this board now?");
		if(option == JOptionPane.CANCEL_OPTION)
			return false;
		if(option == JOptionPane.YES_OPTION)
		{
			mi.saveBoard();
			return true;
		}
		if(option == JOptionPane.NO_OPTION)
			return true;
		return false;
	}
	
	private boolean saveFileNow()
	{
		if(!saveNow())
			return false;
		if(mi.fileIsSaved())
			return true;
		int option = JOptionPane.showConfirmDialog(null,"Save current file?");
		if(option == JOptionPane.CANCEL_OPTION)
			return false;
		if(option == JOptionPane.YES_OPTION)
		{
			if(mi.saveFile())
				return true;
			JOptionPane.showMessageDialog(null,"Couldn't auto save, use \"Save As\"");
			return false;
		}
		if(option == JOptionPane.NO_OPTION)
			return true;
		return false;
	}
	
	public FilePanel(ModelInterface mi,EditorPanel ep)
	{
		this.mi = mi;
		this.ep = ep;
		setLayout(new GridLayout(7,2));
		b0 = new JButton("Save Puzzle");
		add(b0);
		b1 = new JButton("Save To File");
		add(b1);
		b8 = new JButton("Create new File");
		b9 = new JButton("Save File As");
		add(b8);
		add(b9);
		b2 = new JButton("Load From File");
		add(b2);
		b3 = new JButton("Load a new puzzle");
		add(b3);
		b4 = new JButton("Previous");
		b5 = new JButton("Next");
		add(b4);
		add(b5);
		b6 = new JButton("Set #ofMoves");
		add(b6);
		b7 = new JButton("New puzzle");
		add(b7);
		b11 = new JButton("Move puzzle forward");
		
		b12 = new JButton("Move puzzle backwards");
		add(b12);
		add(b11);
		b10 = new JButton("Delete puzzle");
		add(b10);
		
		ButtonListener listener = new ButtonListener();
		
		b0.addActionListener(listener);
		b1.addActionListener(listener);
		b2.addActionListener(listener);
		b3.addActionListener(listener);
		b4.addActionListener(listener);
		b5.addActionListener(listener);
		b6.addActionListener(listener);
		b7.addActionListener(listener);
		b8.addActionListener(listener);
		b9.addActionListener(listener);
		b10.addActionListener(listener);
		b11.addActionListener(listener);
		b12.addActionListener(listener);
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			Object source = evt.getSource();
			
			if(source == b0)
				mi.saveBoard();
			if(source == b1)
				if(saveNow())
					mi.saveFile();
			if(source == b2)
			{
				String filename;
				JFileChooser FC = new JFileChooser(); //Definere JFileCHooser
				int returnVal = FC.showOpenDialog(null); //Shows "Open" dialog
				if (returnVal == JFileChooser.APPROVE_OPTION) //If the user selected a file
				{
					File selectedFile = FC.getSelectedFile();
					filename = selectedFile.getPath();
					mi.openFile(filename);
				}
			}
			if(source == b3)
			{
				if(saveNow())
				try{
					int boardToOpen = 0;
					String numStr = JOptionPane.showInputDialog("Enter board number: ");
					boardToOpen = Integer.parseInt(numStr);
					if((boardToOpen>-1)&&(boardToOpen<=mi.getNumberOfBoards()))
					mi.getModel(boardToOpen);
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"Couldn't open that board, reason: "+e);
				}
			}
			if(source == b4)
				if(saveNow())
				try{
					int boardToOpen = mi.getActiveBoardNr()-1;
					if(boardToOpen>-1)
					mi.getModel(boardToOpen);
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"Couldn't open that board, reason: "+e);
				}
			if(source == b5)
			{
				if(saveNow())
				try{
					int boardToOpen = mi.getActiveBoardNr()+1;
					if(boardToOpen<mi.getNumberOfBoards())
						mi.getModel(boardToOpen);
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"Couldn't open that board, reason: "+e);
				}
			}
			if(source == b7)
			{
				if(!mi.newBoard())
					JOptionPane.showMessageDialog(null,"Couldn't create new puzzle");
			}
			if(source ==b6)
			{
				String numStr = JOptionPane.showInputDialog("Enter number of allowed moves: ");
				int num = 0;
				try{
					num = Integer.parseInt(numStr);
					mi.setNumberOfMoves(num);
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"Not a valid number");
				};
			}
			if(source == b8) //New File
			{
				if(saveFileNow())
				{
					mi.newFile();
				}
			}
			if(source == b9) //Save as
			{
				if(saveNow())
				{
					String filename;
					JFileChooser FC = new JFileChooser();
					int returnVal = FC.showSaveDialog(null); //Shows "Save" dialog
					if (returnVal == JFileChooser.APPROVE_OPTION) //If the user selected a file
					{
						File selectedFile = FC.getSelectedFile();
						filename = selectedFile.getPath();
						mi.saveFile(filename);
					}
				}
			}
			
			if(source == b10) //Delete Puzzle
			{
				mi.deleteBoard();
			}
			
			if(source == b11) //Move Puzzle Forward
			{
				mi.moveBoardForward();
			}
			
			if(source == b12) //Move Puzzle back
			{
				mi.moveBoardBack();
			}
			ep.repaint();
		}
	}
}