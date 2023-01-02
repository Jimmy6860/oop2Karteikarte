package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

import interfaces.Deck;

public class Card implements Deck {

    private StringProperty id;
    private StringProperty word;
	private StringProperty foreignWord;
	private BooleanProperty learned;

    public Card() {
    }

    public Card(StringProperty id, StringProperty word, StringProperty foreignWord, BooleanProperty learned) {
        this.id = id;
        this.word = word;
        this.foreignWord = foreignWord;
        this.learned = learned;
    }

    public StringProperty getId() {
        return this.id;
    }

    public void setId(StringProperty id) {
        this.id = id;
    }

    public StringProperty getWord() {
        return this.word;
    }

    public void setWord(StringProperty word) {
        this.word = word;
    }

    public StringProperty getForeignWord() {
        return this.foreignWord;
    }

    public void setForeignWord(StringProperty foreignWord) {
        this.foreignWord = foreignWord;
    }

    public BooleanProperty getLearned() {
        return this.learned;
    }

    public void setLearned(BooleanProperty learned) {
        this.learned = learned;
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
