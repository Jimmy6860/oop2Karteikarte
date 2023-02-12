package controller;

import javafx.util.Duration;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import com.fasterxml.jackson.databind.ObjectMapper;

import Service.FileAccess;
import enumeration.Language;
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
import javafx.scene.text.Text;
import model.Card;
import model.CardsStack;

public class Controller implements Initializable {
    @FXML
    private TextField germanTxtField;
    @FXML
    private TextField foreignTxtField;
    @FXML
    private TextField lnGermanTxt;
    @FXML
    private TextField lnForeignTxt;
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
    private Label systemLabelLearn;
    @FXML
    private Text learnSectionTitle;


    private static ObservableList<Card> cardList;
    private List<Card> englishlist = new ArrayList<>();
    private List<Card> frenchlist = new ArrayList<>();
    private List<Card> italianlist = new ArrayList<>();
    private List<Card> spanishList = new ArrayList<>();
    private CardsStack englishStack = new CardsStack("1", "EnglishStack", englishlist, Language.Englisch);
    private CardsStack frenchStack = new CardsStack("2", "FrenchStack", frenchlist, Language.Französisch);
    private CardsStack italianStack = new CardsStack("3", "ItalianStack", italianlist, Language.Italienisch);
    private CardsStack spanishStack = new CardsStack("4", "SpnishStack", spanishList, Language.Spanisch);
    private Language[] languages = Language.values();
    private List<Card> currentList = englishStack.getCards();
    private Integer currentIndex = 0;
    String currentLanguage;
    

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        loadFile();
        setTableData();

        // Default card details value
        showCardDetails(null);

        // Event listener, check which card the user select and set the card details values
        cardTable.getSelectionModel().selectedItemProperty().addListener((observale, oldvalue, newValue) -> showCardDetails(newValue));
        
        for(Language language: languages) {
            choiceBoxLanguage.getItems().add(language.name());
        }
       
