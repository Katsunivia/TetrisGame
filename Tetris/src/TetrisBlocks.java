import java.awt.*;

public class TetrisBlocks {
	
	int r = 255;
	int g = 255;
	int b = 255;
	Color color = new Color(r,g,b);
	
	int x_pos = 4;
	int y_pos = 0;
	
	int shape = 0;
	
	public TetrisBlocks()
	{
		setRandomColor();
		setRandomShape();
		System.out.println("RGB: "+r+" "+g+" "+b);
		//System.out.println("Shape: "+shape);
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
		shape = (int)(Math.random()*2);
		
		
	}
	
	void test()
	{
		
	}
	
}
