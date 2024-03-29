package com.g50.model.element.movable;

import com.g50.model.element.Position;

public class PacMan extends MovableElement {
    private int lives;

    public PacMan(Position position) {
        super("PacMan", position, Orientation.LEFT);
        this.lives = 3;
    }

    public void decreaseLives() { this.lives--; }

    public void increaseLives() { this.lives++; }

    public int getLives() { return this.lives; }

    public boolean isAlive() { return this.lives > 0; }
}
