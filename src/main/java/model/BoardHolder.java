


package model; //Part of the data model

import java.util.Vector;
import java .io.*;
import java.util.StringTokenizer;

public class BoardHolder
{
	private Vector theBoards;
	
	public BoardHolder(String filename)
	{
		try{
			FileReader fr = new FileReader(filename);
			BufferedReader inFile = new BufferedReader(fr);
		
			theBoards = new Vector();
			
			String line = inFile.readLine();
			StringTokenizer tokenizer = new StringTokenizer(line);
			int nrOfBoards =  Integer.parseInt(tokenizer.nextToken());
			
			for(int i=0;i<nrOfBoards;i++)
			{
				TheBoard ourBoard = new TheBoard();
				line = inFile.readLine();
				tokenizer = new StringTokenizer(line);
				ourBoard.setNumberOfMoves(Integer.parseInt(tokenizer.nextToken()));
				for(int j=0;j<12;j++)
				{
					line = inFile.readLine();
					tokenizer = new StringTokenizer(line);
					for(int k=0;k<6;k++)
					{
						ourBoard.setBrick(k,j,Integer.parseInt(tokenizer.nextToken()));						
					}
				}
				theBoards.add(i,ourBoard);
			}
			
			
		}catch(Exception e)
		{
			System.out.println("Error: "+e);
		}
	}
	
	public BoardHolder()
	{
		theBoards = new Vector();
	}
	
	public boolean saveBoards(String filename)
	{
		try{
			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter outFile = new PrintWriter(bw);
			
			outFile.println(getNumberOfBoards());
			for(int i=0;i<getNumberOfBoards();i++)
			{
				outFile.println(((TheBoard)theBoards.get(i)).getNumberOfMoves());
				for(int j=0;j<12;j++)
				{
					for(int k=0;k<6;k++)
					{
						outFile.print(((TheBoard)theBoards.get(i)).getBrick(k,j));
						if(k<5)
							outFile.print(" ");
						else
							outFile.println();
					}
				}
			}
			outFile.close();
		}catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	public TheBoard getModel(int nr) throws Exception
	{
		try{
			if(theBoards.size()<nr)
				throw new Exception("There is not that many boards. Remember the first board is number 0!");
			return (TheBoard)theBoards.get(nr);
		}catch(Exception e)
		{
			throw new Exception("Couldn't open board");
		}
	}
	
	public boolean setModel(int nr, TheBoard model)
	{
		theBoards.set(nr,model);
		return true;
	}
	
	public int getNumberOfBoards()
	{
		return theBoards.size();
	}
	
	public boolean addBoard()
	{
		if(!(theBoards.size()<49))
			return false;
		theBoards.add(new TheBoard());
		return true;
	}
	
	public boolean removeBoard(int number)
	{
		if(theBoards!=null)
			theBoards.remove(number);
		return true;	
	}
	
	public boolean moveBoardBack(int nr)
	{
		if(theBoards==null)
			return false;
		if(nr<1) //We can't move back
			return false;
		if(nr>getNumberOfBoards())
			return false;
		TheBoard tb = (TheBoard)theBoards.get(nr);
		theBoards.remove(nr);
		theBoards.add(nr-1,tb);
		return true;
	}
	
	public boolean moveBoardForward(int nr)
	{
		if(theBoards==null)
			return false;
		if(nr>getNumberOfBoards()-2) //We can't move forward
			return false;
		if(nr<0)
			return false;
		TheBoard tb = (TheBoard)theBoards.get(nr);
		theBoards.remove(nr);
		theBoards.add(nr+1,tb);
		return true;
	}
}