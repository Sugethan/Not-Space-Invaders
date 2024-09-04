import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen{
    

    public static void screen(GUI game) {

        JTextArea text = new JTextArea("Not Space Invaders", 200, 200);
        text.setEditable(false); // Make it non-editable
        text.setLineWrap(true); // Enable line wrapping
        text.setWrapStyleWord(true); // Wrap by word
        text.setForeground(Color.RED); // Text color
        text.setBackground(Color.BLACK);
        text.setFont(new Font("MONOSPACED", Font.CENTER_BASELINE, 50));
        game.getContentPane().removeAll(); // Removes all components from the frame
        game.add(text);
        text.setBounds(120, 120, 400, 150);

        JButton play = new JButton("Play");
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.start();
            }
        });

        game.add(play);
        play.setBounds(200, 275, 100, 50);
        play.setBackground(Color.RED);
        play.setForeground(Color.WHITE);

        JButton Scores = new JButton("Scores");
        Scores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RecordScreen.screen(game);
            }
        });

        game.add(Scores);
        Scores.setBounds(200, 350, 100, 50);
        Scores.setBackground(Color.RED);
        Scores.setForeground(Color.WHITE);

        game.setIgnoreRepaint(false);
        game.revalidate();
        game.repaint();
        game.setIgnoreRepaint(true);
    }
}
