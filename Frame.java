import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

public class Frame extends JFrame implements KeyListener{

    private int WIDTH = 1024;
    private int HEIGHT = 512;
    private Player player1;

    Frame(){
        setTitle("ray casting");                        // Title of the window
        setSize(WIDTH, HEIGHT);                         // Frame dimensions 
        getContentPane().setBackground(Color.GRAY);     // Background color of the window    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit app on close
        setLocationRelativeTo(null);                    // Centere frame
        setLayout(null);                                // Allows manual positioning

        player1 = new Player();                         // Initialise character  
        add(player1);

        addKeyListener(this);                           // Add keylistener to the frame
        setFocusable(true);                             // Makes frame interactable

        setVisible(true);                               // Status visible
    }   

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int step = 5;

        if (key == KeyEvent.VK_A) {
            player1.px -= step;
        } else if (key == KeyEvent.VK_D) {
            player1.px += step;
        } else if (key == KeyEvent.VK_W) {
            player1.py -= step;
        } else if (key == KeyEvent.VK_S) {
            player1.py += step;
        }
        
        player1.setLocation(player1.px, player1.py);
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

}