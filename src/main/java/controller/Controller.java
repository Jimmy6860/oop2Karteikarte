package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Card;
import model.CardsStack;

public class Controller implements Initializable {
    @FXML
    private TextField germanTxtField;

    @FXML
    private TextField foreignTxtField;
    
    UUID uuid = UUID.randomUUID();
    String uuidAsString = uuid.toString();
    private CardsStack englishStack = new CardsStack();
    private String[] languages= {"Englisch", "Französich", "Italienisch" };

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
   
    }

    @FXML
    public void addNewCard() {
        System.out.println("Click hinzufügen");
        Card newCard = new Card();

        newCard.setWord(germanTxtField.getText());
        
    }
}

// Task newTask = new Task();
// newTask.setTitel(titleTxt.getText());
// newTask.setDescription(descriptionTxt.getText());
// newTask.setDueDate(DateUtil.parse(dueDayTxt.getText()));
// if(this.priorityGroup.getSelectedToggle().equals(this.rbHigh)) {
//     newTask.setPriority("1 Hoch");
// } else if (this.priorityGroup.getSelectedToggle().equals(this.rbMiddle)) {
//     newTask.setPriority("2 Mittel");
// } else {
//     newTask.setPriority("3 Niedrig");
// }
// showTaskDetails(newTask);
// tabPane.getSelectionModel().select(tabOverview);
// systemLabel.setText("Aufgabe gespeichert.");
// Timeline timeline = new Timeline(new KeyFrame(
// Duration.millis(6000),
// ae -> systemLabel.setText("")));
// timeline.play();
// taskTable.getItems().add(newTask);