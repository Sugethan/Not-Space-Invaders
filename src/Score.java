import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Score extends JLabel{

    private ImageIcon trophy = new ImageIcon("trophy.png");
    private final int height = 20;
    private final int width = 20;
    private final int max_index = 10;
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

    public ArrayList<Long> getTopScores() {

        ArrayList<Long> TopScores =  new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Record.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                TopScores.add(Long.parseLong(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TopScores;

    }

    public void RecordTopScores() {

        ArrayList<Long> TopScores = this.getTopScores();

        TopScores.add(this.current_score);
        Collections.sort(TopScores);
        Collections.reverse(TopScores);
        TopScores.remove(this.max_index);

        try (FileWriter writer = new FileWriter("Record.txt")) {
            for (Long score : TopScores) {
                writer.write(score + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
