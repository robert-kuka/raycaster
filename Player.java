import java.awt.*;
import javax.swing.*;

public class Player extends JPanel {

    public double px = 300;
    public double py = 300;
    public int pa = 180;

    public final int LENGTH = 8;
    private final double MOVE_SPEED = 1.5;
    private final int TURN_SPEED = 2;

    Map gameMap = new Map();

    public Player() {
        setOpaque(false);
        setFocusable(true);
    }

    public void moveForward() {
        double rad = Math.toRadians(pa);
        px += Math.cos(rad) * MOVE_SPEED;
        py += Math.sin(rad) * MOVE_SPEED;
    }

    public void moveBackward() {
        double rad = Math.toRadians(pa);
        px -= Math.cos(rad) * MOVE_SPEED;
        py -= Math.sin(rad) * MOVE_SPEED;
    }

    public void turnLeft() {
        pa = (pa - TURN_SPEED + 360) % 360;
    }

    public void turnRight() {
        pa = (pa + TURN_SPEED + 360) % 360;
    }

    public double dist(double ax, double ay, double bx, double by){
        return Math.sqrt((bx-ax)*(bx-ax)+(by-ay)*(by-ay));
    }

public void drawRays(Graphics g, Map gameMap, double centerX, double centerY){
    int r, mx, my, mp, dof;
    double ra, disT, rx = 0, ry = 0, xo = 0, yo = 0;
    int mapX = 8;
    int mapY = 8;
    int[] map = gameMap.map;
    
    int numRays = 120; 
    int screenWidth = 1024; 
    int screenHeight = 512;

    ra = pa - 30; if (ra < 0) ra += 360; if (ra >= 360) ra -= 360;

    for(r = 0; r < 60; r++) {
        
        //---- Check Horizontal Lines ----
        double disH = 100000, hx = centerX, hy = centerY;
        dof = 0;
        double aTan = -1 / Math.tan(Math.toRadians(ra));
        
        if(ra > 180) { // Looking up
            ry = (((int)py >> 6) << 6) - 0.0001;
            rx = (py - ry) * aTan + px;
            yo = -64;
            xo = -yo * aTan;
        }
        else if(ra < 180) { // Looking down
            ry = (((int)py >> 6) << 6) + 64;
            rx = (py - ry) * aTan + px;
            yo = 64;
            xo = -yo * aTan;
        }
        else { // ra == 0 || ra == 180 - Looking straight left or right
            rx = centerX;
            ry = centerY;
            dof = 8;
            xo = 0;
            yo = 0;
        }
        
        while(dof < 8) {
            mx = (int)(rx) >> 6; 
            my = (int)(ry) >> 6; 
            mp = my * mapX + mx;
            
            if(mp >= 0 && mp < mapX * mapY && mx >= 0 && mx < mapX && my >= 0 && my < mapY) {
                if(map[mp] == 1) { 
                    dof = 8; 
                    hx = rx; 
                    hy = ry;
                    disH = dist(centerX, centerY, hx, hy);
                }
                else { 
                    rx += xo; 
                    ry += yo; 
                    dof += 1; 
                }
            }
            else { 
                dof = 8; 
            }
        }
        
        //---- Check Vertical Lines ----
        double disV = 100000, vx = centerX, vy = centerY;
        dof = 0;
        double nTan = -Math.tan(Math.toRadians(ra));
        
        if(ra > 90 && ra < 270) { // Looking left
            rx = (((int)px >> 6) << 6) - 0.0001;
            ry = (px - rx) * nTan + py;
            xo = -64;
            yo = -xo * nTan;
        }
        else if(ra < 90 || ra > 270) { // Looking right 
            rx = (((int)px >> 6) << 6) + 64;
            ry = (px - rx) * nTan + py;
            xo = 64;
            yo = -xo * nTan;
        }
        else { // ra == 270 || ra == 90 - Looking straight up or down
            rx = centerX;
            ry = centerY;
            dof = 8;
            xo = 0;
            yo = 0;
        }
        
        while(dof < 8) {
            mx = (int)(rx) >> 6; 
            my = (int)(ry) >> 6; 
            mp = my * mapX + mx;
            
            if(mp >= 0 && mp < mapX * mapY && mx >= 0 && mx < mapX && my >= 0 && my < mapY) {
                if(map[mp] == 1) { 
                    dof = 8; 
                    vx = rx; 
                    vy = ry;
                    disV = dist(centerX, centerY, vx, vy);
                }
                else { 
                    rx += xo; 
                    ry += yo; 
                    dof += 1; 
                }
            }
            else { 
                dof = 8; 
            }
        }
        
        // Choose the closest wall hit
        if (disV < disH) {
            rx = vx; 
            ry = vy;
            disT = disV;
            g.setColor(new Color(200,0,0)); // Vertical color
        }
        else {
            rx = hx; 
            ry = hy;
            disT = disH;
            g.setColor(new Color(255,0,0)); // Horizontal color
        }
        
        // Draw the ray
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine((int)centerX, (int)centerY, (int)rx, (int)ry);

        // Draw the walls
        double ca = (pa - ra);
        if (ca < 0) ca += 360;
        if (ca > 360) ca -= 360;
        disT = disT * Math.cos(Math.toRadians(ca));

        double lineH = (64.0 * 320) / disT; 
        if (lineH > 320) lineH = 320;

        double lineO = (160 - lineH / 2.0);

        int columnWidth = screenWidth / numRays;
        double columnX = screenHeight + r * columnWidth;


        // Draw the vertical slice
        g.fillRect((int)columnX, (int)lineO, columnWidth, (int)lineH);
        


        ra+=1; if (ra < 0) ra += 360; if (ra >= 360) ra -= 360;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        int drawX = (int) px;
        int drawY = (int) py;

        // Draw player body
        g2.setColor(Color.ORANGE);
        g2.fillRect(drawX, drawY, LENGTH, LENGTH);

        // Center of player square
        double centerX = px + LENGTH / 2.0;
        double centerY = py + LENGTH / 2.0;

        drawRays(g2, gameMap, centerX, centerY);

        g2.dispose();
    }
}
