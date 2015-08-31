package model;

public class ModelInterface
{
	/*
	 * colorSelected < 7: the field must have this color
	 * if 7: move up
	 * if 8: move down
	 */
	private int colorSelected; //The color the user have selected
	private int boardActive;   //Number of the active board, -1 if not active
	private TheBoard tb;       //The active board
	private BoardHolder bh;    //The Vector that holds the boards
	private boolean saved;     //The current board is saved
	private boolean fileSaved; //The whole file is saved
	private String fileName;   //The filename that is open, "" if no filename is given
	
	//Constructor
	public ModelInterface()
	{
		boardActive = -1;
		saved = true;
		fileSaved = true;
	}
	
	public void getModel(int nr) throws Exception
	{
		try{
			tb = new TheBoard(bh.getModel(nr));
			saved = true;
			boardActive = nr;
		}catch(Exception e)
		{
			throw e;
		}
	}
	
	//True if a file is open
	public boolean exists()
	{
		if(bh==null)
			return false;
		return true;
	}
	
	public void selectColor(int selection)
	{
		colorSelected = selection;
	}
	
	public int getSelectedColor()
	{
		return colorSelected;
	}
	
	public void drawOnModel(int x, int y)
	{
		if(boardActive!=-1)
		{
			if(colorSelected<7) {
                //Ordinary thing
                tb.setBrick(x,y,colorSelected);
            }
			if(colorSelected==7) {
				tb.moveUp(x,y);
            }
			if(colorSelected==8) {
				tb.moveDown(x,y);
            }
			saved = false;
		}
	}
	
	public void moveLeft()
	{
		if(boardActive!=-1)
			tb.moveLeft();
		saved = false;
	}
	
	public void moveRight()
	{
		if(boardActive!=-1)
			tb.moveRight();
		saved = false;
	}
	
	public void deleteBoard()
	{
		if(boardActive!=-1)
		{
			bh.removeBoard(boardActive);
			boardActive = -1;
			saved = true;
			fileSaved = false;
		}
	}
	
	public void moveBoardBack()
	{
		if(boardActive!=-1)
		{
			if(bh.moveBoardBack(boardActive))
				boardActive--;
		}
	}
	
	public void moveBoardForward()
	{
		if(boardActive!=-1)
		{
			if(bh.moveBoardForward(boardActive))
				boardActive++;
		}
	}
	
	public void saveBoard()
	{
		if(boardActive!=-1)
		{
			bh.setModel(boardActive,tb);
			saved = true;
			fileSaved = false;
		}
	}
	
	public boolean saveFile(String filename)
	{
		if((bh!=null)&&(filename!=""))
		{
			fileName = filename; 
			bh.saveBoards(filename);
			fileSaved = true;
			return true;
		}
		return false;
	}
	
	public boolean saveFile()
	{
		if((bh!=null)&&(!fileName.equals("")))
		{
			bh.saveBoards(fileName);
			fileSaved = true;
			return true;
		}
		return false;
	}
	
	public void openFile(String filename)
	{
		fileName = filename;
		bh = new BoardHolder(filename);
		saved = true;
		fileSaved = true;
	}
	
	public void newFile()
	{
		fileName = "";
		bh = new BoardHolder();
		tb = null;
		boardActive = -1;
		saved = true;
		fileSaved = false;
	}
	
	public int getColor(int x, int y)
	{
		if(boardActive==-1)
			return -1;
		return tb.getBrick(x,y);
	}
	
	public int getActiveBoardNr()
	{
		return boardActive;
	}
	
	public int getNumberOfMoves()
	{
		if(tb!=null)
		{
			return tb.getNumberOfMoves();
		}
		return 0;
	}
	
	public boolean newBoard()
	{
		if(bh!=null)
		{
			fileSaved = false;
			return bh.addBoard();
		}
		else
			return false;
	}
	
	public int getNumberOfBoards()
	{
		if(bh!=null)
			return bh.getNumberOfBoards();
		else
			return 0;
	}
	
	public void setNumberOfMoves(int moves)
	{
		if(tb!=null)
		{
			saved = false;
			tb.setNumberOfMoves(moves);
		}
	}
	
	public boolean isSaved()
	{
		return saved;
	}
	
	public boolean fileIsSaved()
	{
		return fileSaved;
	}
}