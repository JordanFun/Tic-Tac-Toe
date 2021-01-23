package main;

public class Tile {
    private State state;
    private int column;
    private int row;

    public enum State {
        EMPTY, X, O
    }

    // EFFECTS: A tile on the Tic-Tac-Toe board with empty, X, or O state
    public Tile(int column, int row) {
        this.state = State.EMPTY;
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    public int getRow() {
        return this.row;
    }

    // EFFECTS: Sets the state of the tile
    public void setState(State state) {
        if (state == State.EMPTY) {
            this.state = State.EMPTY;
        } else if (state == State.X) {
            this.state = State.X;
        } else {
            this.state = State.O;
        }
    }

    // EFFECTS: Returns current state of the tile
    public State getState() {
        return this.state;
    }

}
