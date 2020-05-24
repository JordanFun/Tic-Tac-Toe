package main;

public class Board {
    Tile[][] board;

    public Board() {
        board = new Tile[3][3];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Tile();
            }
        }
    }

    public Tile getTile(int row, int column) {
        return board[row][column];
    }

}
