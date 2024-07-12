package code.matheus.jcdk.attribute;

import org.jetbrains.annotations.Range;

public final class Level {
    //Variables

    @Range(from = 1, to = 100)
    private final int limitLevel;
    @Range(from = 1, to = 100)
    private int currentLevel;
    private final int[] levels;
    private boolean levelInitialized = false;

    //Constructors

    public Level(@Range(from = 1, to = 100) int limitLevel) {
        currentLevel = 1;
        this.limitLevel = limitLevel;
        levels = new int[limitLevel];
        initializeLevels();
    }

    //Getters and Setters

    public void setCurrentLevel(@Range(from = 1, to = 100) int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int[] getLevels() {
        return levels;
    }

    public Experience getExperience() {
        return new Experience();
    }

    //Inner Class

    public final class Experience {
        private int pointsXp = 0;

        public int getPoints() {
            return pointsXp;
        }

        public void add(int points) {
            this.pointsXp += points;
            calculateLevel();
        }

        private void calculateLevel() {
            for (int i = 0; i < levels.length; i++) {
                if (pointsXp < levels[i]) {
                    currentLevel = i - 1;
                    return;
                }

                if (i == levels.length - 1) {
                    currentLevel = limitLevel;
                }
            }
        }

        public int getLevel() {
            return currentLevel;
        }
    }

    //Methods statics and privates

    private void initializeLevels() {
        if (!levelInitialized) {
            levels[0] = 0;
            int pointsForNextLevel = 100;
            for (int i = 1; i < levels.length; i++) {
                levels[i] = pointsForNextLevel;
                pointsForNextLevel *= 1.15;
            }
            levelInitialized = true;
        }
    }
}