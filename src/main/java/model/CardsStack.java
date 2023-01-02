package model;

import java.util.List;

import interfaces.Deck;
import javafx.beans.property.StringProperty;
import enumeration.*;

public class CardsStack implements Deck {

    private StringProperty id;
    private StringProperty name;
    private List<Card> cards;
    private Language language;

    public CardsStack() {
    }

    public CardsStack(StringProperty id, StringProperty name, List<Card> cards, Language language) {
        this.id = id;
        this.name = name;
        this.cards = cards;
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public StringProperty getId() {
        return this.id;
    }

    public void setId(StringProperty id) {
        this.id = id;
    }

    public StringProperty getName() {
        return this.name;
    }

    public void setName(StringProperty name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public void create() {}

    @Override
    public void delete() {}

    @Override
    public void edite() {}
    
}
