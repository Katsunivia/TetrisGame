import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;



public class TetrisWindow extends JFrame{
	
	int width, length;
	int block_x = 10;
	int block_y = 15;
	
	public JButton[][] blocks = new JButton[block_x][block_y];
	
	
	public TetrisWindow(int x, int y)
	{
		new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		width = x;
		length = y;
		
		GridLayout grid = new GridLayout(block_y,block_x,2,2); //(rows, cols)
		
		setSize(width,length);
		setLayout(grid);
		
		for(int h = 0; h < block_y; h++)
		{
			for(int i = 0; i < block_x; i++)
			{
				blocks[i][h] = new JButton();
				blocks[i][h].setSize(40,40);
				blocks[i][h].setBackground(new Color(0,0,0));
				add(blocks[i][h]);
			}
		}		
		
		setVisible(true);
		
				
		
						
	}
	
	
	
	/*public void setSize(int x, int y)
	{
		width = x;
		length = y;
	}*/

}
