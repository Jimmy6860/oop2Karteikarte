package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import model.CardsStack;

public class Controller implements Initializable {
    private CardsStack englishStack = new CardsStack();
    private String[] languages= { "Deutsch", "Englisch", "Französich", "Italienisch" };

    @FXML
    private ChoiceBox<String> choiceBoxLanguage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        choiceBoxLanguage.getItems().addAll(languages);
        choiceBoxLanguage.setOnAction(this::getLanguages);
    }

    public void getLanguages(ActionEvent event) {
        String myLanguage = choiceBoxLanguage.getValue();
        System.out.println(myLanguage);
    }

    @FXML
    private void initialize() {

        System.out.println("Initialize");
              // Füge Optionen hinzu und setze eine Standardauswahl
    //   choiceBox.getItems().add("Option 1");
    //   choiceBox.getItems().add("Option 2");
    //   choiceBox.getItems().add("Option 3");
    //   choiceBox.setValue("Option 1");

    //   // Füge einen ChangeListener hinzu
    //   choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    //      System.out.println("Aktuelles Element: " + newValue);
    //   });
   
    }


}
