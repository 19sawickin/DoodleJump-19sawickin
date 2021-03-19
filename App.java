package doodlejump;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class in charge of starting the game.
 * The main method of this application calls launch, a JavaFX method
 * which eventually calls the start method below.
 */
public class App extends Application {

    /**
     * The start method is responsible for constructing the stage and the PaneOrganizer
     * that contains all of the panes. This starts the game.
     */
    @Override
    public void start(Stage stage) {
        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(),Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Doodlejump!");
        stage.show();
    }

    public static void main(String[] argv) {
        launch(argv);
    }
}
