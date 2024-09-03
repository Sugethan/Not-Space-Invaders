import java.awt.Color;
import javax.swing.JFrame;

public class Game_window {
    
    public static void maker(GUI game, int x, int y){

        game.setTitle("Not Space Invaders"); // Set window's title
        game.setSize(x, y); // Set window's size
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set window's exit button behavior
        game.getContentPane().setBackground(Color.BLACK); // Set window's background color
        game.setLayout(null); // No layout manager
        game.setResizable(false); // Window cannot be resized
        game.setIgnoreRepaint(true); // Disable automatic repaint when panels are modified

    }

}
