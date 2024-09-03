import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver{
    

    public static void screen(GUI game, Score score) {

        JTextArea text = new JTextArea("Not even good enough to be a Force hound !", 200, 200);
        text.setEditable(false); // Make it non-editable
        text.setLineWrap(true); // Enable line wrapping
        text.setWrapStyleWord(true); // Wrap by word
        text.setForeground(Color.RED); // Text color
        text.setBackground(Color.BLACK);
        text.setFont(new Font("MONOSPACED", Font.CENTER_BASELINE, 50)); 
        game.add(text);
        text.setBounds(40, 120, 500, 200);

        JButton button = new JButton("Replay");       
        button.addActionListener(new ActionListener() {  // Waits for the button to be pressed
            public void actionPerformed(ActionEvent e) {  // Defines the action to perform
                game.start(); // Starts the game
            }
        });

        game.add(button);
        button.setBounds(200, 350, 100, 50);
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);

        game.add(score);

        game.setIgnoreRepaint(false); // Allows to repaint the Jcomponents
        game.revalidate(); // Revalidates all Jcomponents in order of hierarchy
        game.repaint(); // Repaints all Jcomponents
        game.setIgnoreRepaint(true); // Disallows to repaint the Jcomponents
    }
}
