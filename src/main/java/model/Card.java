package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.UUID;

import enumeration.Language;
import interfaces.Deck;

public class Card implements Deck {
    UUID uuid = UUID.randomUUID();
    String uuidAsString = uuid.toString();

    private String id;
    private StringProperty word;
	private StringProperty foreignWord;
	private boolean learned;
    private String language;

    public Card() {
        this(null, null, null, null, null);
    }

    public Card(String id, String word, String foreignWord, Boolean learned, String language) {
        this.id = id.length() == 0 ? uuidAsString : id ;
        this.word = new SimpleStringProperty(word);
        this.foreignWord = new SimpleStringProperty(foreignWord);
        this.learned = learned;
        this.language = language;
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

    //Langauge
    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "{" +
        "\"id\":" + "\"" + getId() + "\"" +
        ", \"word\":" + "\"" + getWord() + "\"" +
        ", \"foreignWord\":" + "\"" + getForeignWord() + "\"" +
        ", \"learned\":" + getLearned() +
        ", \"language\":" + "\"" + getLanguage() + "\"" +
        "}";
    }

    @Override
    public void create() {}

    @Override
    public void delete() {}

    @Override
    public void edite() {}
    
}
