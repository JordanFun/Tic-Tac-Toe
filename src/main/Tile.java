package main;

public class Tile {
    private State state;

    public enum State {
        EMPTY, X, O
    }

    // EFFECTS: A tile on the Tic-Tac-Toe board with empty, X, or O state
    public Tile() {
        this.state = State.EMPTY;
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
