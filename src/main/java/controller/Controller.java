package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    @FXML
    public void addNewCard() {
        Card newCard = new Card();
        newCard.setWord(germanTxtField.getText());
        newCard.setForeignWord(foreignTxtField.getText());
        newCard.setLearned(false);
        System.out.println(newCard);
        System.out.println(newCard.getId());
       
        cardTable.getItems().add(newCard);
    }

    @FXML
    public void deleteCard() {
        int selectedIndex = cardTable.getSelectionModel().getSelectedIndex();
        cardTable.getItems().remove(selectedIndex);
        clearCardDetails();

    }

    @FXML
    public void clearCardDetails() {
        germanTxtField.setText("");
        foreignTxtField.setText("");
    }
}
