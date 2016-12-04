package spaceinvaders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Spaceboard extends JPanel implements Runnable, Attributes { 

	    Image img;
		private Dimension d;
	    private ArrayList aliens;   
	 // private ArrayList walls;
	    private Player player;
	    private Missile missile;
	    private Wall wall;
	    private int alienX = 150;
	    private int alienY = 5;
	 //  private int wallX = 150;
	 //  private int wallY = 10;
	    
	    private int direction = -1;
	    private int deaths = 0;

	    private boolean ingame = true;
	 
	    private String message = "Game Over";
	    private String life = "life";
	    private Thread animator;

	    public Spaceboard() 
	    {
	        addKeyListener (new TAdapter());
	        setFocusable(true);
	        img = Toolkit.getDefaultToolkit().createImage("/Users/Tardis/git/space_invaders/Spaceinvaders/chalkboard-resized.png");   	 	            
	        //img.getScaledInstance(BOARD_W, BOARD_H, Image.SCALE_DEFAULT);
	        d = new Dimension(BOARD_W, BOARD_H); 
	        //setBackground(Color.BLACK);
	        gameInit();
	        setDoubleBuffered(true);
	    }

	    public void addNotify() {
	        super.addNotify();
	        gameInit();
	    }
	        public void gameInit() {

	            aliens = new ArrayList();
	          /* 
	           * Code for wanting more than one wall. 
	           *  walls = new ArrayList();
	            
	            ImageIcon iii= new ImageIcon ("/Users/magedmahmoud/Documents/workspace/workspace/Spaceinvaders/Walls.png","wall");
	            for (int i=0; i < 4; i++) {
	            	for (int j=0; j < 4; j++) {
	            		
	                    Wall wall = new Wall(wallX + 300*j, wallY + 500*i);
	                    wall.setImage(iii.getImage());
	                    walls.add(wall);    
	            	}
	            }
	            */
	            
	            ImageIcon ii= new ImageIcon ("/Users/Tardis/git/space_invaders/Spaceinvaders/alien.png","Alienship");
	            for (int i=0; i < 4; i++) {
	            	for (int j=0; j < 6; j++) {
	            		//Spacing for the aliens now done right
	                    Alien alien = new Alien(alienX + 65*j, alienY + 65*i);
	                    alien.setImage(ii.getImage());
	                    aliens.add(alien);           
	                }
	            }

	            
	       
	            player = new Player();
	            missile = new Missile();
	            wall = new Wall();

	            if (animator == null || !ingame) {
	                animator = new Thread(this);
	                animator.start();
	            }
	        }
	        
	        public void drawAliens (Graphics g)
	        {
	            Iterator it = aliens.iterator();
	            
	            while (it.hasNext()) {
	                Alien alien = (Alien) it.next();

	                if (alien.isVisible()) {
	                    g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
	                }

	                if (alien.isDestroyed()) {
	                    alien.die();
	                }
	            }
	        }

	        public void drawPlayer(Graphics g) {

	            if (player.isVisible()) {
	                g.drawImage(player.getImage(), player.getX(), player.getY(), this);
	            	}
	            
	            if (player.isDestroyed()) {
	                player.die();
	                ingame = false;
	            	}
	        }
      
	        
	        public void drawMissile(Graphics g) {
	            if (missile.isVisible())
	                g.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
	        }

	        public void drawWall(Graphics g) {
	        //	Iterator itw = walls.iterator();

	       //     while (itw.hasNext()) {
	       //        Wall wall = (Wall) itw.next();

	            if (wall.isVisible())
	                g.drawImage(wall.getImage(), wall.getX(), wall.getY(), this);
	            }
		   //	}
	

	        
	        public void drawBombing(Graphics g) {

	            Iterator i3 = aliens.iterator();

	            while (i3.hasNext()) {
	                Alien a = (Alien) i3.next();

	                Alien.Bomb b = a.getBomb();

	                if (!b.isDestroyed()) {
	                    g.drawImage(b.getImage(), b.getX(), b.getY(), this); 
	                }
	            }
	        }
	        
	       

	        
	        public void paint(Graphics g)
	        {
	        	//Image img;
	 	       // Loads the background image and stores in img object.
	 	       //img = Toolkit.getDefaultToolkit().createImage("/Users/Tardis/git/space_invaders/Spaceinvaders/chalkboard.png");   	 	            
	           super.paint(g);
	           //g.setColor(Color.black);
	           //g.fillRect(0, 0, d.width, d.height);
	           g.drawImage(img, 0, 0, BOARD_W, BOARD_H, this);
	           g.setColor(Color.blue);   
	           

	          if (ingame) {
	        	
	            
	        	g.drawLine(0, FLOOR, BOARD_W, FLOOR);
	            // add a message for lives and aliens remaining.
	            drawAliens(g);
	            drawPlayer(g);
	            drawMissile(g);
	            drawBombing(g);
	            drawWall(g);
	            
	            Font small = new Font("COMIC_SANS", Font.BOLD, 14);
	            FontMetrics metr = this.getFontMetrics(small);
	           
	            g.setColor(Color.white);
	            g.setFont(small);
	            g.drawString(life, MessageW/2, MessageH/2);
	           
	          }

	          Toolkit.getDefaultToolkit().sync();
	          g.dispose();
	        }
	        
	        public void gameOver()
	        {

	            Graphics g = this.getGraphics();

	            g.setColor(Color.black);
	            g.fillRect(0, 0, BOARD_W, BOARD_H);

	            g.setColor(new Color(0, 32, 48));
	            g.fillRect(50, BOARD_W/2 - 30, BOARD_W-100, 50);
	            g.setColor(Color.white);
	            g.drawRect(50, BOARD_W/2 - 30, BOARD_W-100, 50);

	            Font small = new Font("COMIC_SANS", Font.BOLD, 14);
	            FontMetrics metr = this.getFontMetrics(small);
	           
	            g.setColor(Color.white);
	            g.setFont(small);
	            g.drawString(message, (BOARD_W - metr.stringWidth(message))/2, 
	                BOARD_W/2);
	        }
	        
	     
	        
	        public void animationCycle()  {

	            if (deaths == ALIENS_LEFT) {
	                ingame = false;
	                message = "You've saved us all!";
	            }

	            // player
	            // method for the wall to move called
	            wall.move();       
	            player.act();
	   
	            	
	            
	            // ADD WIDER DETECTION (kinda done)
	            // Missile
	            if (missile.isVisible()) {
	                Iterator it = aliens.iterator();
	                int MissileX = missile.getX();
	                int MissileY = missile.getY();

	                while (it.hasNext()) {
	                    Alien alien = (Alien) it.next();
	                    int alienX = alien.getX();
	                    int alienY = alien.getY();
	                    
	                    //if the differnce between the x of the missile and alien is in the specfied range, (same with y)
	                    //instead of bieng the exact x and y
	                    
	                    if (alien.isVisible() && missile.isVisible()) {
	                        if (MissileX - (alienX) >= 20 && 
	                            MissileX - (alienX + ALIEN_W) <= 20 &&
	                            MissileY - (alienY) >= 20 &&
	                            MissileY - (alienY+ALIEN_H) <= 20) {
	                                ImageIcon ii = new ImageIcon ("/Users/magedmahmoud/Documents/workspace/workspace/Spaceinvaders/explosion.png","expl");                      
	                                alien.setImage(ii.getImage());
	                                alien.setDestroyed(true);
	                                deaths++;
	                                //find a way to let it lag before it dies, maybe instead of changing the photo itself, enter explosion alone
	                                //or let it live for 2x movements? 
	                                missile.die();
	                                alien.die();
	                         }    
	                    }
	                }

	                int y = missile.getY();
	                y -= 4;
	                if (y < 0)
	                    missile.die();
	                else missile.setY(y);
	            }

	            // aliens

	             Iterator it1 = aliens.iterator();

	             while (it1.hasNext()) {
	                 Alien a1 = (Alien) it1.next();
	                 int x = a1.getX();

	                 if (x  >= BOARD_W - BORDER_R && direction != -1) {
	                     direction = -1;
	                     Iterator i1 = aliens.iterator();
	                     while (i1.hasNext()) {
	                         Alien a2 = (Alien) i1.next();
	                         a2.setY(a2.getY() + MOVE_DOWN);
	                     }
	                 }

	                if (x <= BORDER_L && direction != 1) {
	                    direction = 1;

	                    Iterator i2 = aliens.iterator();
	                    while (i2.hasNext()) {
	                        Alien a = (Alien)i2.next();
	                        a.setY(a.getY() + MOVE_DOWN);
	                    }
	                }
	            }


	            Iterator it = aliens.iterator();

	            while (it.hasNext()) {
	                Alien alien = (Alien) it.next();
	                if (alien.isVisible()) {

	                    int y = alien.getY();

	                    if (y > FLOOR - ALIEN_H) {
	                        ingame = false;
	                        message = "Invasion!";
	                        
	                    }

	                    alien.act(direction);          
	                    	
	                }
	            }
    
	            // bombs
	            //alien bombing and how many lives it takes from the player. 
	            Iterator i3 = aliens.iterator();
	            Random generator = new Random();

	            while (i3.hasNext()) {
	                int Missile = generator.nextInt(15);
	                Alien a = (Alien) i3.next();
	                Alien.Bomb b = a.getBomb();
	                if (Missile == LIVES && a.isVisible() && b.isDestroyed()) {

	                    b.setDestroyed(false);
	                    b.setX(a.getX());
	                    b.setY(a.getY());   
	                    
	                }
	                //ADD WALL DETECTION
	                //Wall detection needs an accurate X detection
	                //add animation when player is shot
	                int bombX = b.getX();
	                int bombY = b.getY();
	                int playerX = player.getX();
	                int playerY = player.getY();
	                int wallX = wall.getX();  
	                int wallY = wall.getY();
	                int missileX = missile.getX();
	                int missileY = missile.getY();
	              
	                int lives = 0;
	                
	            
	                
	                
	                if (player.isVisible() && b.isVisible()) {
	                    if (bombX - (playerX) >= 20 && 
	                        bombX - (playerX+PLAYER_W) <= 20 &&
	                        bombY - (playerY) >= 20 &&          
	                        bombY - (playerY+PLAYER_H) <= 20 ) {
	                    	
	                    	
	                    		ImageIcon ii = new ImageIcon ("/Users/magedmahmoud/Documents/workspace/workspace/Spaceinvaders/explosion.png","expl");	                         
	                            player.setImage(ii.getImage());
	                            // display how many times you were hit
	                            // need life string to read this and display it, for some reason this does not get read. 
	                            lives ++;
	                            life = "Life = " + new Integer(lives).toString() + "/3";
	                            
	                            if (lives == 5) {
	                            	
	                            player.setDestroyed(true);
	                            b.setDestroyed(true);
	                            }
	                            
	                            // GAME NOW ENDS WHEN YOU DIE ALOT NOT JUST ONCE.
	                            if (player.isDestroyed()); {
	    	                    	ingame = false;
	    	                    	message = "GAME OVER!!";
	    	                    }
	    	                    
	                        }
	                }
	                
	                if (wall.isVisible() && missile.isVisible()) {
	                	if (wallX - (missileX) >= 20 &&          
	                	    wallX - (missileX+WallW) <= 20 &&
	                        wallY - (missileY) >= 5 &&          
	                        wallY - (missileY+WallH) <= 5){
	                		missile.setDestroyed(true);
	                		missile.die();
	                	}
	            }
	                if (wall.isVisible() && b.isVisible()) {
	                	if (wallX - (bombX) >= 1 && 
	                        wallX - (bombX+WallW) <= 1  &&
	                        wallY - (bombY) >= 5 &&          
	                        wallY - (bombY+WallH) <= 5){
	                		b.setDestroyed(true);
	                		b.die();		
	                }
	            }
	                
	                if (!b.isDestroyed()) {
	                    b.setY(b.getY() + 1);   
	                    if (b.getY() >= FLOOR - BOMB_SIZE) {
	                        b.setDestroyed(true);
	                    }
	                }
	            }
	        }

	        //thread handling and interrupts. 
	        
	        public void run() {

	            long beforeTime, timeDiff, sleep;

	            beforeTime = System.currentTimeMillis();

	            while (ingame) {
	                repaint();
	                animationCycle();

	                timeDiff = System.currentTimeMillis() - beforeTime;
	                sleep = DELAY - timeDiff;

	                if (sleep < 0) 
	                    sleep = 2;
	                try {
	                    Thread.sleep(sleep);
	                } catch (InterruptedException e) {
	                    System.out.println("interrupted");
	                }
	                beforeTime = System.currentTimeMillis();
	            }
	            gameOver();
	         }

	  class TAdapter extends KeyAdapter {

      public void keyReleased(KeyEvent e) {
          player.keyReleased(e);
      }

      public void keyPressed(KeyEvent e) {

        player.keyPressed(e);
        //canon is placed right now
        //attempting to make it so if missile is half way up screen another shot is possible.
        int x = player.getX();
        int y = player.getY();
   
        if (ingame)
        {
          if (e.isAltDown()) {
        	 
        	  
              if (!missile.isVisible())
                  missile = new Missile(x, y);
          } /*else {
        	  
        	  z = 350;
        	  missile = new Missile(x, y);
          }*/
        }
      }     
   }
}


