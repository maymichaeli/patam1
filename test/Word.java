package test;

import java.util.Objects;

public class Word {
    Tile tiles;
    int row;
    int col;
    boolean vertical; //if vertical it true else false
	
    private Word( Tile tiles, int row, int col, boolean vertical)
    {
        this.tiles=tiles;
        this.col= col;
        this.row= row;
        this.vertical= vertical;
    }

    public Tile getTiles()
    {
        return this.tiles;
    }

    public int getCol()
    {
        return this.col;
    }
    public int getRow()
    {
        return this.row;
    }
    public boolean getVertical()
    {
        return this.vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return row == word.row && col == word.col && vertical == word.vertical && Objects.equals(tiles, word.tiles);
    }

}
