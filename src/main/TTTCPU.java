package main;

import java.util.Random;

public class TTTCPU {
    Board board;
    Boolean XTurn;
    Tile.State mark;

    private static int MAX_SCORE = 10;

    private static int INITIAL_CENTER_SCORE = 4;
    private static int INITIAL_CORNER_SCORE = 3;
    private static int INITIAL_SIDE_SCORE = 2;

    private static int BLOCK_SCORE_INCREMENT = 5;
    private static int SCORE_INCREMENT = 1;

    // TTT Board that the CPU will be playing on and whether it marks tiles on X's turn or not
    public TTTCPU(Board board, Boolean first) {
        this.board = board;
        if (first) {
            this.XTurn = board.getXTurn();
        } else {
            this.XTurn = !board.getXTurn();
        }

        if (XTurn) {
            mark = Tile.State.X;
        } else {
            mark = Tile.State.O;
        }
        System.out.println(mark);
    }

    // REQUIRES: game is not over
    // MODIFIES: this
    // EFFECTS: Marks a random tile on the board and returns the position of that marked tile
    public int[] markRandom() {
        Random rand = new Random();
        int upperBound = 3;

        while(true) {
            int randomColumn = rand.nextInt(upperBound);
            int randomRow = rand.nextInt(upperBound);

            if (board.getTile(randomColumn, randomRow).getState() == Tile.State.EMPTY) {
                board.markTile(randomColumn, randomRow);
                int[] tile = new int[2];
                tile[0] = randomColumn;
                tile[1] = randomRow;
                return tile;
            }
        }
    }

    // REQUIRES: game is not over
    // MODIFIES: this
    // EFFECTS: Marks a tile using an algorithm and returns the tile position
    public int[] markSmart() {
        int[] tile = findBestMove();

        board.markTile(tile[0], tile[1]);
        return tile;
    }

