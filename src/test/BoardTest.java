package test;


import main.Board;
import main.Tile;
import main.Tile.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    Board board1;
    Tile tile1;

    @BeforeEach
    void runBefore() {
        board1 = new Board();
    }

    @Test
    void testGetter() {
        tile1 = board1.getTile(0,0);
        assertEquals(State.EMPTY, tile1.getState());
    }

    @Test
    void testGetGameIsOver() {
        assertFalse(board1.getGameIsOver());
    }

    @Test
    void testMark() {
        board1.getTile(1,0).setState(Tile.State.X);
        board1.getTile(2,0).setState(Tile.State.O);

        board1.markTile(0, 0);
        board1.markTile(1, 0);
        board1.markTile(2, 0);

        assertEquals(State.X, board1.getTile(0, 0).getState());
        assertEquals(State.X, board1.getTile(1, 0).getState());
        assertEquals(State.O, board1.getTile(2, 0).getState());
    }

    @Test
    void testMultipleMarks() {
        board1.markTile(0, 0);
        board1.markTile(1, 0);
        board1.markTile(2, 0);

        assertEquals(State.X, board1.getTile(0, 0).getState());
        assertEquals(State.O, board1.getTile(1, 0).getState());
        assertEquals(State.X, board1.getTile(2, 0).getState());
    }

    @Test
    void testWinDownwards() {
        assertFalse(board1.markTile(0, 0));
        assertFalse(board1.markTile(1, 0));
        assertFalse(board1.markTile(1, 1));
        assertFalse(board1.markTile(2, 0));
        assertTrue(board1.markTile(2, 2));
        assertFalse(board1.checkStraights(2, 2));
        assertTrue(board1.checkDiagonals(2, 2));
        assertTrue(board1.checkDownwardsDiagonal());
        assertTrue(board1.getGameIsOver());
    }

    @Test
    void testWinUpwards() {
        assertFalse(board1.markTile(0, 2));
        assertFalse(board1.markTile(1, 0));
        assertFalse(board1.markTile(1, 1));
        assertFalse(board1.markTile(2, 2));
        assertTrue(board1.markTile(2, 0));
        assertFalse(board1.checkStraights(2, 0));
        assertTrue(board1.checkDiagonals(2, 0));
        assertTrue(board1.checkUpwardsDiagonal());
    }
    @Test
    void testWinHorizontal() {
        assertFalse(board1.markTile(0, 0));
        assertFalse(board1.markTile(0, 1));
        assertFalse(board1.markTile(1, 0));
        assertFalse(board1.markTile(1, 1));
        assertTrue(board1.markTile(2, 0));
        assertTrue(board1.checkStraights(2, 0));
        assertFalse(board1.checkDiagonals(2, 0));
        assertTrue(board1.checkHorizontal(0));
    }

    @Test
    void testWinVertical() {
        assertFalse(board1.markTile(0, 0));
        assertFalse(board1.markTile(1, 0));
        assertFalse(board1.markTile(0, 1));
        assertFalse(board1.markTile(1, 1));
        assertTrue(board1.markTile(0, 2));
        assertTrue(board1.checkStraights(0, 2));
        assertFalse(board1.checkDiagonals(0, 2));
        assertTrue(board1.checkVertical(0));
    }

}
