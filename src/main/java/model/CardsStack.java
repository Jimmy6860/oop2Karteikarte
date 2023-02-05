package model;

import java.util.List;

import interfaces.Deck;
import javafx.beans.property.StringProperty;
import enumeration.*;

public class CardsStack implements Deck {

    private String id;
    private String name;
    private List<Card> cards;
    private Language language;

    public CardsStack() {
    }

    public CardsStack(String id, String name, List<Card> cards, Language language) {
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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
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
