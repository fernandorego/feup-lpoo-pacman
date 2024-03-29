package com.g50.view;

import java.util.Objects;

public class ViewProperty {
    private final String color;
    private final Character character;

    public ViewProperty(String color, char character) {
        this.color = color;
        this.character = character;
    }

    public ViewProperty(char character) {
        this("#FFFFFF", character);
    }

    public String getColor() {
        return color;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewProperty)) return false;
        ViewProperty that = (ViewProperty) o;
        return getCharacter() == that.getCharacter() && getColor().equals(that.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getCharacter());
    }
}
