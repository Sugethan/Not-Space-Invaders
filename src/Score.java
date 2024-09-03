import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Score extends JLabel{

    private ImageIcon trophy = new ImageIcon("trophy.png");
    private final int height = 20;
    private final int width = 20;
    private long current_score;

    Score(long score) {

        this.current_score = score;
        this.setText(String.valueOf(this.current_score)); // Tranform into into string
        this.setForeground(Color.WHITE); // Color of the text
        
        Image image = this.trophy.getImage(); // Load image
        image = image.getScaledInstance(this.width, this.height, java.awt.Image.SCALE_SMOOTH); // Scale image
        this.trophy = new ImageIcon(image); // Change the property
        this.setIcon(this.trophy); // Choose the image as an icon.
        this.setHorizontalTextPosition(JLabel.RIGHT); // Position of the text relative to the icon
        this.setVerticalTextPosition(JLabel.BOTTOM); // Position of the text relative to the icon

    }

    public void printscore(GUI game, int width, int height) { // Width and height of the game window
        game.add(this); // Adds the label to the frame
        this.setBounds(10, height - 3 * this.height, width - 10, this.height); // Set label position and displayed content's size.
    }

    public void score_update(long new_score) {
        this.current_score += new_score;
        this.setText(String.valueOf(this.current_score)); 
    }

    public void score_restart() {
        this.current_score = 0L;
        this.setText(String.valueOf(this.current_score)); 
    }

}
