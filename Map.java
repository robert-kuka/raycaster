import java.awt.*;
import javax.swing.JPanel;

public class Map extends JPanel {
    private int mapX = 8;
    private int mapY = 8;
    private int mapS = 64;
    
    // Map layout
    public int map[] = {
        1,1,1,1,1,1,1,1,
        1,0,1,0,0,0,0,1,
        1,0,1,0,0,0,0,1,
        1,0,1,0,0,0,0,1,
        1,0,0,0,0,0,0,1,
        1,0,0,0,0,1,0,1,
        1,0,0,0,0,0,0,1,
        1,1,1,1,1,1,1,1,
    };
    
    public Map() {
    }
    
    // Draw grid
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for(int y = 0; y < mapY; y++) {
            for(int x = 0; x < mapX; x++) {
                Color color;
                if (map[y * mapX + x] == 1) {
                    color = Color.BLACK;
                } else {
                    color = Color.WHITE;
                }
                int xo = x * mapS;
                int yo = y * mapS;
                drawBox(g, xo, yo, color);
            }
        }
    }
    
    public void drawBox(Graphics g, int xo, int yo, Color color) {
        g.setColor(color);
        g.fillRect(xo, yo, mapS, mapS);
        g.setColor(Color.GRAY);
        g.drawRect(xo, yo, mapS, mapS);
    }
}