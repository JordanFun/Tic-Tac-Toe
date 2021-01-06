package ui;

import main.Board;
import main.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// TicTacToe GUI representing a Tic-Tac-Toe board
public class TicTacToeGUI implements ActionListener {

    private Board board;

    private static int FRAME_WIDTH = 1600;
    private static int FRAME_HEIGHT = 900;
    private JFrame frame;
    private JPanel backgroundColor;
    private JLabel winText;
    private JButton playAgain;
    private static int LINE_WIDTH = 10;
    private static int LINE_HEIGHT = FRAME_WIDTH / 2;
    private static int TILE_WIDTH = ((LINE_HEIGHT) - (2 * LINE_WIDTH)) / 3;

    // MODIFIES: this
    // EFFECTS: Starts up the GUI JFrame and the elements inside of it
    public TicTacToeGUI() {
        board = new Board();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tic-tac-toe");

        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.pack();
        frame.setLocationRelativeTo(null);

        setUpBoard();

        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the lines of the board and the tiles ready to be marked
    private void setUpBoard() {
        backgroundColor = new JPanel();
        backgroundColor.setLayout(null);
        backgroundColor.setBackground(Color.darkGray);
        backgroundColor.setVisible(true);
        frame.add(backgroundColor);

        winText = new JLabel("", JLabel.CENTER);
        winText.setBounds((FRAME_WIDTH / 2) - (TILE_WIDTH / 2),
                (FRAME_HEIGHT / 2) - (3 * TILE_WIDTH / 4),
                TILE_WIDTH,
                TILE_WIDTH);
        winText.setForeground(Color.white);
        winText.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        winText.setVisible(true);
        backgroundColor.add(winText);

        playAgain = new JButton();
        playAgain.addActionListener(this);
        playAgain.setText("Play again");
        playAgain.setBounds((FRAME_WIDTH / 2) - 50,
                (FRAME_HEIGHT / 2),
                100,
                50);
        playAgain.setVisible(false);
        backgroundColor.add(playAgain);

        createLines();
        setTiles();
    }

    // EFFECTS: Creates the lines on the Tic-Tac-Toe board
    private void createLines() {
        createTopHorizontalLine();
        createBottomHorizontalLine();
        createLeftVerticalLine();
        createRightVerticalLine();
    }

    // MODIFIES: this
    // EFFECTS: Creates a JPanel line that is TILE_WIDTH apart from the line parallel to it
    private void createTopHorizontalLine() {
        JPanel topHorizontalLine = new JPanel();
        topHorizontalLine.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2),
                (FRAME_HEIGHT / 3) + (LINE_WIDTH / 2),
                LINE_HEIGHT,
                LINE_WIDTH);
        topHorizontalLine.setLayout(new BorderLayout());
        topHorizontalLine.setBackground(Color.white);

