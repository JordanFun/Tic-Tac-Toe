package main;

import java.util.Random;

public class TTTCPU {
    Board board;
    Boolean XTurn;

    // TTT Board that the CPU will be playing on and whether it marks tiles on X's turn or not
    public TTTCPU(Board board, Boolean XTurn) {
        this.board = board;
        this.XTurn = XTurn;
    }

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
}
