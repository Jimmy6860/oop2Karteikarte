package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    private Card card;

    @BeforeEach
    void setUp() {
        card = new Card("1", "hello", "hallo", false, "English");
    }

    @Test
    void testConstructorWithParameters() {
        assertEquals("1", card.getId());
        assertEquals("hello", card.getWord());
        assertEquals("hallo", card.getForeignWord());
        assertFalse(card.getLearned());
        assertEquals("English", card.getLanguage());
    }

    @Test
    void testGetId() {
        assertEquals("1", card.getId());
    }

    @Test
    void testGetWord() {
        assertEquals("hello", card.getWord());
    }

    @Test
    void testGetForeignWord() {
        assertEquals("hallo", card.getForeignWord());
    }

    @Test
    void testGetLearned() {
        assertFalse(card.getLearned());
    }

    @Test
    void testGetLanguage() {
        assertEquals("English", card.getLanguage());
    }

    @Test
    void testSetId() {
        card.setId("2");
        assertEquals("2", card.getId());
    }

    @Test
    void testSetWord() {
        card.setWord("world");
        assertEquals("world", card.getWord());
    }

    @Test
    void testSetForeignWord() {
        card.setForeignWord("Welt");
        assertEquals("Welt", card.getForeignWord());
    }

    @Test
    void testSetLearned() {
        card.setLearned(true);
        assertTrue(card.getLearned());
    }

    @Test
    void testSetLanguage() {
        card.setLanguage("German");
        assertEquals("German", card.getLanguage());
    }
}
