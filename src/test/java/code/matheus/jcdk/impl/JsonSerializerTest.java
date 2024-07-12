package code.matheus.jcdk.impl;

import code.matheus.jcdk.character.Character;
import code.matheus.jcdk.util.CharacterCreator;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class JsonSerializerTest {
    @Test
    public void testSerializableAndDeserializable() {
        CharacterImpl character = CharacterCreator.create("Carlos", Character.Gender.WOMAN, CharacterImpl.class);
        character.getExperience().add(1000);
        character.getImproveAttributes().addPoints(10);
        character.getImproveAttributes().distributePoints("health", 3);
        character.getImproveAttributes().distributePoint("stamina");

        JsonSerializer serializer = new JsonSerializer();
        File file = new File("character.json");
        serializer.serialize(character, file.getPath());

        CharacterImpl copy = serializer.deserializer(file);

        assertEquals(character.getName(), copy.getName());
        assertEquals(character.getGender(), copy.getGender());
        assertEquals(character.getHealth(), copy.getHealth());
        assertEquals(character.getStamina(), copy.getStamina());
        assertEquals(character.getAgility(), copy.getAgility());
        assertEquals(character.getDefense(), copy.getDefense());
        assertEquals(character.getLevel(), copy.getLevel());
        assertEquals(character.getExperience().getPoints(), copy.getExperience().getPoints());
        assertEquals(character.getImproveAttributes().getAvailablePoints(), copy.getImproveAttributes().getAvailablePoints());
    }
}