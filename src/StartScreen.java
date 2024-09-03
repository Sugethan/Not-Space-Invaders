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
        game.add(text);
        text.setBounds(100, 120, 400, 200);

        JButton button = new JButton("Play");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.start();
            }
        });

        game.add(button);
        button.setBounds(200, 350, 100, 50);
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);

        game.setIgnoreRepaint(false);
        game.revalidate();
        game.repaint();
        game.setIgnoreRepaint(true);
    }
}
