import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

public class key_bindings {

    public void make_bindings(GUI game) {

        KeyStroke leftArrow = KeyStroke.getKeyStroke("LEFT"); // Event "left arrow is pressed"
        KeyStroke RightArrow = KeyStroke.getKeyStroke("RIGHT"); // Event "right arrow is pressed"

        AbstractAction LeftMove = new AbstractAction() {  // Action "move to the left"
            
            public void actionPerformed(ActionEvent e) {game.player.move(game,-1);}
        };
        
        AbstractAction RightMove = new AbstractAction() { // Action "move to the right"
            
            public void actionPerformed(ActionEvent e) {game.player.move(game,1);}   
        };


        JRootPane rootPane = game.getRootPane(); // Access root panel (tied to the frame)
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(leftArrow, "LeftMove"); // Event to look for when the game windows is focused and naming it.
        rootPane.getActionMap().put("LeftMove", LeftMove);  // Map the previous event to the action to make
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(RightArrow, "RightMove");
        rootPane.getActionMap().put("RightMove", RightMove);
    }
}
