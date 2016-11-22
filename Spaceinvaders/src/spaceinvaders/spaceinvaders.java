
package spaceinvaders;

import javax.swing.JFrame;

public class spaceinvaders extends JFrame implements Attributes {

    public spaceinvaders()
    {
        add(new Spaceboard());
        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_W, BOARD_H);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new spaceinvaders();
    }
}

