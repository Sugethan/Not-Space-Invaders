import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Bullet extends JPanel{


    private final int width = 6;
    private final int height = 10;
    private final int speed = 2;

    private int x_pos;
    private int y_pos;

    Bullet() {

        this.setPreferredSize(new Dimension(this.width, this.height)); // Set panel size
        this.setOpaque(false); // Non opaque panel background
    }

    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g; // Upgrade to Graphics2D
        super.paintComponent(g); // Render empty graph
        g2d.setPaint(Color.WHITE); // Choose color
        g2d.setStroke(new BasicStroke(this.width)); // Choose bullet width
        g2d.drawLine(this.width/2, 0, this.width/2, this.height); // Draw line from the top left corner of the panel to top right corner of the panel
    }

    public void shoot(GUI game) {


        game.add(this); // Adds bullet panel to the frame
        this.x_pos = game.player.gun_position; // Initial position is the position of the gun
        this.y_pos = game.player.y_pos - this.height;
        this.setBounds(this.x_pos, this.y_pos, this.width, this.height); // Position and displayed content's size
            
    }

    public void flying_through_the_sky(GUI game) { // Move the bullets

        this.y_pos = this.y_pos - this.speed; // Update the position of the bullet
        this.setBounds(this.x_pos, this.y_pos, this.width, this.height); // Moves up the bullet

        if (this.y_pos<=0) { // If the bullet is outside of the screen
            
            game.remove(this); // Removes the bullet
            
        }
    }

    public static void bulleting(GUI game) { // Method to move all the bullets in game

        ArrayList<Bullet> bullets = GUI.listComponentsOfType(game, Bullet.class); // Lists all bullets on screen

        bullets.forEach(bullet -> bullet.flying_through_the_sky(game)); // Moves them up or remove them after they reach the top of the screen
    }
}
    

