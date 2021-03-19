package doodlejump;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PaneOrganizer {
    private HBox _bottomPane;
    private BorderPane _root;

    public PaneOrganizer() {
        _root = new BorderPane();
        Pane doodlePane = new Pane();
        _bottomPane = new HBox();
        this.addButton();
        new Game(_root, _bottomPane, doodlePane);

    }

    public void addButton() {
        Button b1 = new Button("Quit");
        b1.setOnAction(new QuitHandler());
        _bottomPane.getChildren().add(b1);
        _bottomPane.setFocusTraversable(false);
        b1.setFocusTraversable(false); // IS THIS RIGHT??
    }

    public BorderPane getRoot() {
        return _root;
    }

    private class QuitHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            System.exit(0);
        }
    }

}