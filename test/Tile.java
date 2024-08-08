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

    //note for myself:
    //The Bag class is implemented using the Singleton Pattern,
    //ensuring that only one instance of the Bag class exists throughout the application.
    public static class Bag
    {
        int [] quantity;
        Tile [] tiles;
        private static Bag bag= null;

        private Bag()
        {
            this.quantity= new int []{9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,6,2,2,1,2,1};
            this.tiles= new Tile[]{
                    new Tile('A', 1),
                    new Tile('B', 3),
                    new Tile('C', 3),
                    new Tile('D', 2),
                    new Tile('E', 1),
                    new Tile('F', 4),
                    new Tile('G', 2),
                    new Tile('H', 4),
                    new Tile('I', 1),
                    new Tile('J', 8),
                    new Tile('K', 5),
                    new Tile('L', 1),
                    new Tile('M', 3),
                    new Tile('N', 1),
                    new Tile('O', 1),
                    new Tile('P', 3),
                    new Tile('Q', 10),
                    new Tile('R', 1),
                    new Tile('S', 1),
                    new Tile('T', 1),
                    new Tile('U', 1),
                    new Tile('V', 4),
                    new Tile('W', 4),
                    new Tile('X', 8),
                    new Tile('Y', 4),
                    new Tile('Z', 10)
            };
        }
        
        //note to myself: now if the value in null it'ii create a bag, othewise it return the existing bag
        public static Bag getBag() {
            if (bag == null) {
                bag = new Bag();
            }
            return bag;
        }
    }
	
}
