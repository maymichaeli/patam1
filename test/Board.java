
package test;

import test.Tile.Bag;
import java.util.ArrayList;


public class Board {
    Tile [][] boardTiles;
    String [][] score;
    int size=15;
    int numOfTiles=0;
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

    //singleton
    public static Board getBoard()
    {
        if (board == null)
            board = new Board();
        return board;
    }
    //need to return copy of tiles
    public Tile [][] getTile()
    {
        return this.boardTiles.clone();
    }

    // מתלבטת אם העתקה שטוחה או עמוקה

    //  // Return a copy of the tiles array
    //  public Tile[][] getTiles() {
    //     Tile[][] tilesCopy = new Tile[size][size];
    //     for (int i = 0; i < size; i++) {
    //         for (int j = 0; j < size; j++) {
    //             tilesCopy[i][j] = this.boardTiles[i][j]; // Copy each tile reference
    //         }
    //     }
    //     return tilesCopy;
    // }
    
    private boolean isWithinBounds(Word w) {
        int row = w.getRow();
        int col = w.getCol();
        int length = w.getTiles().length;

        if (w.isVertical()) {
            return row >= 0 && row + length <= size && col >= 0 && col < size;
        } else {
            return col >= 0 && col + length <= size && row >= 0 && row < size;
        }
    }

    //check if the word touching existing tile
    private boolean isTouchingExistingTile(Word w) {
        int row = w.getRow();
        int col = w.getCol();
        Tile[] tiles = w.getTiles();

        for (int i = 0; i < tiles.length; i++) {
            int r = w.isVertical() ? row + i : row;
            int c = w.isVertical() ? col : col + i;

            if (boardTiles[r][c] != null) {
                return true;
            }

            //check if there is a close tile
            if (r > 0 && boardTiles[r - 1][c] != null) return true;
            if (r < size - 1 && boardTiles[r + 1][c] != null) return true;
            if (c > 0 && boardTiles[r][c - 1] != null) return true;
            if (c < size - 1 && boardTiles[r][c + 1] != null) return true;
        }

        return false;
    }

    // private boolean doesNotReplaceExistingTiles(Word w) {
    //     int row = w.getRow();
    //     int col = w.getCol();
    //     Tile[] tiles = w.getTiles();

    //     for (int i = 0; i < tiles.length; i++) {
    //         int r = w.isVertical() ? row + i : row;
    //         int c = w.isVertical() ? col : col + i;

    //         if (boardTiles[r][c] != null && !boardTiles[r][c].equals(tiles[i])) {
    //             return false;
    //         }
    //     }

    //     return true;
    // }

    private boolean doesNotReplaceExistingTiles(Word w) {
        int row = w.getRow();
        int col = w.getCol();
        Tile[] tiles = w.getTiles();
    
        for (int i = 0; i < tiles.length; i++) {
            int r = w.isVertical() ? row + i : row;
            int c = w.isVertical() ? col : col + i;
    
            // Only check if the tile is not null
            if (tiles[i] != null) {
                if (boardTiles[r][c] != null && !boardTiles[r][c].equals(tiles[i])) {
                    return false; // The tile on the board doesn't match the one in the word
                }
            }
        }
    
        return true; // All non-null tiles either match or the board tile is empty
    }
    
    

    private boolean isTouchingCenter(Word w) {
        int row = w.getRow();
        int col = w.getCol();
        Tile[] tiles = w.getTiles();
    
        for (int i = 0; i < tiles.length; i++) {
            int r = w.isVertical() ? row + i : row;
            int c = w.isVertical() ? col : col + i;
    
            if (r == 7 && c == 7) {
                return true;
            }
        }
    
        return false;
    }

    public boolean boardLegal(Word w) {
        if (!isWithinBounds(w)) return false;
        
        //if this is the first word and its on the center index
        if (numOfTiles == 0 && !isTouchingCenter(w)) {
            return false;
        }
    
        if (!doesNotReplaceExistingTiles(w)) return false;
        if (numOfTiles > 0 && !isTouchingExistingTile(w)) return false;
    
        return true;
    }
    
    public boolean dictionaryLegal(Word w) //need to chaeck if the word exist in dictionary
    {
        return true; 
    }

