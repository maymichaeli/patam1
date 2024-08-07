package test;

import java.util.Objects;

public class Tile {
    public final char letter;
    public final int score;

    private Tile (char letter, int score)
    {
        this.letter=letter;
        this.score= score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }
	
}
