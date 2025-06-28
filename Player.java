import java.awt.*;
import javax.swing.*;

public class Player extends JPanel {

    public double px = 300;
    public double py = 300;
    public int pa = 90;

    public final int LENGTH = 8;
    private final double MOVE_SPEED = 1.5;
    private final int TURN_SPEED = 3;

    public Player() {
        setSize(LENGTH,LENGTH);
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
        pa = (pa - TURN_SPEED) % 360;
    }

    public void turnRight() {
        pa = (pa + TURN_SPEED + 360) % 360;
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

        // Barrel params
        int barrelLength = 20;
        int barrelWidth = 4;

        // Center of player square
        double centerX = px + LENGTH / 2.0;
        double centerY = py + LENGTH / 2.0;

        double rad = Math.toRadians(pa);

        double dx = Math.cos(rad);
        double dy = Math.sin(rad);

        double px_perp = -dy;
        double py_perp = dx;

        // Barrel polygon points
        int[] xPoints = new int[4];
        int[] yPoints = new int[4];

        xPoints[0] = (int) (centerX + px_perp * barrelWidth / 2);
        yPoints[0] = (int) (centerY + py_perp * barrelWidth / 2);

        xPoints[1] = (int) (centerX - px_perp * barrelWidth / 2);
        yPoints[1] = (int) (centerY - py_perp * barrelWidth / 2);

        xPoints[2] = (int) (xPoints[1] + dx * barrelLength);
        yPoints[2] = (int) (yPoints[1] + dy * barrelLength);

        xPoints[3] = (int) (xPoints[0] + dx * barrelLength);
        yPoints[3] = (int) (yPoints[0] + dy * barrelLength);

        int rayLength = 100;  // example length

        int rayX = (int) (centerX + dx * rayLength);
        int rayY = (int) (centerY + dy * rayLength);

        g2.setColor(Color.RED);
        g2.drawLine((int)centerX, (int)centerY, rayX, rayY);

        g2.setColor(Color.ORANGE);
        g2.fillPolygon(xPoints, yPoints, 4);

        g2.dispose();
    }


}