    public ArrayList<Word> getWords(Word w) {
        ArrayList<Word> words = new ArrayList<>();
        Tile[] tiles = w.getTiles();
        int row = w.getRow();
        int col = w.getCol();
    
        // Add the original word, filling in nulls with existing tiles
        Tile[] filledTiles = new Tile[tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            int r = w.isVertical() ? row + i : row;
            int c = w.isVertical() ? col : col + i;
            filledTiles[i] = (tiles[i] == null) ? boardTiles[r][c] : tiles[i];
        }
        words.add(new Word(filledTiles, row, col, w.isVertical()));

        // Check for new words created horizontally
        if (!w.isVertical()) {
            for (int i = 0; i < tiles.length; i++) {
                int r = row;
                int c = col + i;
                
                // Check above and below for vertical words
                if (r > 0 && boardTiles[r - 1][c] != null || r < size - 1 && boardTiles[r + 1][c] != null) {
                    int startRow = r;
                    int endRow = r;
                    
                    // Find the start of the word
                    while (startRow > 0 && boardTiles[startRow - 1][c] != null) {
                        startRow--;
                    }
                    
                    // Find the end of the word
                    while (endRow < size - 1 && boardTiles[endRow + 1][c] != null) {
                        endRow++;
                    }
                    
                    // Collect the tiles and form the word
                    Tile[] wordTiles = new Tile[endRow - startRow + 1];
                    for (int j = startRow; j <= endRow; j++) {
                        wordTiles[j - startRow] = boardTiles[j][c];
                    }
                    
                    words.add(new Word(wordTiles, startRow, c, true));
                }
            }
        }
        // Check for new words created vertically
        else {
            for (int i = 0; i < tiles.length; i++) {
                int r = row + i;
                int c = col;
                
                // Check left and right for horizontal words
                if (c > 0 && boardTiles[r][c - 1] != null || c < size - 1 && boardTiles[r][c + 1] != null) {
                    int startCol = c;
                    int endCol = c;
                    
                    // Find the start of the word
                    while (startCol > 0 && boardTiles[r][startCol - 1] != null) {
                        startCol--;
                    }
                    
                    // Find the end of the word
                    while (endCol < size - 1 && boardTiles[r][endCol + 1] != null) {
                        endCol++;
                    }
                    
                    // Collect the tiles and form the word
                    Tile[] wordTiles = new Tile[endCol - startCol + 1];
                    for (int j = startCol; j <= endCol; j++) {
                        wordTiles[j - startCol] = boardTiles[r][j];
                    }
                    
                    words.add(new Word(wordTiles, r, startCol, false));
                }
            }
        }
        return words;
    }

    // public int getScore(Word w, Board board) {
    //     int score = 0;
    //     int multiplier = 1; // Multiplier for the word score based on bonuses
    
    //     // Iterate through each tile in the word
    //     for (int i = 0; i < w.getTiles().length; i++) {
    //         Tile tile = w.getTiles()[i];
    //         int row = w.getRow();
    //         int col = w.getCol();
    
    //         // Get the position of the tile on the board
    //         int r = w.isVertical() ? row + i : row;
    //         int c = w.isVertical() ? col : col + i;
    
    //         // Use the existing tile if the current tile is null
    //         if (tile == null) {
    //             tile = boardTiles[r][c];
    //         }
    
    //         if (tile == null) {
    //             // Handle the case where the tile is still null (shouldn't happen)
    //             continue;
    //         }
    
    //         // Add the tile's score
    //         score += tile.score;
    
    //         // Check for bonuses at the current position
    //         String bonus = board.score[r][c];
    //         if (bonus != null) {
    //             switch (bonus) {
    //                 case "Yellow":
    //                     // Double word score
    //                     multiplier *= 2;
    //                     break;
    //                 case "Red":
    //                     // Triple word score
    //                     multiplier *= 3;
    //                     break;
    //                 case "lightBlue":
    //                     // Double letter score
    //                     score += tile.score;
    //                     break;
    //                 case "blue":
    //                     // Triple letter score
    //                     score += 2 * tile.score;
    //                     break;
    //             }
    //         }
    //     }
    
    //     // Apply the word multiplier
    //     score *= multiplier;
    
    //     return score;
    // }
    
