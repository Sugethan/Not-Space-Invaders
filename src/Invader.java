import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Invader extends JPanel{

    private final Image invader;
    private final int width = 40;
    private final int height = 40;
    private int speed;
    private final int screen_width = 500;
    private boolean normal;
    
    private int x_pos;
    private int y_pos;

    Invader(GUI game, int initial_x, int initial_y, int velocity, boolean bool) {  // (Game, initial x position, initial y position, speed, horizontal wave ?)

        this.setPreferredSize(new Dimension(this.width, this.height)); // Set panel size
        this.invader = new ImageIcon("Rakatan.png").getImage(); // Load image
        this.setOpaque(false); // Set non-opaque background for the panel
        this.x_pos = initial_x; // Initial position
        this.y_pos = initial_y; 
        this.setLocation(this.x_pos, this.y_pos);
        this.speed += velocity;
        this.normal = bool;
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(this.invader, 0, 0, this.width, this.height, this); // Draws image from top left corner of the panel. 
    }

    public void accelerate(AtomicBoolean bool) { // Accelerates the invaders

        if (bool.get()) {           
            if (this.speed < 0) { // If speed negative, decreases
                this.speed -= 1;
            } else {              // If speed positivee, increases
                this.speed += 1;
            }
        }

    }

    public AtomicBoolean killed(GUI game) { // Verify if the invader is destroyed and remove the bullet who killed it from the game

        AtomicBoolean bool = new AtomicBoolean(false);

        GUI.listComponentsOfType(game, Bullet.class).forEach(bullet -> {    // Finds all bullets and for each bullet
          
            if (bullet.getBounds().intersects(this.getBounds())) {             // Verifies if the invader intersect with a bullet
                
                game.remove(bullet);   // Removes the bullet
                bool.set(true); // Returns that the invader has been killed
            }     
        });

        return bool;
    }

    public AtomicBoolean move(int line_height) { // Move the ships

        AtomicBoolean bool = new AtomicBoolean(false);

        if (this.normal) { 
            switch (this.x_pos + this.speed > this.screen_width - this.width ? 1 : this.x_pos + this.speed < 0 ? 1 : 0) {

                case 1:   // If the invader has reached the border, lower it
                    this.y_pos += 50;
                    this.setBounds(this.x_pos, this.y_pos, this.width, this.height);
                    if (this.y_pos >= line_height - this.height) {  // If the invader touches the ground
                        bool.set(true);
                    } 
                    this.speed = -this.speed;// Changes movement direction
                    break;
                    
                case 0: 
                    this.x_pos += this.speed; // Moves the invader horizontally
                    this.setBounds(this.x_pos, this.y_pos, this.width, this.height);
                    break;
            }
        } else {

            this.y_pos += Math.abs(this.speed);
            this.setBounds(this.x_pos, this.y_pos, this.width, this.height);
            if (this.y_pos >= line_height - this.height) {  // If the invader touches the ground
                bool.set(true); // Returns true and stops the method since it's game over 
            } 
        }

        return bool;
    }

}