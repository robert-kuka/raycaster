import javax.swing.*;

public class Frame extends JFrame{

    private int WIDTH = 1024;
    private int HEIGHT = 512;

    Frame(){
        setTitle("ray casting");                        // Title of the window
        setSize(WIDTH, HEIGHT);                         // Frame dimensions 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit app on close
        setLocationRelativeTo(null);                    // Centere frame
        setVisible(true);                               // Status visible
    }   


}