    // public int tryPlaceWord(Word w) {
    //     // Check if the word can be legally placed on the board
    //     if (!boardLegal(w)) {
    //         return 0;
    //     }
    
    //     // Get all new words that would be created by placing this word
    //     ArrayList<Word> newWords = getWords(w);
    
    //     // Check if all new words are legal according to the dictionary
    //     for (Word newWord : newWords) {
    //         if (!dictionaryLegal(newWord)) {
    //             return 0; // If any word is not legal, return 0 and do not place the word
    //         }
    //     }
    
    //     // If all checks pass, place the word on the board and calculate the total score
    //     return placeWord(w, newWords);
    // }
    
    // private int placeWord(Word w, ArrayList<Word> newWords) {
    //     // Place the new tiles on the board
    //     for (int i = 0; i < w.getTiles().length; i++) {
    //         int row = w.getRow() + (w.isVertical() ? i : 0);
    //         int col = w.getCol() + (w.isVertical() ? 0 : i);
    //         Tile newTile = w.getTiles()[i];
            
    //         // Place the new tile on the board if it's not already occupied
    //         if (boardTiles[row][col] == null) {
    //             boardTiles[row][col] = newTile;
    //             numOfTiles++;
    //         }
    //     }
        
    //     // Calculate the score for the word that was placed
    //     int wordScore = getScore(w, this);
        
    //     // Calculate the score for each new word created
    //     int totalScore = wordScore;
    //     for (Word newWord : newWords) {
    //         totalScore += getScore(newWord, this);
    //     }
        
    //     // Print the total score for debugging purposes
    //     System.out.println("The score for the word and new words created is: " + totalScore);
    
    //     return totalScore;
    // }
    
    public int getScore(Word w, Board board) {
        int score = 0;
        int multiplier = 1; // Multiplier so we need 1
    
        int row = w.getRow();
        int col = w.getCol();
    
        for (int i = 0; i < w.getTiles().length; i++) {
            Tile tile = w.getTiles()[i];
            int r = w.isVertical() ? row + i : row;
            int c = w.isVertical() ? col : col + i;
    
            // // Use the existing tile if the current tile is null
            // if (tile == null) {
            //     tile = board.boardTiles[r][c];
            // }
    
             // Check if the current position already has a tile on the board
            if (board.boardTiles[r][c] != null) {
                // Tile is already on the board, add its score only
                Tile existingTile = board.boardTiles[r][c];
                score += existingTile.score;
            } else {
                // Tile is new, add score with bonus
                if (tile != null) {
                    String bonus = board.score[r][c];
                    switch (bonus) {
                        case "Yellow":
                            // Double word score
                            multiplier *= 2;
                            score += tile.score;
                            break;
                        case "Red":
                            // Triple word score
                            multiplier *= 3;
                            score += tile.score;
                            break;
                        case "lightBlue":
                            // Double letter score
                            score += tile.score * 2;
                            break;
                        case "blue":
                            // Triple letter score
                            score += tile.score * 3;
                            break;
                        default:
                            score += tile.score;
                            break;
                    }
                }
            }
        }
        // Apply the word multiplier
        score *= multiplier;
    
        return score;
    }
    
    private void placeWord(Word w) {
        // Place the new tiles on the board
        for (int i = 0; i < w.getTiles().length; i++) {
            int row = w.getRow() + (w.isVertical() ? i : 0);
            int col = w.getCol() + (w.isVertical() ? 0 : i);
            Tile newTile = w.getTiles()[i];
    
            // Place the new tile on the board if it's not already occupied
            if (boardTiles[row][col] == null) {
                boardTiles[row][col] = newTile;
                numOfTiles++;
            }
        }
    
        
    }
    
    public int tryPlaceWord(Word w) {
        // Check if the word can be legally placed on the board
        if (!boardLegal(w)) {
            return 0;
        }

        // Calculate the score for the word and all new words created
        ArrayList<Word> newWords = getWords(w);
        int totalScore = 0;
        
        System.out.println("The score of the word is: " + getScore(w, this));

        for (Word newWord : newWords) {
            totalScore += getScore(newWord, this);
        }
    
        // Print the total score for debugging purposes
        System.out.println("The score for the word and new words created is: " + totalScore);
    
        // Place the word on the board and calculate the total score

        placeWord(w);

        return totalScore;
    
    }

    
    
}