package code.matheus.jdck.character;

import code.matheus.jdck.attribute.Attribute;;
import code.matheus.jdck.attribute.Level;
import org.jetbrains.annotations.NotNull;

public interface Character extends Attribute {
    @NotNull String getName();

    void setName(@NotNull String name);

    @NotNull Gender getGender();

    void setGender(@NotNull Gender gender);

    void setGender(@NotNull String gender);

    int getDeaths();

    @Override
    double getHealth();

    @Override
    void setHealth(double health);

    @Override
    int getLevel();

    @Override
    void setLevel(int level);

    @Override
    @NotNull Level.Experience getExperience();

    @Override
    double getStamina();

    @Override
    void setStamina(double stamina);

    @Override
    double getAgility();

    @Override
    void setAgility(double agility);

    @Override
    double getDefense();

    @Override
    void setDefense(double defense);

    enum Gender {
        MAN,
        WOMAN
    }
}