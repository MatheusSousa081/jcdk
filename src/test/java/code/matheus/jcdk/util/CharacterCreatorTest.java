package code.matheus.jcdk.util;

import code.matheus.jcdk.character.Character;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterCreatorTest {
    @Test
    void create() {
        Character character = CharacterCreator.create("Carlos", Character.Gender.MAN, Character.class);
        assertNotNull(character);
        assertEquals("Carlos", character.getName());
        assertEquals(Character.Gender.MAN, character.getGender());
    }
}