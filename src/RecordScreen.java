import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RecordScreen{

    public static JTextArea Boxes(long score, int n) {

        JTextArea text = new JTextArea(String.valueOf(n) + ". " + String.valueOf(score), 50, 50);
        text.setEditable(false); // Make it non-editable
        text.setLineWrap(true); // Enable line wrapping
        text.setWrapStyleWord(true); // Wrap by word
        text.setForeground(Color.RED); // Text color
        text.setBackground(Color.BLACK);
        text.setFont(new Font("MONOSPACED", Font.CENTER_BASELINE, 20)); 
        
        return text;
        
    }
    
    public static void screen(GUI game) {

        ArrayList <JTextArea> classement = new ArrayList<>();
        ArrayList<Long> TopScores = game.infos.getTopScores();

        game.getContentPane().removeAll(); // Removes all components from the frame

        for (int index = 0; index < TopScores.size(); index++) {

            classement.add(RecordScreen.Boxes(TopScores.get(index), index));
            game.add(classement.get(index));
            classement.get(index).setBounds(200, 120 + index * 30, 350, 25);

        }

        JButton button = new JButton("Return");       
        button.addActionListener(new ActionListener() {  // Waits for the button to be pressed
            public void actionPerformed(ActionEvent e) {  // Defines the action to perform
                StartScreen.screen(game); // Return to the starting screen
            }
        });

        game.add(button);
        button.setBounds(10, 400, 100, 50);
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);

        JTextArea text = new JTextArea("Top Scores", 200, 200);
        game.add(text);
        text.setEditable(false); // Make it non-editable
        text.setLineWrap(true); // Enable line wrapping
        text.setWrapStyleWord(true); // Wrap by word
        text.setForeground(Color.RED); // Text color
        text.setBackground(Color.BLACK);
        text.setFont(new Font("MONOSPACED", Font.CENTER_BASELINE, 50)); 
        text.setBounds(80, 10, 500, 200);

        game.setIgnoreRepaint(false); // Allows to repaint the Jcomponents
        game.revalidate(); // Revalidates all Jcomponents in order of hierarchy
        game.repaint(); // Repaints all Jcomponents
        game.setIgnoreRepaint(true); // Disallows to repaint the Jcomponents
    }
}
