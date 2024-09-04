import javax.swing.JFrame;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;

public class GUI extends JFrame {

    private final int height;
    private final int width;
    private final Line looseline = new Line(); // Initializes ground line
    private int line_height;

    public Ship player = new Ship(); // Initializes ship
    public Score infos = new Score(0L); // Initializes score and trophy icon
    public int countdown = 0; // Countdown for the different games phases

    GUI(int x, int y) {
        
        this.width = x;
        this.height = y;

        Game_window.maker(this, this.width, this.height); // Initializes the game window and its properties
        StartScreen.screen(this); // Draws the starting screen

        this.setVisible(true); 
    
    }

    public void start () { // Starts the game window

        this.getContentPane().removeAll(); // Removes every panels from the screen
        this.infos.score_restart();  // Reinitializes the score
        this.countdown = 0; // Countdown for the different games phases

        Game_window.maker(this, this.width, this.height); // Initializes the game window and its properties
        this.infos.printscore(this, this.width, this.height); // Set the score and trophy icon 
        this.line_height = this.height - 3 * this.infos.getPreferredSize().height - 10; // Height of the ground line
        this.looseline.printline(this, this.line_height); // Set the ground line into the frame
        this.player.printship(this, this.width, this.height); // Set the ship into the frame

        this.setVisible(true); 
        
        this.screen_update();
    }

    public static <T extends Component> ArrayList<T> listComponentsOfType(Container container, Class<T> type) { // Return arraylist of objet of class type in container
        
        ArrayList<T> components = new ArrayList<>();
        
        for (Component component : container.getComponents()) { // Get all components in the container
            
            if (type.isInstance(component)) { // If the component matches the type, add it to the list
                components.add(type.cast(component));
            }

            if (component instanceof Container) { // If the component is a container, looks in it
                components.addAll(listComponentsOfType((Container) component, type));
            }
        }

        return components;
    }

    public void screen_update() {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(6); // Initializes five threads

        Runnable Shooting = () -> {new Bullet().shoot(GUI.this);}; // Taks that shoots bullet from the player's ship

        Runnable invaders_pop = () -> {Wave.WavePop(GUI.this);}; // Task that creates invaders wave

        Runnable invading = () -> {
            
            if (Wave.invading(GUI.this, GUI.this.line_height).get()) { // Move the invaders and check if one reached the ground.
                scheduler.shutdownNow(); // Terminates all tasks in scheduler
                try {
                    while (!scheduler.awaitTermination(1, TimeUnit.SECONDS)) {} // Waits for termination for one second
                } catch (InterruptedException e) {} //Does nothing if the tasks were not terminated properly
                GUI.this.infos.RecordTopScores();
                GameOver.screen(GUI.this, GUI.this.infos); // Game over screen
            };
        };

        Runnable bullet_move = () -> {Bullet.bulleting(GUI.this);}; // Task that moves up the bullets and deletes those out of screen

        Runnable destroy_ship = () -> {Wave.destroyed(GUI.this);}; // Task that deletes invaders and the bullets that killed them

        Runnable screen_refresh = () -> {

            GUI.this.setIgnoreRepaint(false);
            GUI.this.revalidate();
            GUI.this.repaint();
            GUI.this.setIgnoreRepaint(true);
        };

        scheduler.scheduleAtFixedRate(Shooting, 0, 500, TimeUnit.MILLISECONDS); // Shoots bullet
        scheduler.scheduleAtFixedRate(invaders_pop, 0, 6000, TimeUnit.MILLISECONDS); //Makes invaders pop
        scheduler.scheduleAtFixedRate(invading, 0, 40, TimeUnit.MILLISECONDS); // Moves invaders 
        scheduler.scheduleAtFixedRate(bullet_move, 0, 20, TimeUnit.MILLISECONDS); // Moves bullets
        scheduler.scheduleAtFixedRate(destroy_ship, 0, 100, TimeUnit.MILLISECONDS); // Remove destroyed ships and the bullets that caused the destruction
        scheduler.scheduleAtFixedRate(screen_refresh, 0, 1, TimeUnit.MILLISECONDS); // Redraw every panels
    };
}

