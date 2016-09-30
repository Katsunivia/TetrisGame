import javax.swing.*;

import java.awt.*;
import java.awt.event.*;



public class BlockEngine {
	
	int old_x, old_y, next_x, next_y, left_x, right_x;
	int id = 0;
	int delay = 500; //milliseconds
	boolean pause = true;
	int counter = 0;
	
	boolean[][] blockCheck;
	Timer timer;
	
	JFrame menu;
	TetrisBlocks block;	
	TetrisWindow window;

	BlockEngine(TetrisWindow w)
	{
		/*menu = new JFrame();
		menu.setSize(200,100);
		menu.setLayout(new FlowLayout());
		JLabel message = new JLabel("Press Exit to continue!");
		menu.add(message);
		menu.setVisible(false);*/
		
		window = w;
		block = new TetrisBlocks();
		blockCheck = new boolean[window.block_x][window.block_y];
		
		for(int i = 0; i < window.block_y; i++)
		{
			for(int j = 0; j < window.block_x; j++)
			{
				blockCheck[j][i] = false;
			}
		}
		
		window.blocks[block.x_pos][block.y_pos].setBackground(block.color);
		
		window.setFocusable(true);
		window.addKeyListener(new ControlBlock());
		
		
		
		ActionListener move = 
		new ActionListener() {
		 public void actionPerformed(ActionEvent evt) {
			 			 		
			
			left_x = block.x_pos - 1;
			right_x = block.x_pos + 1;
			next_y = block.y_pos + 1;
			
			
			if((next_y < 15)&&(!blockCheck[block.x_pos][next_y])) {
			old_x = block.x_pos;
			old_y = block.y_pos;
			block.y_pos++;	
			window.blocks[block.x_pos][block.y_pos].setBackground(block.color);
			blockCheck[block.x_pos][block.y_pos] = true;
			blockCheck[old_x][old_y] = false;
		  	System.out.println("Setting "+block.x_pos+" "+block.y_pos+" to new color");
		  	
		  	/*for(int i = 0; i < window.block_y; i++)
			{
				for(int j = 0; j < window.block_x; j++)
				{
					if(!blockCheck[j][i])
					{
						//System.out.println("Black: "+j+" "+i);
						window.blocks[j][i].setBackground(Color.BLACK);
					}
				}
			}*/		  	
		  	
		  	if(!blockCheck[old_x][old_y]){
		  	blockCheck[old_x][old_y] = false;
		  	window.blocks[old_x][old_y].setBackground(Color.BLACK);
		  	
		  	 }
		  	 		  	
		     
		    }			
			else {
				 counter = 0;
				 for(int p = 0; p < window.block_x; p++)
				 {
					 if(blockCheck[p][block.y_pos]){counter ++;}
				 }
				 if(counter == 10)
				 {
					 
						 for(int p = 0; p < window.block_x; p++)
						 {
							blockCheck[p][block.y_pos] = false;
						 	window.blocks[p][block.y_pos].setBackground(Color.BLACK);
						    
						 }
						 for(int q = block.y_pos-1; q > 0 ; q-- )
						 {
							 for(int p = 0; p < window.block_x; p++)
							 {
								 if(blockCheck[p][q])
								    {								    	
								    	blockCheck[p][q+1] = true;
								    	w.blocks[p][q+1].setBackground(w.blocks[p][q].getBackground());
								    	blockCheck[p][q] = false;
								    	w.blocks[p][q].setBackground(Color.BLACK);
								    }
							 }
						 }
						 
				 }
				 
		    	 block = new TetrisBlocks();
		    	 window.blocks[block.x_pos][block.y_pos].setBackground(block.color);
		    	 blockCheck[block.x_pos][block.y_pos] = true;
		    }
			/*if(block.y_pos < 14)
			{
				blockCheck[block.x_pos][block.y_pos] = false;
				block.y_pos++;
				System.out.println("new y_pos: "+(block.y_pos));
			}*/
			
		 }		      			
		};
		
									     		
		  timer = new Timer(delay, move);
		  timer.start();
		  
		
	}
	
	public class ControlBlock implements KeyListener
	{
		public void keyTyped(KeyEvent e)
		{
			System.out.println("LOL");
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			System.out.println("KeyPressed");
			id = e.getKeyCode();
			
			if(pause) {
			 switch(id)	{
			 case 37: //Left Arrow Key	    
		     if((block.x_pos > 0)&&(!blockCheck[left_x][block.y_pos])){
		    	old_x = block.x_pos;
			    old_y = block.y_pos;
			    block.x_pos = block.x_pos - 1;
			    blockCheck[block.x_pos][block.y_pos] = true;
			    window.blocks[block.x_pos][block.y_pos].setBackground(block.color);
			    blockCheck[old_x][old_y] = false;
			    window.blocks[old_x][old_y].setBackground(new Color(0,0,0));
		        }    					    
			 break;
			 
			 case 39: //Right Arrow Key
			 old_x = block.x_pos;
			 old_y = block.y_pos;			
			 if((block.x_pos < 9)&&(!blockCheck[right_x][block.y_pos])){
				block.x_pos = block.x_pos + 1;
				blockCheck[block.x_pos][block.y_pos] = true;
				window.blocks[block.x_pos][block.y_pos].setBackground(block.color);
				blockCheck[old_x][old_y] = false;
				window.blocks[old_x][old_y].setBackground(new Color(0,0,0));
				}			
			 break;
			
			 case 38: //Upper Arrow Key				 
			 break;
			
			 case 40: //Lower Arrow Key
			 if(delay > 50){
				delay = delay - 50; System.out.println("delay set to "+delay);
				}
			 timer.setDelay(delay);
			 break;
			 
			 case 27: //Escape Key
			 System.out.println("Game paused");
			 pause = false;
			 timer.stop();
			 //menu.setVisible(true);
			 break;
			 }
			}
			else
			{
				if(id == 27) // Escape Key
				{
					pause = true;
					timer.start();
					System.out.println("Game continues");
					//menu.setVisible(false);
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			int r = e.getKeyCode();
			if(r == 40)
			{
				delay = 500;
				System.out.println("delay set to "+delay);
				timer.setDelay(delay);
			}
			
		}
	}

	
	
	
}
