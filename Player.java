import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Player extends JPanel{

    public int LENGTH = 8;


    public int px = 300;
    public int py = 300;
    public double dir;
    public double fov;

    Player(){
        setSize(LENGTH,LENGTH);
        setBackground(Color.YELLOW);
        setLocation(px, py);
        setVisible(true);
    }

}