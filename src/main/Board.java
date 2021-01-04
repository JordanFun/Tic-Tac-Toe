package main;

public class Board {
    Tile[][] board;
    Boolean XTurn;
    Boolean gameIsOver;

    // EFFECTS: A 3x3 board of tiles representing a Tic-Tac-Toe board
    public Board() {
        board = new Tile[3][3];
        XTurn = true;
        gameIsOver = false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Tile();
            }
        }
    }

    // EFFECTS: returns tile at (column, row)
    public Tile getTile(int column, int row) {
        return board[column][row];
    }

    // EFFECTS: returns if the game is over or not
    public Boolean getGameIsOver() {
        return gameIsOver;
    }

    // MODIFIES: this
    // EFFECTS: Marks a tile with X or O depending on who's turn it is, then switches turns
    public Boolean markTile(int column, int row) {
        if ((row <= 2 && row >= 0 && column <= 2 && column >= 0) || !gameIsOver) {
            Tile currTile = getTile(column, row);
            if (currTile.getState() == Tile.State.EMPTY) {
                if (XTurn) {
                    currTile.setState(Tile.State.X);
                } else {
                    currTile.setState(Tile.State.O);
                }
                XTurn = !XTurn;
            }
            gameIsOver = checkWin(column, row);
            return gameIsOver;
        }
        return false;
    }

    // REQUIRES: 0 <= column <= 2 and 0 <= row <= 2
    // EFFECTS: Checks if there is a win at (column, row)
    public Boolean checkWin(int column, int row) {
        Boolean diagonals = false;
        Boolean straights = checkStraights(column, row);

        // if tile is in a corner or tile is in the center check diagonals for a win
        if ((column != 1 && row != 1) || ((column == 1 && row == 1))) {
            diagonals = checkDiagonals(column, row);
        }

        return diagonals || straights;
    }

    // REQUIRES: 0 <= column <= 2 and 0 <= row <= 2
    // EFFECTS: Checks if there is a win in horizontal or vertical directions at (column, row)
    public Boolean checkStraights(int column, int row) {
        return checkHorizontal(row) || checkVertical(column);
    }

    // REQUIRES: 0 <= row <= 2
    // EFFECTS: Checks if tiles in a row (horizontally) are all of the same mark
    public Boolean checkHorizontal(int row) {
        Tile tile1 = getTile(0, row);
        Tile tile2 = getTile(1, row);
        Tile tile3 = getTile(2, row);

        if (tile1.getState() != Tile.State.EMPTY) {
            return tile1.getState() == tile2.getState() && tile2.getState() == tile3.getState();
        }
        return false;
    }

    // REQUIRES: 0 <= column <= 2
    // EFFECTS: Checks if tiles in a column (vertically) are all of the same mark
    public Boolean checkVertical(int column) {
        Tile tile1 = getTile(column, 0);
        Tile tile2 = getTile(column, 1);
        Tile tile3 = getTile(column, 2);

        if (tile1.getState() != Tile.State.EMPTY) {
            return tile1.getState() == tile2.getState() && tile2.getState() == tile3.getState();
        }
        return false;
    }

    // REQUIRES: 0 <= column <= 2 and 0 <= row <= 2
    // EFFECTS: Checks if there is a win in diagonals
    public Boolean checkDiagonals(int column, int row) {
        Boolean downwards = false;
        Boolean upwards = false;

        if (column != row) {
            upwards = checkUpwardsDiagonal();
        } else if (column == 1) {
            upwards = checkUpwardsDiagonal();
            downwards = checkDownwardsDiagonal();
        } else {
            downwards = checkDownwardsDiagonal();
        }
        return downwards || upwards;
    }
    
    // EFFECTS: Checks if the downwards ( \ ) diagonal is a win
    public Boolean checkDownwardsDiagonal() {
        Tile tile1 = getTile(0, 0);
        Tile tile2 = getTile(1, 1);
        Tile tile3 = getTile(2, 2);

        if (tile1.getState() != Tile.State.EMPTY) {
            return tile1.getState() == tile2.getState() && tile2.getState() == tile3.getState();
        }
        return false;
    }

    // EFFECTS: Checks if the upwards ( / ) diagonal is a win
    public Boolean checkUpwardsDiagonal() {
        Tile tile1 = getTile(2, 0);
        Tile tile2 = getTile(1, 1);
        Tile tile3 = getTile(0, 2);

        if (tile1.getState() != Tile.State.EMPTY) {
            return tile1.getState() == tile2.getState() && tile2.getState() == tile3.getState();
        }
        return false;
    }
}
