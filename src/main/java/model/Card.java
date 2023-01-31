package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.UUID;

import interfaces.Deck;

public class Card implements Deck {
    UUID uuid = UUID.randomUUID();
    String uuidAsString = uuid.toString();

    private String id;
    private StringProperty word;
	private StringProperty foreignWord;
	private boolean learned;

    public Card() {
        this(null, null, null, null);
    }

    public Card(String id, String word, String foreignWord, Boolean learned) {
        this.id = uuidAsString;
        this.word = new SimpleStringProperty(word);
        this.foreignWord = new SimpleStringProperty(foreignWord);
        this.learned = false;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //Word
    public String getWord() {
        return word.get();
    }

    public void setWord(String word) {
        this.word.set(word);
    }

	public StringProperty wordProperty() {
		return word;
	}

    //Foreign word
    public String getForeignWord() {
        return foreignWord.get();
    }

    public void setForeignWord(String foreignWord) {
        this.foreignWord.set(foreignWord);
    }

    public StringProperty foreignWordProperty() {
		return foreignWord;
	}

    //Gelernt
    public boolean getLearned() {
        return this.learned;
    }

    public void setLearned(boolean learned) {
        this.learned = learned;
    }

    // public BooleanProperty learnedProperty() {
    //     return learned;
    // }

    @Override
    public String toString() {
        return "{" +
            " word='" + getWord() + "'" +
            ", foreignWord='" + getForeignWord() + "'" +
            ", learned='" + getLearned() + "'" +
            "}";
    }

    @Override
    public void create() {}

    @Override
    public void delete() {}

    @Override
    public void edite() {}
    
}
