package code.matheus.jdck;

import code.matheus.jdck.character.Character;
import code.matheus.jdck.impl.CharacterImpl;
import code.matheus.jdck.impl.JsonSerializer;
import code.matheus.jdck.util.CharacterCreator;
import code.matheus.jdck.attribute.Level;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Level level = new Level(100);

        for (int i = 0; i < level.getLevels().length; i++) {
            System.out.println(i + ": " + level.getLevels()[i]);
        }

        Level.Experience xp = level.new Experience();
        xp.add(10000);
        System.out.println(xp.getLevel());

        CharacterImpl character = CharacterCreator.create("Carlos", Character.Gender.MAN, CharacterImpl.class);
        CharacterImpl.ImproveAttributes upAttributes = character.getImproveAttribute();

        upAttributes.addPoints(10);
        upAttributes.distributePoints("agility", 2);

        System.out.println("agility " + character.getAgility());
        character.getExperience().add(10000);
        System.out.println("Level:" + character.getLevel());
        System.out.println(character.getExperience().getPoints());

        File file = new File("character.json");

        JsonSerializer serializer = new JsonSerializer();
        serializer.serialize(character, file.getPath());
        String obj = serializer.serialize(character);
        System.out.println("Serializado: " + obj);
        System.out.println("Objeto: " + character);
        System.out.println("Deserializado: " + serializer.deserializer(obj));
        System.out.println("Deserializado: " + serializer.deserializer(file));
        character.getImproveAttribute().addPoints(10);
        System.out.println(character.getImproveAttribute().getAvailablePoints());
    }
}