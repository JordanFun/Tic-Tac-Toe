package test;

import main.Tile;
import main.Tile.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    Tile tile1;

    @BeforeEach
    void runBefore() {
        tile1 = new Tile(0, 0);
    }

    @Test
    void testGetter() {
        assertEquals(Tile.State.EMPTY, tile1.getState());
    }

    @Test
    void testSetter() {
        tile1.setState(State.X);
        assertEquals(Tile.State.X, tile1.getState());
    }
}
