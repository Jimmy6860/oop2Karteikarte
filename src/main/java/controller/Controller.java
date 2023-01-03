package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import model.CardsStack;

public class Controller {
    private CardsStack englishStack = new CardsStack();

    @FXML
    private void initialize() {

        System.out.println("Initialize");
    }
}
