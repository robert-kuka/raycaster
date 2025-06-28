import java.awt.*;
import javax.swing.*;

public class Player extends JPanel{

    public int LENGTH = 8;

    public double dir;
    public double fov;

    Player(int x, int y){
        setSize(LENGTH,LENGTH);
        setBackground(Color.YELLOW);
        setLocation(x, y);
        setVisible(true);
        
    }

    public void move(){

    }

}