package ConsoleVersion;

import ConsoleVersion.Controller.Controller;
import Model.GameFacade;

/**
 * The App class serves as the entry point for the console version of the game.
 * It initializes the game's main components and starts the game loop.
 */
public class App {
        /**
         * The main method that serves as the entry point of the application.
         * It creates the necessary objects for the game and starts the game loop.
         *
         * @param args Command-line arguments (not used in this application).
         */
        public static void main(String[] args) {
            // Create a GameFacade instance which acts as the main interface to the game model.
            GameFacade gameFacade = new GameFacade();
            // Create a Controller instance which manages the interaction between the user and the game model.
            Controller controller = new Controller(gameFacade);
            // Start the game by initializing necessary components and entering the main game loop.
            controller.startGame();
            controller.run();
        }
}
