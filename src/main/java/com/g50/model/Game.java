package com.g50.model;

import com.g50.model.map.GameMap;

import java.io.IOException;

public class Game {
    private final GameMap map;
    private int score;
    private final int highScore;
    private final Level level;

    public Game(GameMap map, int highScore, int level) throws IOException {
        assert level > 0;
        this.map = map;
        this.highScore = highScore;
        this.score = 0;
        this.level = new Level(level);
    }

    public Game(GameMap map, int highScore, int level, int score) throws IOException {
        this.map = map;
        this.highScore = highScore;
        this.score = score;
        this.level = new Level(level);
    }

    public GameMap getGameMap() {
        return map;
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public Level getLevel() { return level; }

    public void incrementScore(int increment) { score += increment; }

    public void resetScore() { score = 0; }

    public void resetPositions() {
        this.map.resetPositions();
    }
}
