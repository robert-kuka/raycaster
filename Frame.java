import java.awt.Color;
import java.awt.Insets;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

public class Frame extends JFrame implements KeyListener{

    private int WIDTH = 1024-40;
    private int HEIGHT = 512;
    private Player player;
    private Set<Integer> keys = new HashSet<>();

    Frame(){
        setTitle("ray casting");                        // Title of the window                                 
        setVisible(true);                               // Window visible
        
        pack();
        Insets insets = getInsets();
        setSize(WIDTH + insets.left + insets.right,
                HEIGHT + insets.top + insets.bottom);
        
        getContentPane().setBackground(Color.BLUE);     // Background color of the window    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit app on close
        setLocationRelativeTo(null);                    // Centere frame
        setLayout(null);                                // Allows manual positioning

        player = new Player();                          
        player.setBounds(0, 0, WIDTH + insets.left + insets.right
                             , HEIGHT+ insets.top + insets.bottom);
        add(player);

        Map map = new Map();
        map.setBounds(0, 0, WIDTH + insets.left + insets.right
                          , HEIGHT+ insets.top + insets.bottom);
        add(map);

        addKeyListener(this);                           // Add keylistener to the frame
        setFocusable(true);                             // Makes frame interactable


        new Timer(16, e -> updateMovement()).start();
    }   

    private void updateMovement() {
        if (keys.contains(KeyEvent.VK_W)) player.moveForward();
        if (keys.contains(KeyEvent.VK_S)) player.moveBackward();
        if (keys.contains(KeyEvent.VK_A)) player.turnLeft();
        if (keys.contains(KeyEvent.VK_D)) player.turnRight();

        player.repaint();
    }

    @Override public void keyPressed(KeyEvent e) { keys.add(e.getKeyCode()); }
    @Override public void keyReleased(KeyEvent e) { keys.remove(e.getKeyCode()); }
    @Override public void keyTyped(KeyEvent e) {}
}