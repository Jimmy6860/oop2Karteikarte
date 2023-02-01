package controller;

import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Card;
import model.CardsStack;

public class Controller implements Initializable {
    private static ObservableList<Card> cardList;
    private List<Card> list = new ArrayList<>();
    private CardsStack englishStack = new CardsStack();
    private String[] languages= {"Englisch", "Französich", "Italienisch" };

    @FXML
    private TextField germanTxtField;
    @FXML
    private TextField foreignTxtField;
    @FXML
    private ChoiceBox<String> choiceBoxLanguage;
    @FXML
    private TableView<Card> cardTable;
    @FXML
    private TableColumn<Card, String> cardColumn;
    @FXML
    private Label systemLabelRight;
    @FXML
    private Label systemLabelLeft;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        cardList = getInitialTableData();

        // Set all card values to the table
        cardTable.setItems(cardList);
        cardColumn.setCellValueFactory(cellData -> cellData.getValue().wordProperty());

        // Default card details value
        showCardDetails(null);

        // Event listener, check which card the user select and set the card details values
        cardTable.getSelectionModel().selectedItemProperty().addListener((observale, oldvalue, newValue) -> showCardDetails(newValue));
        
        choiceBoxLanguage.getItems().addAll(languages);
        choiceBoxLanguage.setOnAction(this::getLanguages);
    }

    // set initialize tasks data
    private ObservableList<Card> getInitialTableData() {

        ObservableList<Card> observableList = FXCollections.observableList(list);

        return observableList;
    }

    public void getLanguages(ActionEvent event) {
        String myLanguage = choiceBoxLanguage.getValue();
        System.out.println(myLanguage);
    }

    public void showCardDetails(Card card) {
        if(card != null) {
            germanTxtField.setText(card.getWord());
            foreignTxtField.setText(card.getForeignWord());
        } else {
            germanTxtField.setText("");
            foreignTxtField.setText("");
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (germanTxtField.getText().isEmpty() || foreignTxtField.getText().isEmpty()) {
            errorMessage += "Nicht alle Felder ausgefüllt!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Felder Valdierung");
            alert.setHeaderText("Bitte korrigieren Sie die ungültigen Felder.");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }
        return false;
    }

    @FXML
    public void addNewCard() {
        Card newCard = new Card();
        if(isInputValid()){
            newCard.setWord(germanTxtField.getText());
            newCard.setForeignWord(foreignTxtField.getText());
            newCard.setLearned(false);
            System.out.println(newCard);
            System.out.println(newCard.getId());
           
            systemLabelRight.setText("Karteikarte gespeichert");
            Timeline timeline = new Timeline(new KeyFrame(
            Duration.millis(3000),
            ae -> systemLabelRight.setText("")));
            timeline.play();
            cardTable.getItems().add(newCard);
        }
    }

    @FXML
    public void deleteCard() {
        int selectedIndex = cardTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Need a confirmation from user to delete the task
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Karteikarte");
            confirmationAlert.setContentText("Löschen?");
            ButtonType okButton = new ButtonType("JA");
            ButtonType noButton = new ButtonType("NEIN");
            confirmationAlert.getButtonTypes().setAll(okButton, noButton);
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.get() == okButton) {
                cardTable.getItems().remove(selectedIndex);
                clearCardDetails();

                systemLabelLeft.setText("Karteikarte gelöscht");
                Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(3000),
                ae -> systemLabelLeft.setText("")));
                timeline.play();
            } else if (result.get() == noButton) {
                confirmationAlert.close();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Karteikarte löschen");
                alert.setHeaderText("Keine Karteikarte ausgewählt");
                alert.setContentText("Bitte wählen Sie eine Karteikarte in der Liste aus.");
                alert.showAndWait();
        }

    }

    @FXML
    public void clearCardDetails() {
        germanTxtField.setText("");
        foreignTxtField.setText("");
    }
}
