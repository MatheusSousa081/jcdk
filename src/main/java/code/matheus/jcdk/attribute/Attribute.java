package code.matheus.jcdk.attribute;

import org.jetbrains.annotations.NotNull;

public interface Attribute{
    double getHealth();

    void setHealth(double health);

    int getLevel();

    void setLevel(int level);

    @NotNull Level.Experience getExperience();

    double getStamina();

    void setStamina(double stamina);

    double getAgility();

    void setAgility(double agility);

    double getDefense();

    void setDefense(double defense);
}