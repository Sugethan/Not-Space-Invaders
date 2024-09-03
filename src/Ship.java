import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Ship extends JPanel{

    private final Image ship;
    private final int width = 40;
    private final int height = 40;
    private int x_pos; // Position of the panel
    public int y_pos;
    public int gun_position;
    private final int speed = 10;

    Ship() {

        this.setPreferredSize(new Dimension(this.width, this.height)); // Set panel size
        this.ship = new ImageIcon("ship.png").getImage(); // Load image
        this.setOpaque(false); // Set non-opaque background for the panel
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(this.ship, 0, 0, this.width, this.height, this); // Draw image from top left corner of the panel. 
    }

    public void printship(GUI game, int width, int height) { // (Width, height of the game window)

        game.add(this);
        this.x_pos = width/2 - this.width/2; // Initial position
        this.y_pos = height - this.height - 80;
        this.gun_position = this.x_pos + this.width/2 -3 ;
        this.setBounds(this.x_pos, this.y_pos, this.width, this.height);

    }

    public void move(GUI game, int LeftorRight) { // Move to the left if int is negative and to the right if the int is positive 

        switch (this.x_pos + LeftorRight * this.speed < 0 ? 1 : this.x_pos + LeftorRight * this.speed > game.getWidth() - this.width ? 1 : 0 ) {
            case 1:
                break;
        
            case 0:
                this.x_pos = this.x_pos + LeftorRight * this.speed; // Update position
                this.gun_position = this.x_pos + this.getPreferredSize().width/2 -3 ; // Update gun position
                this.setBounds(this.x_pos, this.y_pos, this.width, this.height);
                break;
        }
    }
}