        backgroundColor.add(topHorizontalLine);
        topHorizontalLine.setVisible(true);
    }

    private void createBottomHorizontalLine() {
        JPanel bottomHorizontalLine = new JPanel();
        bottomHorizontalLine.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2),
                (FRAME_HEIGHT / 3) + (LINE_WIDTH / 2) + TILE_WIDTH + LINE_WIDTH,
                LINE_HEIGHT,
                LINE_WIDTH);
        bottomHorizontalLine.setLayout(new BorderLayout());
        bottomHorizontalLine.setBackground(Color.white);

        backgroundColor.add(bottomHorizontalLine);
        bottomHorizontalLine.setVisible(true);
    }

    private void createLeftVerticalLine() {
        JPanel leftVerticalLine = new JPanel();
        leftVerticalLine.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2) + TILE_WIDTH,
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2),
                LINE_WIDTH,
                LINE_HEIGHT);
        leftVerticalLine.setLayout(new BorderLayout());
        leftVerticalLine.setBackground(Color.white);

        backgroundColor.add(leftVerticalLine);
        leftVerticalLine.setVisible(true);
    }

    private void createRightVerticalLine() {
        JPanel rightVerticalLine = new JPanel();
        rightVerticalLine.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2) + (2 * TILE_WIDTH) + LINE_WIDTH,
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2),
                LINE_WIDTH,
                LINE_HEIGHT);
        rightVerticalLine.setLayout(new BorderLayout());
        rightVerticalLine.setBackground(Color.white);

        backgroundColor.add(rightVerticalLine);
        rightVerticalLine.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets the 9 tiles on the 3x3 grid on the Tic-Tac-Toe board
    private void setTiles() {
        setTopLeftTile();
        setMiddleLeftTile();
        setBottomLeftTile();

        setTopMiddleTile();
        setCenterTile();
        setBottomMiddleTile();

        setTopRightTile();
        setMiddleRightTile();
        setBottomRightTile();

    }

    // MODIFIES: this
    // EFFECTS: Creates a new panel that gets added onto the frame, when clicked the label shows either X or O
    // depending on which turn the board was on
    private void setTopLeftTile() {
        JPanel topLeftTile = new JPanel();

        topLeftTile.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2),
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2),
                TILE_WIDTH,
                TILE_WIDTH);

        topLeftTile.setLayout(new BorderLayout());
        topLeftTile.setBackground(Color.darkGray);

        JLabel state = new JLabel("", JLabel.CENTER);
        state.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2),
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2),
                TILE_WIDTH,
                TILE_WIDTH);
        state.setFont(new Font("Comic Sans", Font.PLAIN, 250));
        topLeftTile.add(state);
        state.setVisible(true);

        topLeftTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    if (board.markTile(0,0)) {
                        endGame();
                    } else if (board.isBoardFull()) {
                        winText.setText("Draw!");
                        playAgain.setVisible(true);
                    }
                    if (board.getTile(0, 0).getState() == Tile.State.X) {
                        state.setForeground(Color.blue);
                        state.setText("X");
                    } else if (board.getTile(0, 0).getState() == Tile.State.O) {
                        state.setForeground(Color.red);
                        state.setText("O");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        backgroundColor.add(topLeftTile);
        topLeftTile.setVisible(true);
    }

    private void setMiddleLeftTile() {
        JPanel middleLeftTile = new JPanel();
        middleLeftTile.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2),
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2) + TILE_WIDTH + LINE_WIDTH,
                TILE_WIDTH,
                TILE_WIDTH);

        middleLeftTile.setLayout(new BorderLayout());
        middleLeftTile.setBackground(Color.darkGray);

        JLabel state = new JLabel("", JLabel.CENTER);
        state.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2),
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2) + TILE_WIDTH + LINE_WIDTH,
                TILE_WIDTH,
                TILE_WIDTH);
        state.setFont(new Font("Comic Sans", Font.PLAIN, 250));
        middleLeftTile.add(state);
        state.setVisible(true);

        middleLeftTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    if (board.markTile(0,1)) {
                        endGame();
                    } else if (board.isBoardFull()) {
                        winText.setText("Draw!");
                        playAgain.setVisible(true);
                    }
                    if (board.getTile(0, 1).getState() == Tile.State.X) {
                        state.setForeground(Color.blue);
                        state.setText("X");
                    } else if (board.getTile(0, 1).getState() == Tile.State.O) {
                        state.setForeground(Color.red);
                        state.setText("O");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });



        backgroundColor.add(middleLeftTile);
        middleLeftTile.setVisible(true);
    }

    private void setBottomLeftTile() {
        JPanel bottomLeftTile = new JPanel();
        bottomLeftTile.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2),
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2) + (2 * TILE_WIDTH) + (2 * LINE_WIDTH),
                TILE_WIDTH,
                TILE_WIDTH);

        bottomLeftTile.setLayout(new BorderLayout());
        bottomLeftTile.setBackground(Color.darkGray);

        JLabel state = new JLabel("", JLabel.CENTER);
        state.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2),
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2) + (2 * TILE_WIDTH) + (2 * LINE_WIDTH),
                TILE_WIDTH,
                TILE_WIDTH);
        state.setFont(new Font("Comic Sans", Font.PLAIN, 250));
        bottomLeftTile.add(state);
        state.setVisible(true);

        bottomLeftTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    if (board.markTile(0,2)) {
                        endGame();
                    } else if (board.isBoardFull()) {
                        winText.setText("Draw!");
                        playAgain.setVisible(true);
                    }
                    if (board.getTile(0, 2).getState() == Tile.State.X) {
                        state.setForeground(Color.blue);
                        state.setText("X");
                    } else if (board.getTile(0, 2).getState() == Tile.State.O) {
                        state.setForeground(Color.red);
                        state.setText("O");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        backgroundColor.add(bottomLeftTile);
        bottomLeftTile.setVisible(true);
    }

    private void setTopMiddleTile() {
        JPanel topMiddleTile = new JPanel();
        topMiddleTile.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2) + TILE_WIDTH + LINE_WIDTH,
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2),
                TILE_WIDTH,
                TILE_WIDTH);

        topMiddleTile.setLayout(new BorderLayout());
        topMiddleTile.setBackground(Color.darkGray);

        JLabel state = new JLabel("", JLabel.CENTER);
        state.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2) + TILE_WIDTH + LINE_WIDTH,
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2),
                TILE_WIDTH,
                TILE_WIDTH);
        state.setFont(new Font("Comic Sans", Font.PLAIN, 250));
        topMiddleTile.add(state);
        state.setVisible(true);

        topMiddleTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    if (board.markTile(1,0)) {
                        endGame();
                    } else if (board.isBoardFull()) {
                        winText.setText("Draw!");
                        playAgain.setVisible(true);
                    }
                    if (board.getTile(1, 0).getState() == Tile.State.X) {
                        state.setForeground(Color.blue);
                        state.setText("X");
                    } else if (board.getTile(1, 0).getState() == Tile.State.O) {
                        state.setForeground(Color.red);
                        state.setText("O");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        backgroundColor.add(topMiddleTile);
        topMiddleTile.setVisible(true);
    }

    private void setCenterTile() {
        JPanel centerTile = new JPanel();
        centerTile.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2) + TILE_WIDTH + LINE_WIDTH,
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2) + TILE_WIDTH + LINE_WIDTH,
                TILE_WIDTH,
                TILE_WIDTH);

        centerTile.setLayout(new BorderLayout());
        centerTile.setBackground(Color.darkGray);

        JLabel state = new JLabel("", JLabel.CENTER);
        state.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2) + TILE_WIDTH + LINE_WIDTH,
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2) + TILE_WIDTH + LINE_WIDTH,
                TILE_WIDTH,
                TILE_WIDTH);
        state.setFont(new Font("Comic Sans", Font.PLAIN, 250));
        centerTile.add(state);
        state.setVisible(true);

        centerTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    if (board.markTile(1,1)) {
                        endGame();
                    } else if (board.isBoardFull()) {
                        winText.setText("Draw!");
                        playAgain.setVisible(true);
                    }
                    if (board.getTile(1, 1).getState() == Tile.State.X) {
                        state.setForeground(Color.blue);
                        state.setText("X");
                    } else if (board.getTile(1, 1).getState() == Tile.State.O) {
                        state.setForeground(Color.red);
                        state.setText("O");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        backgroundColor.add(centerTile);
        centerTile.setVisible(true);
    }

    private void setBottomMiddleTile() {
        JPanel bottomMiddleTile = new JPanel();
        bottomMiddleTile.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2) + TILE_WIDTH + LINE_WIDTH,
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2) + (2 * TILE_WIDTH) + (2 * LINE_WIDTH),
                TILE_WIDTH,
                TILE_WIDTH);

        bottomMiddleTile.setLayout(new BorderLayout());
        bottomMiddleTile.setBackground(Color.darkGray);

        JLabel state = new JLabel("", JLabel.CENTER);
        state.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2) + TILE_WIDTH + LINE_WIDTH,
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2) + (2 * TILE_WIDTH) + (2 * LINE_WIDTH),
                TILE_WIDTH,
                TILE_WIDTH);
        state.setFont(new Font("Comic Sans", Font.PLAIN, 250));
        bottomMiddleTile.add(state);
        state.setVisible(true);

        bottomMiddleTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    if (board.markTile(1,2)) {
                        endGame();
                    } else if (board.isBoardFull()) {
                        winText.setText("Draw!");
                        playAgain.setVisible(true);
                    }
                    if (board.getTile(1, 2).getState() == Tile.State.X) {
                        state.setForeground(Color.blue);
                        state.setText("X");
                    } else if (board.getTile(1, 2).getState() == Tile.State.O) {
                        state.setForeground(Color.red);
                        state.setText("O");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        backgroundColor.add(bottomMiddleTile);
        bottomMiddleTile.setVisible(true);
    }

    private void setTopRightTile() {
        JPanel topRightTile = new JPanel();
        topRightTile.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2) + (2 * TILE_WIDTH) + (2 * LINE_WIDTH),
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2),
                TILE_WIDTH,
                TILE_WIDTH);

        topRightTile.setLayout(new BorderLayout());
        topRightTile.setBackground(Color.darkGray);

        JLabel state = new JLabel("", JLabel.CENTER);
        state.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2) + (2 * TILE_WIDTH) + (2 * LINE_WIDTH),
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2),
                TILE_WIDTH,
                TILE_WIDTH);
        state.setFont(new Font("Comic Sans", Font.PLAIN, 250));
        topRightTile.add(state);
        state.setVisible(true);

        topRightTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    if (board.markTile(2,0)) {
                        endGame();
                    } else if (board.isBoardFull()) {
                        winText.setText("Draw!");
                        playAgain.setVisible(true);
                    }
                    if (board.getTile(2, 0).getState() == Tile.State.X) {
                        state.setForeground(Color.blue);
                        state.setText("X");
                    } else if (board.getTile(2, 0).getState() == Tile.State.O) {
                        state.setForeground(Color.red);
                        state.setText("O");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        backgroundColor.add(topRightTile);
        topRightTile.setVisible(true);
    }

    private void setMiddleRightTile() {
        JPanel middleRightTile = new JPanel();
        middleRightTile.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2) + (2 * TILE_WIDTH) + (2 * LINE_WIDTH),
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2) + TILE_WIDTH + LINE_WIDTH,
                TILE_WIDTH,
                TILE_WIDTH);

        middleRightTile.setLayout(new BorderLayout());
        middleRightTile.setBackground(Color.darkGray);

        JLabel state = new JLabel("", JLabel.CENTER);
        state.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2) + (2 * TILE_WIDTH) + (2 * LINE_WIDTH),
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2) + TILE_WIDTH + LINE_WIDTH,
                TILE_WIDTH,
                TILE_WIDTH);
        state.setFont(new Font("Comic Sans", Font.PLAIN, 250));
        middleRightTile.add(state);
        state.setVisible(true);

        middleRightTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    if (board.markTile(2,1)) {
                        endGame();
                    } else if (board.isBoardFull()) {
                        winText.setText("Draw!");
                        playAgain.setVisible(true);
                    }
                    if (board.getTile(2, 1).getState() == Tile.State.X) {
                        state.setForeground(Color.blue);
                        state.setText("X");
                    } else if (board.getTile(2, 1).getState() == Tile.State.O) {
                        state.setForeground(Color.red);
                        state.setText("O");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        backgroundColor.add(middleRightTile);
        middleRightTile.setVisible(true);
    }

    private void setBottomRightTile() {
        JPanel bottomRightTile = new JPanel();
        bottomRightTile.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2) + (2 * TILE_WIDTH) + (2 * LINE_WIDTH),
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2) + (2 * TILE_WIDTH) + (2 * LINE_WIDTH),
                TILE_WIDTH,
                TILE_WIDTH);

        bottomRightTile.setLayout(new BorderLayout());
        bottomRightTile.setBackground(Color.darkGray);

        JLabel state = new JLabel("", JLabel.CENTER);
        state.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2) + (2 * TILE_WIDTH) + (2 * LINE_WIDTH),
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2) + (2 * TILE_WIDTH) + (2 * LINE_WIDTH),
                TILE_WIDTH,
                TILE_WIDTH);
        state.setFont(new Font("Comic Sans", Font.PLAIN, 250));
        bottomRightTile.add(state);
        state.setVisible(true);

        bottomRightTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    if (board.markTile(2,2)) {
                        endGame();
                    } else if (board.isBoardFull()) {
                        winText.setText("Draw!");
                        playAgain.setVisible(true);
                    }
                    if (board.getTile(2, 2).getState() == Tile.State.X) {
                        state.setForeground(Color.blue);
                        state.setText("X");
                    } else if (board.getTile(2, 2).getState() == Tile.State.O) {
                        state.setForeground(Color.red);
                        state.setText("O");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        backgroundColor.add(bottomRightTile);
        bottomRightTile.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: When a side wins, display winner and Play Again button
    private void endGame() {
        if (board.getXTurn()) {
            winText.setText("O wins!");
        } else {
            winText.setText("X wins!");
        }
        playAgain.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Restarts the game if Play Again button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playAgain) {
            new TicTacToeGUI();
        }
    }
}
