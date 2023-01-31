package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


import interfaces.Deck;

public class Card implements Deck {

    private String id;
    private StringProperty word;
	private StringProperty foreignWord;
	private BooleanProperty learned;

    public Card() {
    }

    public Card(String id, String word, String foreignWord, Boolean learned) {
        this.id = id;
        this.word = new SimpleStringProperty(word);
        this.foreignWord = new SimpleStringProperty(foreignWord);
        this.learned = new SimpleBooleanProperty(learned);
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

    //Gelernd
    public boolean getLearned() {
        return learned.get();
    }

    public void setLearned(boolean learned) {
        this.learned.set(learned);
    }

    public BooleanProperty learnedProperty() {
        return learned;
    }

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
