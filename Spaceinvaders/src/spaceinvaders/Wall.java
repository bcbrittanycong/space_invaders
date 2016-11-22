package spaceinvaders;
import javax.swing.ImageIcon;



public class Wall extends Sprite implements Attributes {
	 private final int START_Y = 500; 
	 private final int START_X = 50;
	 private int width;
	 
	 public Wall() {
		

        ImageIcon ii = new ImageIcon ("/Users/magedmahmoud/Documents/workspace/workspace/Spaceinvaders/Walls.png","wall");
        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
  
	 }

	//making the wall move
	public void move() {
		x += dx;
		
    	if ( x != 650) {
			 dx = 2;
		 } else if (x >= 650) {
			 dx = -2;
		 }
	
		
	   }
	}