    // EFFECTS: Finds the best move out of the 9 tiles on the board and returns its position
    private int[] findBestMove() {
        int[] resultTile = new int[2];
        int currTileScore = 0;
        int newScore = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newScore = getTileScore(i, j);
                System.out.println(newScore);
                System.out.println("--------End of Tile--------");
                if (newScore > currTileScore) {
                    currTileScore = getTileScore(i, j);

                    resultTile[0] = i;
                    resultTile[1] = j;
                }
            }
        }
        System.out.println("-----------------End of Turn-----------------");
        return resultTile;
    }

    // EFFECTS: return's the tile's value if the CPU decides to mark here
    private int getTileScore(int column, int row) {
        if (board.getTile(column, row).getState() != Tile.State.EMPTY) {
            return 0;
        } else if (column == 1 && row == 1) {
            return getCenterScore(column, row);
        } else if (column == 1 || row == 1) {
            return getSideScore(column, row);
        } else {
            return getCornerScore(column, row);
        }
    }

    // EFFECTS: Gets the score for marking the center tile
    // should do this first or second turn always as it is the best move
    private int getCenterScore(int column, int row) {
        return INITIAL_CENTER_SCORE + MAX_SCORE;
    }

    // EFFECTS: Gets the score for marking a corner tile
    // vertical score + horizontal score + diagonal score
    // REQUIRES: corner tile coordinates given
    private int getCornerScore(int column, int row) {
        int additionalScore = getVerticalScore(column, row) + getHorizontalScore(column, row);

        if (column == row) {
            additionalScore += getDownwardsDiagonalScore(column, row);
        } else {
            additionalScore += getUpwardsDiagonalScore(column, row);
        }

        return INITIAL_CORNER_SCORE + additionalScore;
    }

    // EFFECTS: Gets the score for marking a side tile
    // vertical score + horizontal score
    // REQUIRES: side tile coordinates given
    private int getSideScore(int column, int row) {
        int additionalScore = getVerticalScore(column, row) + getHorizontalScore(column, row);

        return INITIAL_SIDE_SCORE + additionalScore;
    }

    // EFFECTS: Gets the horizontal score for marking a tile
    private int getHorizontalScore(int column, int row) {
        Tile[] otherTiles = new Tile[2];

        int j = 0;
        for (int i = 0; i < 3; i++) {
            if (i != column) {
                otherTiles[j] = board.getTile(i, row);
                j++;
            }
        }

        Tile tile1 = otherTiles[0];
        Tile tile2 = otherTiles[1];

        int score = calculateScore(tile1, tile2);

        System.out.println("(" + tile1.getColumn() + ", " + tile1.getRow() + ") " +
                "(" + tile2.getColumn() + ", " + tile2.getRow() + ") " + "Score: " + score);


        return score;
    }

    // EFFECTS: Gets the vertical score for marking a tile
    private int getVerticalScore(int column, int row) {
        Tile[] otherTiles = new Tile[2];

        int j = 0;
        for (int i = 0; i < 3; i++) {
            if (i != row) {
                otherTiles[j] = board.getTile(column, i);
                j++;
            }
        }

        Tile tile1 = otherTiles[0];
        Tile tile2 = otherTiles[1];

        int score = calculateScore(tile1, tile2);

        System.out.println("(" + tile1.getColumn() + ", " + tile1.getRow() + ") " +
                "(" + tile2.getColumn() + ", " + tile2.getRow() + ") " + "Score: " + score);


        return score;
    }

    // EFFECTS: Gets the downward diagonal score for marking a tile
    private int getDownwardsDiagonalScore(int column, int row) {
        Tile[] otherTiles = new Tile[2];

        int j = 0;
        for (int i = 0; i < 3; i++) {
            if (i != row) {
                otherTiles[j] = board.getTile(i, i);
                j++;
            }
        }

        Tile tile1 = otherTiles[0];
        Tile tile2 = otherTiles[1];

        int score = calculateScore(tile1, tile2);

        System.out.println("(" + tile1.getColumn() + ", " + tile1.getRow() + ") " +
                "(" + tile2.getColumn() + ", " + tile2.getRow() + ") " + "Score: " + score);


        return score;
    }

    // EFFECTS: Gets the upward diagonal score for marking a tile
    private int getUpwardsDiagonalScore(int column, int row) {
        Tile[] otherTiles = new Tile[2];

        int j = 0;
        int k = 2;
        for (int i = 0; i < 3; i++) {
            if (i != row) {
                otherTiles[j] = board.getTile(k, i);
                j++;
            }
            k--;
        }

        Tile tile1 = otherTiles[0];
        Tile tile2 = otherTiles[1];

        int score = calculateScore(tile1, tile2);

        System.out.println("(" + tile1.getColumn() + ", " + tile1.getRow() + ") " +
                "(" + tile2.getColumn() + ", " + tile2.getRow() + ") " + "Score: " + score);


        return score;
    }

    // EFFECTS: calculates the score based on constants
    // Takes two tiles that are in the same row/diagonal/column as the tile of interest
    // Returns a score based on how valuable it would be to mark this tile
    private int calculateScore(Tile tile1, Tile tile2) {
        if (tile1.getState() == Tile.State.EMPTY && tile2.getState() == Tile.State.EMPTY) {
            // both tiles are empty
            // marking this tile provides no additional value past the inital marking value
            return 0;
        } else if (tile1.getState() != Tile.State.EMPTY && tile2.getState() != Tile.State.EMPTY) {
            if (tile1.getState() == tile2.getState()) {
                // both tiles are the same mark
                // can either: block or win
                if (tile1.getState() == mark) {
                    // both tiles are your mark
                    // marking this tile will win the game
                    return MAX_SCORE;
                } else {
                    // both tiles are the opponent's mark
                    // marking this tile will stop opponent's win next turn
                    return BLOCK_SCORE_INCREMENT;
                }
            } else {
                // tiles are one of each mark
                // "dead" column as neither player can win off of this row
                return 0;
            }
        } else {
            // tiles are one empty, one marked
            // marking this tile will either:
            // 1. make this a dead column
            // 2. make two tiles in this column your mark, forcing the opponent to mark the third tile
            return SCORE_INCREMENT;
        }
    }

}
