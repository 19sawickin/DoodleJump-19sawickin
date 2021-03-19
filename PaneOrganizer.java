package doodlejump;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * The PaneOrganizer class contains all of the panes in the game, which includes
 * the root pane, the doodlePane, and the bottomPane.
 */
public class PaneOrganizer {
    private HBox _bottomPane;
    private BorderPane _root;

    /**
     * The PaneOrganizer constructor constructs all of the panes and also
     * constructs the Game class and passes these panes through as arguments.
     */
    public PaneOrganizer() {
        _root = new BorderPane();
        Pane doodlePane = new Pane();
        _bottomPane = new HBox();
        this.addButton();
        new Game(_root, _bottomPane, doodlePane);

    }

    /**
     * This method constructs a quit button and adds it to the
     * bottom pane which exits the game if pressed.
     */
    public void addButton() {
        Button b1 = new Button("Quit");
        b1.setOnAction(new QuitHandler());
        _bottomPane.getChildren().add(b1);
        _bottomPane.setFocusTraversable(false);
        b1.setFocusTraversable(false); // IS THIS RIGHT??
    }

    /**
     * This method returns the root pane
     */
    public BorderPane getRoot() {
        return _root;
    }

    /**
     * This private class handles the action when the quit button is
     * pressed.
     */
    private class QuitHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            System.exit(0);
        }
    }

}