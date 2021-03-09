package doodlejump;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PaneOrganizer {
    private BorderPane _root;
    private HBox _bottomPane;

    public PaneOrganizer() {
        _root = new BorderPane();
        _bottomPane = new HBox();
        Pane doodlePane = new Pane();
        new Doodle(doodlePane);
        _root.setCenter(doodlePane);
        this.addButton();
        _root.setBottom(_bottomPane);

    }

    public Pane getRoot() {
        return _root;
    }

    public void addButton() {
        Button b1 = new Button("Quit");
        b1.setOnAction(new QuitHandler());
        _bottomPane.getChildren().add(b1);
    }

    private class QuitHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            System.exit(0);
        }
    }
}
