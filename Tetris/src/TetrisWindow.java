import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;



public class TetrisWindow extends JFrame{
	
	int width = 0;
	int height = 0;
	int block_x = 9;
	int block_y = 15;
	
	int blockHeight = 1;
	int blockWidth = 1;
	
	public JButton[][] blocks = new JButton[block_x][block_y];
	
	
	public TetrisWindow(int x, int y)
	{
		new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		width = x;
		height = y;
		
		blockHeight = (int)(height/block_y)-5;
		blockWidth = (int)(width/block_x)-5;
		
		GridLayout grid = new GridLayout(block_y,block_x); //(rows, cols)
		
		setSize(width,height);
		setLayout(grid);
		
		for(int i = 0; i < block_y; i++)
		{
			for(int h = 0; h < block_x; h++)
			{
				blocks[h][i] = new JButton();
				blocks[h][i].setSize(blockWidth, blockHeight);
				blocks[h][i].setEnabled(false);;
				blocks[h][i].setBackground(new Color(0,0,0));
				add(blocks[h][i]);
			}
		}		
		
		setResizable(false);
		setVisible(true);								
						
	}
	
	//Getters and Setters
	public int getBlockHeight() {
		return blockHeight;
	}

	public void setBlockHeight(int blockHeight) {
		this.blockHeight = blockHeight;
	}

	public int getBlockWidth() {
		return blockWidth;
	}

	public void setBlockWidth(int blockWidth) {
		this.blockWidth = blockWidth;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getBlock_y() {
		return block_y;
	}

	public void setBlock_y(int block_y) {
		this.block_y = block_y;
	}

	public void setBlock_x(int block_x) {
		this.block_x = block_x;
	}

	public int getBlock_x()
	{
		return block_x;
	}
	
	

}
