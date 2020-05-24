package test;


import main.Board;
import main.Tile;
import main.Tile.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    Board board1;

    @BeforeEach
    void runBefore() {
        board1 = new Board();
    }

    @Test
    void testGetter() {
        Tile tile1 = board1.getTile(0,0);
        assertEquals(State.EMPTY, tile1.getState());
    }

}
