package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import enumeration.Language;
import model.Card;
import model.CardsStack;

public class CardsStackTest {
    
    private CardsStack cardsStack;
    
    @BeforeEach
    public void setUp() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("1", "Hello", "Hallo", false, "Englisch"));
        cards.add(new Card("2", "Goodbye", "Auf Wiedersehen", false, "Englisch"));
        cardsStack = new CardsStack("1", "English-German Vocabulary", cards, Language.Englisch);
    }
    
    @Test
    public void testGetLanguage() {
        assertEquals(Language.Englisch, cardsStack.getLanguage());
    }
    
    @Test
    public void testSetLanguage() {
        cardsStack.setLanguage(Language.Französisch);
        assertEquals(Language.Französisch, cardsStack.getLanguage());
    }
    
    @Test
    public void testGetId() {
        assertEquals("1", cardsStack.getId());
    }
    
    @Test
    public void testSetId() {
        cardsStack.setId("2");
        assertEquals("2", cardsStack.getId());
    }
    
    @Test
    public void testGetName() {
        assertEquals("English-German Vocabulary", cardsStack.getName());
    }
    
    @Test
    public void testSetName() {
        cardsStack.setName("German-English Vocabulary");
        assertEquals("German-English Vocabulary", cardsStack.getName());
    }
    
    @Test
    public void testGetCards() {
        assertNotNull(cardsStack.getCards());
        assertEquals(2, cardsStack.getCards().size());
    }
    
    @Test
    public void testSetCards() {
        List<Card> newCards = new ArrayList<>();
        newCards.add(new Card("3", "Yes", "Ja", false, "English"));
        newCards.add(new Card("4", "No", "Nein", false, "English"));
        cardsStack.setCards(newCards);
        assertNotNull(cardsStack.getCards());
        assertEquals(2, cardsStack.getCards().size());
        assertEquals("Yes", cardsStack.getCards().get(0).getWord());
        assertEquals("Ja", cardsStack.getCards().get(0).getForeignWord());
        assertEquals("No", cardsStack.getCards().get(1).getWord());
        assertEquals("Nein", cardsStack.getCards().get(1).getForeignWord());
    }
    
}

