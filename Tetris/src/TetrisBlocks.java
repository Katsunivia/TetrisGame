import java.awt.*;

public class TetrisBlocks {
	
	int r = 255;
	int g = 255;
	int b = 255;
	Color color = new Color(r,g,b);
	
	int x_pos = 2;
	int y_pos = 0;
	
	int[][] block_coord;
	int shape = 0;
	int block_Number = 1;
	
	public TetrisBlocks(int x, int y)
	{
		
		x_pos = x;
		y_pos = y;
		setRandomColor();
		setRandomShape();
		System.out.println("RGB: "+r+" "+g+" "+b);
		block_coord = new int[block_Number][2];
		//System.out.println("Shape: "+shape);
	}
	
	public void rotate()
	{
		
	}
	
	public void setStartCoordinates(int x, int y)
	{		
			block_coord[0][0] = 4;
			block_coord[0][1] = 0;
			block_coord[1][0] = 4;
			block_coord[1][1] = 0 + 1;
	}
	
	public void setRandomColor()
	{	
		r = (int)(Math.random()*255);
	  	g = (int)(Math.random()*255);
	  	b = (int)(Math.random()*255);
	  	color = new Color(r,g,b);
	}
	
	public void setRandomShape()
	{
		shape = (int)(Math.random()*3);
		
		switch(shape) {
		case 0:
			block_Number = 1;
			break;
		case 1:
			block_Number = 2;
			break;
		case 2:
			block_Number = 2;
			break;
		case 3:
			block_Number = 2;
			break;
		
		}
	}

	
}
