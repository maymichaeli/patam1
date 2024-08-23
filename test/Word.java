package test;

import java.util.Arrays;
import java.util.Objects;

public class Word {
    Tile[] tiles;
    int row;
    int col;
    boolean vertical; //if vertical it true else false
	
    public Word( Tile[] tiles, int row, int col, boolean vertical)
    {
        this.tiles=tiles;
        this.col= col;
        this.row= row;
        this.vertical= vertical;
    }

    public Tile[] getTiles()
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
    public boolean isVertical()
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

    @Override
    public int hashCode() {
        return Objects.hash(row, col, vertical, Arrays.hashCode(tiles));
    }
    
    @Override
    public String toString()
    {
        String s = "";
        for(Tile t: tiles)
        {
            if(t== null)
                s+="_";
            else 
                s+= t.letter;
        }
        return s;
    }


}
