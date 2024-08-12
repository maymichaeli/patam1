package test;

import test.Tile.Bag;

public class Board {
    Tile [][] boardTiles;
    String [][] score;
    int size=15;
    private static Board board= null;

    Board()
    {
        boardTiles= new Tile[15][15];
        score= new String[15][15];
        //first we put empty values in the boared
        for(int i=0; i< size; i++)
        {
            for(int j=0; j<size; j++)
            {
                this.score[i][j]="";
            }
        }
        //now insert the value for each index that have score
        this.score[7][7]="Yellow"; //the middle
        this.score[1][1]="Yellow";
        this.score[2][2]="Yellow";
        this.score[3][3]="Yellow";
        this.score[4][4]="Yellow";
        this.score[13][1]="Yellow";
        this.score[12][2]="Yellow";
        this.score[11][3]="Yellow";
        this.score[10][4]="Yellow";
        this.score[13][13]="Yellow";
        this.score[12][12]="Yellow";
        this.score[11][11]="Yellow";
        this.score[10][10]="Yellow";
        this.score[4][10]="Yellow";
        this.score[3][11]="Yellow";
        this.score[2][12]="Yellow";
        this.score[1][13]="Yellow";
        this.score[0][0]="Red";
        this.score[0][7]="Red";
        this.score[0][14]="Red";
        this.score[7][14]="Red";
        this.score[14][14]="Red";
        this.score[14][7]="Red";
        this.score[14][0]="Red";
        this.score[7][0]="Red";
        this.score[0][3]="lightBlue";
        this.score[0][11]="lightBlue";
        this.score[2][6]="lightBlue";
        this.score[2][8]="lightBlue";
        this.score[3][7]="lightBlue";
        this.score[3][14]="lightBlue";
        this.score[6][12]="lightBlue";
        this.score[7][11]="lightBlue";
        this.score[8][12]="lightBlue";
        this.score[11][14]="lightBlue";
        this.score[11][7]="lightBlue";
        this.score[12][8]="lightBlue";
        this.score[12][6]="lightBlue";
        this.score[14][11]="lightBlue";
        this.score[14][3]="lightBlue";
        this.score[11][0]="lightBlue";
        this.score[3][0]="lightBlue";
        this.score[8][2]="lightBlue";
        this.score[7][3]="lightBlue";
        this.score[6][2]="lightBlue";
        this.score[6][6]="lightBlue";
        this.score[6][8]="lightBlue";
        this.score[8][8]="lightBlue";
        this.score[8][6]="lightBlue";
        this.score[1][5]="blue";
        this.score[1][9]="blue";
        this.score[5][13]="blue";
        this.score[9][13]="blue";
        this.score[13][9]="blue";
        this.score[13][5]="blue";
        this.score[9][1]="blue";
        this.score[5][1]="blue";
        this.score[5][5]="blue";
        this.score[5][9]="blue";
        this.score[9][9]="blue";
        this.score[9][5]="blue";
    }

    public static Board getBoard()
    {
        
    }

}
