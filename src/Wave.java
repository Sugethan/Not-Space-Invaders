import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Wave {

    private static final int x_init = 0;
    private static final int y_init = 0;
    private static final int speed_init = 1;
    private static final int invader_width = 40;
    private static final int distance = 50; // Distance between two invaders
    private static final int accel_rate = 1500; // Number of "invading" taks between each game phases
    private static final int invaders_per_wave = 5;
    private static final int invaders_per_vertical_wave = 3;

    public static void WavePop(GUI game) {

        switch (game.countdown/accel_rate > 1 ? 2: game.countdown/accel_rate > 0 ? 1 : 0) {

            case 0:
                for (int n = 0; n < invaders_per_wave; n++) {

                    game.add(new Invader(game, n * distance, y_init, speed_init + game.countdown/accel_rate, true)); // Creates a wave of five invaders top left
                }
                break;
        
            case 1:
                for (int n = 0; n < invaders_per_wave; n++) {

                    game.add(new Invader(game, n * distance, y_init, speed_init + game.countdown/accel_rate, true)); // Creates a wave of five invaders top left
                    game.add(new Invader(game, game.getWidth() - invader_width - n * distance, y_init, - speed_init - game.countdown/accel_rate, true)); // Creates a wave of five invaders top right
                }
                break;

            case 2:
                int randomX = ThreadLocalRandom.current().nextInt(x_init, game.getWidth() - invader_width + 1); // random x position
                for (int n = 0; n < invaders_per_wave; n++) { // Five invaders per wave

                    game.add(new Invader(game, n * distance , y_init, 1 + game.countdown/accel_rate, true)); // Creates a wave of five invaders top left
                    game.add(new Invader(game, game.getWidth() - invader_width - n * distance, y_init, - speed_init - game.countdown/accel_rate, true)); // Creates a wave of five invaders top right
                    if (game.countdown%accel_rate/3 == 0 & n < invaders_per_vertical_wave ) { // Cooldown between pop and three invaders only  
                        game.add(new Invader(game, randomX, n * distance, speed_init, false)); // Creates a vertical wave of three invaders at random x position
                    }
                }
                break;
        }
    }
    
    public static AtomicBoolean invading(GUI game, int line_height) {

        AtomicBoolean bool = new AtomicBoolean(false);
        AtomicBoolean accelerate = new AtomicBoolean(false);
        Iterator<Invader> iterator = GUI.listComponentsOfType(game, Invader.class).iterator();

        game.countdown += 1; 
        
        if (game.countdown%accel_rate == 0 & game.countdown/accel_rate < 3) {
            accelerate.set(true);
        }

        while (iterator.hasNext()) { // For every invader in the wave

            Invader invader = iterator.next();
            invader.accelerate(accelerate);

            if (invader.move(line_height).get()) { // Did a ship reach the ground ?

                bool.set(true);
                break;

            } 
        }

        return bool;
    }
    

    public static void destroyed(GUI game) { 
        
        Iterator<Invader> iterator = GUI.listComponentsOfType(game, Invader.class).iterator(); // Lists  all invaders and for each invader

        while (iterator.hasNext()) {

            Invader invader = iterator.next();

            if (invader.killed(game).get()) {  // If invader killed by a bullet

                    iterator.remove();      // Removes from the list
                    game.remove(invader);   // Removes from the game
                    game.infos.score_update(Long.valueOf(100 + 100 * game.countdown/accel_rate)); // Updates the score
                    
            }
            
        }

    }

}
