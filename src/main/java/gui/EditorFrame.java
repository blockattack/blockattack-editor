package gui;

import javax.swing.*;

import java.awt.event.*;
import model.ModelInterface;

public class EditorFrame extends JFrame implements WindowListener
{
	private final ModelInterface mi;
	private final EditorPanel ep;
	private final SidePanel sp;
	
	public EditorFrame()
	{
		mi = new ModelInterface();
		ep = new EditorPanel(mi);
		sp = new SidePanel(mi,ep);
		this.getContentPane().add(ep,"Center");
		this.getContentPane().add(sp,"East");
		this.setSize(1000,700); //The size of the frame in pixels
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Close the program when the 'x' is clicked
		this.addWindowListener(this);
	}
	
	private boolean saveNow()
	{
		if(mi.isSaved())
			return true;
		int option = JOptionPane.showConfirmDialog(null,"Save this board now?");
		if(option == JOptionPane.CANCEL_OPTION) {
			return false;
        }
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
	
    @Override
	public void windowClosing(WindowEvent e)
	{
		//Ask the user to save the file, before closing
		if(saveFileNow())
			System.exit(0);
	}
	
    @Override
	public void windowClosed(WindowEvent e){}
	
    @Override
	public void windowDeiconified(WindowEvent e){}
	
	//public void windowconified(WindowEvent e){}
	
    @Override
	public void windowOpened(WindowEvent e){}
	
    @Override
	public void windowIconified(WindowEvent e){}
	
    @Override
	public void windowDeactivated(WindowEvent e){}
	
    @Override
	public void windowActivated(WindowEvent e){}
}