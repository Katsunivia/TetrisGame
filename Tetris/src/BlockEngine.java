import javax.swing.*;

import java.awt.*;
import java.awt.event.*;



public class BlockEngine {
	
	int old_x, old_y;  //old x/y value before making it black  
	int next_x, next_y; //next x/y to check if there is already a block under
	int left_x, right_x; //right/left x to check if there is already a block left/right
	int start_x, start_y; //where a new created tetrisblock will start from
	
	int block_x, block_y; //Amount of Rows/Cols in the Tetris grid
	
	int key_id = 0; //e.getKeyCode() result
	
	Timer timer; //timer that drops the blocks (by increasing their y value)
	int delay = 500; //Timer delay in milliseconds
	
	boolean pause = false; //if the game is paused variable is true
	boolean gameover = false; // if you reach the top with a block and therefore lose the game, gameover becomes true
	
	int counter = 0; //variable to count how many blocks there are in a row
	boolean[][] blockCheck; //if there is a block at x/y position, blockCheck is true
		
	JFrame menu; //not implemented yet, menu that appears when game is paused
	TetrisBlocks block; //block that drops	
	TetrisWindow window; //window where the blocks are "drawn"

	BlockEngine(TetrisWindow w)
	{
		/*menu = new JFrame();
		menu.setSize(200,100);
		menu.setLayout(new FlowLayout());
		JLabel message = new JLabel("Press Exit to continue!");
		menu.add(message);
		menu.setVisible(false);*/
		window = w;
		block_x = window.getBlock_x();
		block_y = window.getBlock_y();
		
		blockCheck = new boolean[block_x][block_y];
		
		for(int i = 0; i < block_y; i++)
		{
			for(int j = 0; j < block_x; j++)
			{
				blockCheck[j][i] = false; //unnecessary, all positions on the grid are false by default
			}
		}
		int start_x = (int) (block_x/2);
		int start_y = 0;
		block = new TetrisBlocks(start_x, start_y);//first block at the start of the game
		window.blocks[block.x_pos][block.y_pos].setBackground(block.color);	
		
		window.addKeyListener(new ControlBlock());
				
		ActionListener move = 
		new ActionListener() {
		 public void actionPerformed(ActionEvent evt) {
			 			 					
			left_x = block.x_pos - 1;
			right_x = block.x_pos + 1;
			next_y = block.y_pos + 1;
						
			if((next_y < block_y)&&(!blockCheck[block.x_pos][next_y])) {
			
				old_x = block.x_pos;
				old_y = block.y_pos;
				block.y_pos++;	
				window.blocks[block.x_pos][block.y_pos].setBackground(block.color);
				blockCheck[block.x_pos][block.y_pos] = true;
				blockCheck[old_x][old_y] = false;
				System.out.println("x:"+block.x_pos+" y:"+block.y_pos+" colored");
		  	
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
		  			System.out.println("x:"+old_x+" y:"+old_y+" black");
		  	}
		  	 		  	
		     
		    }			
			else {
				 if(block.y_pos == 0) {
					 
					 gameover = true;
					 pause = true;
					 timer.stop();
					 System.out.println("Game Over! Timer Stop!");
				 }
				 else {
					counter = 0;
				 	for(int p = 0; p < block_x; p++)
				 	{
				 		if(blockCheck[p][block.y_pos]){
				 			counter ++;
				 		}
				 	}
				 	if(counter == block_x)
				 	{
				 		 System.out.println("Line cleared: ");
						 for(int p = 0; p < block_x; p++)
						 {
							blockCheck[p][block.y_pos] = false;
						 	window.blocks[p][block.y_pos].setBackground(Color.BLACK);
						 	
						    
						 }
						 System.out.print("Blocks blacked, ");
						 for(int q = block.y_pos-1; q > 0 ; q-- )
						 {
							 for(int p = 0; p < block_x; p++)
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
						 System.out.println("Blocks moved Down");
				 	}			 
				 	block = new TetrisBlocks(start_x, start_y);
				 	window.blocks[block.x_pos][block.y_pos].setBackground(block.color);
				 	blockCheck[block.x_pos][block.y_pos] = true;
				 	System.out.println("New Block created");
				 }
		    }		
		 }		      			
		};
											     		
		  timer = new Timer(delay, move);
		  timer.start();
		  System.out.println("Game starts");
		  
		
	}
	
	public class ControlBlock implements KeyListener
	{
		public void keyTyped(KeyEvent e)
		{
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			System.out.println("keyPressed:");
			key_id = e.getKeyCode();
			
			if(!pause) {
			 switch(key_id)	{
			 case 37: //Left Arrow Key	    
		     if((left_x >= 0)&&(!blockCheck[left_x][block.y_pos])){
		    	System.out.println("Left ");
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
			 		
			 if((right_x < block_x)&&(!blockCheck[right_x][block.y_pos])){
				System.out.println("Right ");
				old_x = block.x_pos;
				old_y = block.y_pos;	
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
				delay = delay - 50;
				System.out.println("Down ");
				System.out.println("delay:"+delay);
				}
			 timer.setDelay(delay);
			 break;
			 
			 case 27: //Escape Key
			 System.out.println("Game paused, Timer paused");
			 pause = true;
			 timer.stop();
			 //menu.setVisible(true);
			 break;
			 
			 default:
		     break;
			 }
			 
			}
			else
			{
				if((key_id == 27)&&(!gameover)) // Escape Key
				{
					pause = false;
					timer.start();
					System.out.println("Game continues, Timer started");
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
				System.out.print("Delay normal ");
				System.out.println("delay:"+delay);
				timer.setDelay(delay);
			}
			
		}
	}

	
	
	
}
