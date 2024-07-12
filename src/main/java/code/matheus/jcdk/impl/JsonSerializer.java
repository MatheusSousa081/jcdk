package code.matheus.jdck.impl;

import code.matheus.jdck.character.Character;
import code.matheus.jdck.serialization.Serializer;
import code.matheus.jdck.util.CharacterCreator;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public final class JsonSerializer implements Serializer {
    private final @NotNull Gson gson = new Gson();

    @Override
    public void serialize(@NotNull CharacterImpl character, @NotNull String path) {
        try (JsonWriter writer = new JsonWriter(new BufferedWriter(new FileWriter(path)))) {
            writer.beginObject();
            writer.name("name").value(character.getName());
            writer.name("gender").value(character.getGender().name());
            writer.name("health").value(character.getHealth());
            writer.name("stamina").value(character.getStamina());
            writer.name("agility").value(character.getAgility());
            writer.name("defense").value(character.getDefense());
            writer.name("level").value(character.getLevel());
            writer.name("xp").value(character.getExperience().getPoints());
            writer.name("points").value(character.getImproveAttribute().getAvailablePoints());
            writer.endObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull String serialize(@NotNull CharacterImpl character) {
        return "CharacterImpl{" +
                "name='" + character.getName() + '\'' +
                ", gender=" + character.getGender() +
                ", health=" + character.getHealth() +
                ", stamina=" + character.getStamina() +
                ", defense=" + character.getDefense() +
                ", agility=" + character.getAgility() +
                ", level=" + character.getLevel() +
                ", experience=" + character.getExperience().getPoints() +
                ", points=" + character.getImproveAttribute().getAvailablePoints() +
                '}';
    }

    @Override
    public @NotNull CharacterImpl deserializer(@NotNull File file) {
        try (JsonReader reader = new JsonReader(new BufferedReader(new FileReader(file.getPath())))) {
            CharacterImpl character = CharacterCreator.create("", Character.Gender.MAN, CharacterImpl.class);
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                switch (name) {
                    case "name" -> character.setName(reader.nextString());
                    case "gender" -> character.setGender(reader.nextString());
                    case "health" -> character.setHealth(reader.nextDouble());
                    case "stamina" -> character.setStamina(reader.nextDouble());
                    case "agility" -> character.setAgility(reader.nextDouble());
                    case "defense" -> character.setDefense(reader.nextDouble());
                    case "level" -> character.setLevel(reader.nextInt());
                    case "xp" -> character.getExperience().add(reader.nextInt());
                    case "points" -> character.getImproveAttribute().addPoints(reader.nextInt());
                }
            }
            reader.endObject();
            return character;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull Character deserializer(@NotNull String string) {
        String data = string.substring(string.indexOf("{") + 1, string.lastIndexOf("}"));

        // Dividir os dados com base nas vírgulas
        String[] parts = data.split(",");

        CharacterImpl character = CharacterCreator.create("name", Character.Gender.MAN, CharacterImpl.class);

        // Iterar sobre os campos e extrair os valores correspondentes
        for (String part : parts) {
            String[] keyValue = part.trim().split("=");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            switch (key) {
                case "name"-> character.setName(value.substring(value.indexOf("'") + 1, value.lastIndexOf("'")));
                case "gender"-> character.setGender(Character.Gender.valueOf(value.toUpperCase()));
                case "health"-> character.setHealth(Double.parseDouble(value));
                case "stamina"-> character.setStamina(Double.parseDouble(value));
                case "agility"-> character.setAgility(Double.parseDouble(value));
                case "defense"-> character.setDefense(Double.parseDouble(value));
                case "level"-> character.setLevel(Integer.parseInt(value));
                case "experience"-> character.getExperience().add(Integer.parseInt(value));
                case "points"-> character.getImproveAttribute().addPoints(Integer.parseInt(value));
            }
        }
        return character;
    }
}