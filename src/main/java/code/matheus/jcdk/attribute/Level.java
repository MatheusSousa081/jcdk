package code.matheus.jdck.attribute;

import org.jetbrains.annotations.Range;

public final class Level {
    @Range(from = 1, to = 100)
    private final int limitLevel;
    @Range(from = 1, to = 100)
    private int currentLevel;
    private final int[] levels;
    private boolean levelInitialized = false;

    public Level(@Range(from = 1, to = 100) int limitLevel) {
        currentLevel = 1;
        this.limitLevel = limitLevel;
        levels = new int[limitLevel];
        initializeLevels();
    }

    public void setCurrentLevel(@Range(from = 1, to = 100) int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int[] getLevels() {
        return levels;
    }

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

    public Experience getExperience() {
        return new Experience();
    }

    public final class Experience {
        private int pointsXp = 0;

        public int getPoints() {
            return pointsXp;
        }

        public void add(@Range(from = 1, to = 10) int points) {
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
}