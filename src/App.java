public class App {

    public static void main(String[] args) throws Exception {

        GUI game = new GUI(500,500); // Initalizes the game window (width, height)

        key_bindings key_binder = new key_bindings(); // Initializes the objects that will bind the keys
        key_binder.make_bindings(game); // Binds the key for movement and shooting

    }
        

}
