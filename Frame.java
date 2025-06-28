import java.awt.Color;

import javax.swing.*;

public class Frame extends JFrame{

    private int WIDTH = 1024;
    private int HEIGHT = 512;

    Frame(){
        setTitle("ray casting");                        // Title of the window
        setSize(WIDTH, HEIGHT);                         // Frame dimensions 
        getContentPane().setBackground(Color.GRAY);     // Background color of the window    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit app on close
        setLocationRelativeTo(null);                    // Centere frame
        setLayout(null);                                // Allows manual positioning

        Player player1 = new Player(300,300);       // Initialise character  
        add(player1);

        setVisible(true);                               // Status visible

    }   


}