package spaceinvaders;

import javax.swing.ImageIcon;


	public class Missile extends Sprite {

	    private final int H_SPACE = 6;
	    private final int V_SPACE = 1;

	    public Missile() {
	    }

	    public Missile(int x, int y) {

	        ImageIcon ii = new ImageIcon ("/Users/magedmahmoud/Documents/workspace/workspace/Spaceinvaders/shot.png","shot");
	        setImage(ii.getImage());
	        setX(x + H_SPACE);
	        setY(y - V_SPACE);
	    }
	}

