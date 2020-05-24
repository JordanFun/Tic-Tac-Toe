package main;

public class Tile {
    private State state;

    public enum State {
        EMPTY, X, O
    }

    public Tile() {
        this.state = State.EMPTY;
    }

    public void setState(State state) {
        if (state == State.EMPTY) {
            this.state = State.EMPTY;
        } else if (state == State.X) {
            this.state = State.X;
        } else {
            this.state = State.O;
        }
    }

    public State getState() {
        return this.state;
    }

}
