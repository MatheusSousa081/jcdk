package code.matheus.jdck.impl;

import code.matheus.jdck.character.Character;
import code.matheus.jdck.attribute.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.lang.reflect.Field;

public class CharacterImpl implements Character {
    private @NotNull String name;
    private @NotNull Gender gender;
    private final double initialHealth = 10;
    private int deaths = 0;
    private double health = 10;
    private double stamina = 10;
    private double defense = 10;
    private double agility = 10;
    private final @NotNull Level level = new Level(100);
    private final @NotNull Level.Experience experience = level.getExperience();

    private final @NotNull ImproveAttributes improveAttributes = new ImproveAttributes();

    private CharacterImpl(@NotNull String name, @NotNull Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public void setName(@NotNull String name) {
        this.name = name;
    }

    @Override
    public @NotNull Gender getGender() {
        return gender;
    }

    @Override
    public void setGender(@NotNull Gender gender) {
        this.gender = gender;
    }

    @Override
    public void setGender(@NotNull String gender) {
        this.gender = Gender.valueOf(gender);
    }

    @Override
    public int getDeaths() {
        return deaths;
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public void setHealth(double health) {
        this.health = health;
        checkDeath();
    }

    @Override
    public int getLevel() {
        return experience.getLevel();
    }

    @Override
    public void setLevel(int level) {
        this.level.setCurrentLevel(level);
    }

    @Override
    public @NotNull Level.Experience getExperience() {
        return experience;
    }

    @Override
    public double getStamina() {
        return stamina;
    }

    @Override
    public void setStamina(double stamina) {
        this.stamina = stamina;
    }

    @Override
    public double getAgility() {
        return agility;
    }

    @Override
    public void setAgility(double agility) {
        this.agility = agility;
    }

    @Override
    public double getDefense() {
        return defense;
    }

    @Override
    public void setDefense(double defense) {
        this.defense = defense;
    }

    public void checkDeath() {
        if (health <= 0) {
            deaths++;
            revive();
        }
    }

    @Override
    public String toString() {
        return "CharacterImpl{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", health=" + health +
                ", stamina=" + stamina +
                ", defense=" + defense +
                ", agility=" + agility +
                ", level=" + experience.getLevel() +
                ", experience=" + experience.getPoints() +
                ", points=" + getImproveAttribute().getAvailablePoints() +
                '}';
    }

    private void revive() {
        health = initialHealth;
    }

    public ImproveAttributes getImproveAttribute() {
        return improveAttributes;
    }

    public final class ImproveAttributes {
        private int availablePoints = 0;

        public int getAvailablePoints() {
            return availablePoints;
        }

        public void addPoints(int points) {
            this.availablePoints += points;
        }

        public void distributePoint(@NotNull String attributeName) {
            distributePoints(attributeName, 1);
        }

        public void distributePoints(@NotNull String attributeName,@Range(from=1, to=3) int points) {
            if (availablePoints <= 0) {
                throw new RuntimeException("You don't have enough points!");
            }
            try {
                Field field = CharacterImpl.this.getClass().getDeclaredField(attributeName);
                field.setAccessible(true);
                double currentValue = field.getDouble(CharacterImpl.this);
                field.setDouble(CharacterImpl.this, currentValue + points);
                availablePoints -= points;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException("Error accessing the attribute");
            }
        }
    }
}