        choiceBoxLanguage.setOnAction(this::getLanguages);
        learnSectionTitle.setText("Englisch");
        currentLanguage = choiceBoxLanguage.getValue();
    }

    // set initialize tasks data
    private ObservableList<Card> getInitialTableData() {

        ObservableList<Card> observableList = FXCollections.observableList(currentList);

        return observableList;
    }

    private void setTableData() {
        cardList = getInitialTableData();
        //Sort list alphabetically
        sortAlphaFunction(cardList);
        // Set all card values to the table
        cardTable.setItems(cardList);
        cardColumn.setCellValueFactory(cellData -> cellData.getValue().wordProperty());
    }

    private void sortAlphaFunction(List<Card> cards) {
        Collections.sort(cards, new Comparator<Card>() {
            public int compare(Card v1, Card v2) {
                return v1.getWord().compareTo(v2.getWord());
            }
        });
    }

    public void getLanguages(ActionEvent event) {
        currentLanguage = choiceBoxLanguage.getValue();

        switch (currentLanguage) {
            case "Englisch":
                currentList = englishStack.getCards();
                break;
            case "Französisch":
                currentList = frenchStack.getCards();
                break;
            case "Italienisch":
                currentList = italianStack.getCards();
                break;
            case "Spanisch":
                currentList = spanishStack.getCards();
                break;
            default:
                currentList = englishStack.getCards();
                break;
        }
        learnSectionTitle.setText(currentLanguage);
        setTableData();
        resetLearningSession();

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

    public void resetCardTextField() {
        germanTxtField.setText("");
        foreignTxtField.setText("");
    }

    public void setSystemLabelRight(String txt) {
        systemLabelRight.setText(txt);
        Timeline timeline = new Timeline(new KeyFrame(
        Duration.millis(3000),
        ae -> systemLabelRight.setText("")));
        timeline.play();
    }

    @FXML
    public void addNewCard() {
        if(isInputValid()){
            Card selectedTask = cardTable.getSelectionModel().getSelectedItem();
            if(selectedTask != null) {
                selectedTask.setWord(germanTxtField.getText());
                selectedTask.setForeignWord(foreignTxtField.getText());
                setSystemLabelRight("Karteikarte angepasst");
            } else {
                Card newCard = new Card("", germanTxtField.getText(), foreignTxtField.getText(), false, currentLanguage);
                System.out.println(newCard);
                System.out.println(Language.valueOf(currentLanguage));
                setSystemLabelRight("Karteikarte gespeichert");
                cardTable.getItems().add(newCard);
                resetCardTextField();
            }
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

    /*******************
    * Learning Section *
    ********************/

    public Card getCurrentCard() {
        Card currentCard = currentList.get(currentIndex);
        return currentCard;
    }

    public void startLearning() {
        
        lnGermanTxt.setText(getCurrentCard().getWord());
        lnForeignTxt.setText("");
        for (Card card : cardList) {
            System.out.println(card.toString());
        }
    }

    public void showForeignWord() {
        String foreignWord = getCurrentCard().getForeignWord();
        if(lnForeignTxt.getText().equals(foreignWord)) {
            systemLabelLearn.setText("Richtig: " + foreignWord);
        } else {
            systemLabelLearn.setText("Falsch: " + foreignWord);
        } 
    }

    public void nextCard() {
        systemLabelLearn.setText("");
        if(currentIndex < (currentList.size() - 1)) {
            currentIndex++;
            System.out.println("currentIndex: " + currentIndex);
            System.out.println("currentList" + currentList.size());
            
            startLearning();
        } else {
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Ende");
            confirmationAlert.setContentText("Möchten Sie nochmals lernen?");
            ButtonType okButton = new ButtonType("JA");
            ButtonType noButton = new ButtonType("NEIN");
            confirmationAlert.getButtonTypes().setAll(okButton, noButton);
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if(result.get() == okButton) {
                resetLearningSession();
                startLearning();
            } else if(result.get() == noButton) {
                resetLearningSession();
            }
        }
    }

    public void previousCard() {
        systemLabelLearn.setText("");
        if(currentIndex != 0) {
            currentIndex--;
            startLearning();
        }
    }

    public void resetLearningSession() {
        lnGermanTxt.setText("");
        lnForeignTxt.setText("");
        currentIndex = 0;
    }

    /************************
    * File handling section *
    *************************/

    public void saveFile() {
        List<Card> allStack = englishStack.getCards();
        allStack.addAll(frenchStack.getCards());
        allStack.addAll(italianStack.getCards());
        allStack.addAll(spanishStack.getCards());

        try {
            FileAccess.saveFile("data.txt", allStack.toString());
            Alert informationAlert = new Alert(AlertType.INFORMATION);
            informationAlert.setTitle("Karteikarte");
            informationAlert.setContentText("Daten wurde erfolgreich gespeichert");
            informationAlert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception: " + e.getClass().getName());
			alert.setHeaderText(null);
			alert.setContentText("Error-Message: " + e.getMessage());
			alert.showAndWait();
		}
    }

    @FXML
	private void loadFile() {
		try {
			String content = FileAccess.readFile("data.txt");
            ObjectMapper mapper = new ObjectMapper();
            JSONCard[] cards = mapper.readValue(content, JSONCard[].class);

            for (JSONCard card : cards) {
                Card newCard = new Card(card.id, card.word, card.foreignWord, card.learned, card.language);

                if(card.getLanguage().equals("Englisch")) {
                    englishlist.add(newCard);
                } else if(card.getLanguage().equals("Französisch")) {
                    frenchlist.add(newCard);
                }  else if(card.getLanguage().equals("Italienisch")) {
                    italianlist.add(newCard);
                } else if(card.getLanguage().equals("Spanisch")) {
                    spanishList.add(newCard);
                }
              }

		} catch (Exception e) {
            System.out.println(e.getMessage());
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception: " + e.getClass().getName());
			alert.setHeaderText(null);
			alert.setContentText("Error-Message: " + e.getMessage());
			alert.showAndWait();
            
		}
		
	}
}

class JSONCard {
    String id;
    String word;
    String foreignWord;
    boolean learned;
    String language;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getForeignWord() {
        return this.foreignWord;
    }

    public void setForeignWord(String foreignWord) {
        this.foreignWord = foreignWord;
    }

    public boolean isLearned() {
        return this.learned;
    }

    public boolean getLearned() {
        return this.learned;
    }

    public void setLearned(boolean learned) {
        this.learned = learned;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String langugage) {
        this.language = langugage;
    }
  }