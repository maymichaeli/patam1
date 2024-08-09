package test;



import java.util.Objects;
import java.util.Random;

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
        int [] originalQuantity;
        Tile [] tiles;
        private static Bag bag= null;

        private Bag()
        {
            this.quantity= new int []{9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,6,2,2,1,2,1};
            this.originalQuantity = this.quantity.clone();
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

        public Tile getRand()
        {
            int sum=0;
            int[] validIndexes = new int[tiles.length];
            int index=0;
            for (int i=0; i< quantity.length; i++)
            {
                  // note: Find all indexes with non-zero quantities
                if (quantity[i] > 0) {
                    validIndexes[index++] = i;
                }
                sum+=quantity[i];
            }
            if(sum==0)
                {
                    return null;
                }

             // Choose a random valid index
            //  יצירת מספרים אקראיים: מחלקת Random מספקת את השיטה nextInt(int bound), 
            //  שמחזירה מספר אקראי בטווח של 0 עד bound - 1.
            //  זה שימושי במקרה הזה כדי לבחור אקראית אינדקס מתוך רשימה של אינדקסים תקפים.
            
             Random rand = new Random();  // Initialize Random object
             int randIndex = validIndexes[rand.nextInt(index)];
             quantity[randIndex]--;
            
             return tiles[randIndex];
        }

        public Tile getTile(char t)
        {
            if(t>='A'&& t<='Z')
            {
                int num= t-'A';
                if(size()<=0)
                    return null;
                if(this.quantity[num]>0)
                {
                    this.quantity[num]--;
                    return tiles[num];
                }
               return null;

            }
            return null;
        }

        public int size()
        {
            int size=0;
            for(int i=0; i< quantity.length; i++)
            {
                size+= quantity[i];
            }
            return size;
        }

        public void put(Tile t)
        {
            char c= t.letter;
            int num= c-'A';
            if(this.originalQuantity[num]>this.quantity[num])
            {
                this.quantity[num]++;
            }
        }
        public int[] getQuantities()
        {
            return this.quantity.clone();
        }
    }
	
}


