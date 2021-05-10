package ui;

import main.Board;
import main.TTTCPU;
import main.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

// TicTacToe GUI representing a Tic-Tac-Toe board
public class TicTacToeGUI implements ActionListener {

    private Board board;
    private TTTCPU cpu;
    private JLabel[][] states;

    private Boolean vsCPU;
    private Boolean vsRandom;
    private Boolean cpuFirst;

    private JFrame frame;
    private JPanel backgroundColor;

    private JLabel startText;
    private JLabel winText;

    private JButton playAgain;
    private JButton playLocal;
    private JButton playCPU;
    private JButton playRandom;
    private JButton playImpossible;


    private static int FRAME_WIDTH = 1600;
    private static int FRAME_HEIGHT = 900;
    private static int LINE_WIDTH = 10;
    private static int LINE_HEIGHT = FRAME_WIDTH / 2;
    private static int TILE_WIDTH = ((LINE_HEIGHT) - (2 * LINE_WIDTH)) / 3;

    // MODIFIES: this
    // EFFECTS: Starts up the GUI JFrame and the elements inside of it
    public TicTacToeGUI() {
        Random random = new Random();
        cpuFirst = random.nextBoolean();

        board = new Board();
        cpu = new TTTCPU(board, cpuFirst);
        states = new JLabel[3][3];

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

        startText = new JLabel("Select your mode:", JLabel.CENTER);
        startText.setBounds((FRAME_WIDTH / 2) - (TILE_WIDTH / 2),
                (FRAME_HEIGHT / 2) - (3 * TILE_WIDTH / 4),
                TILE_WIDTH,
                TILE_WIDTH);
        startText.setForeground(Color.white);
        startText.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        startText.setVisible(true);
        backgroundColor.add(startText);

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

        playCPU = new JButton();
        playCPU.addActionListener(this);
        playCPU.setText("Play CPU");
        playCPU.setBounds((FRAME_WIDTH / 2) - 150,
                (FRAME_HEIGHT / 2),
                100,
                50);
        playCPU.setVisible(true);
        backgroundColor.add(playCPU);

        playRandom = new JButton();
        playRandom.addActionListener(this);
        playRandom.setText("Easy");
        playRandom.setBounds((FRAME_WIDTH / 2) - 150,
                (FRAME_HEIGHT / 2),
                100,
                50);
        playRandom.setVisible(false);
        backgroundColor.add(playRandom);

        playImpossible = new JButton();
        playImpossible.addActionListener(this);
        playImpossible.setText("Hard");
        playImpossible.setBounds((FRAME_WIDTH / 2) + 50,
                (FRAME_HEIGHT / 2),
                100,
                50);
        playImpossible.setVisible(false);
        backgroundColor.add(playImpossible);

        playLocal = new JButton();
        playLocal.addActionListener(this);
        playLocal.setText("Play Local");
        playLocal.setBounds((FRAME_WIDTH / 2) + 50,
                (FRAME_HEIGHT / 2),
                100,
                50);
        playLocal.setVisible(true);
        backgroundColor.add(playLocal);

        createStates();
        createLines();
    }

    // EFFECTS: creates JLabels for each of the tiles that can be marked X, O, or EMPTY
    private void createStates() {
        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < states.length; j++) {
                JLabel state = new JLabel("", JLabel.CENTER);
                state.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2) + (i * TILE_WIDTH) + (i * LINE_WIDTH),
                        ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2) + (j * TILE_WIDTH) + (j * LINE_WIDTH),
                        TILE_WIDTH,
                        TILE_WIDTH);
                state.setFont(new Font("Comic Sans", Font.PLAIN, 250));

                states[i][j] = state;
            }
        }
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

    // EFFECTS: marks the board at (column, row) then lets the CPU move
    private void markBoard(int column, int row) {
        if (!board.getGameIsOver()) {
            if (board.markTile(column,row)) {
                endGame();
            } else if (board.isBoardFull()) {
                winText.setText("Draw!");
                playAgain.setVisible(true);
            }
            if (board.getTile(column, row).getState() == Tile.State.X) {
                states[column][row].setForeground(Color.blue);
                states[column][row].setText("X");
            } else if (board.getTile(column, row).getState() == Tile.State.O) {
                states[column][row].setForeground(Color.red);
                states[column][row].setText("O");
            }
        }
        CPUmark();
    }

    // EFFECTS: if player chooses to VS CPU, marks tile based on CPU difficulty
    // else do nothing
    private void CPUmark() {
        if (vsCPU && (!board.getGameIsOver() && !board.isBoardFull())) {
            int[] coords;
            if (vsRandom) {
                coords = cpu.markRandom();
            } else {
                coords = cpu.markSmart();
            }
            int column = coords[0];
            int row = coords[1];
            //System.out.println("(" + randomColumn + ", " + randomRow + ")");
            if (board.getGameIsOver()) {
                endGame();
            } else if (board.isBoardFull()) {
                winText.setText("Draw!");
                playAgain.setVisible(true);
            }
            if (board.getTile(column, row).getState() == Tile.State.X) {
                states[column][row].setForeground(Color.blue);
                states[column][row].setText("X");
            } else if (board.getTile(column, row).getState() == Tile.State.O) {
                states[column][row].setForeground(Color.red);
                states[column][row].setText("O");
            }
        }
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

        topLeftTile.add(states[0][0]);
        states[0][0].setVisible(true);

        topLeftTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                markBoard(0, 0);

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

        middleLeftTile.add(states[0][1]);
        states[0][1].setVisible(true);

        middleLeftTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                markBoard(0, 1);
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

        bottomLeftTile.add(states[0][2]);
        states[0][2].setVisible(true);

        bottomLeftTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                markBoard(0, 2);

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

        topMiddleTile.add(states[1][0]);
        states[1][0].setVisible(true);

        topMiddleTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                markBoard(1, 0);

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

        centerTile.add(states[1][1]);
        states[1][1].setVisible(true);

        centerTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                markBoard(1, 1);

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

        bottomMiddleTile.add(states[1][2]);
        states[1][2].setVisible(true);

        bottomMiddleTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                markBoard(1, 2);

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

        topRightTile.add(states[2][0]);
        states[2][0].setVisible(true);

        topRightTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                markBoard(2, 0);

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

        middleRightTile.add(states[2][1]);
        states[2][1].setVisible(true);

        middleRightTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                markBoard(2, 1);

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

        bottomRightTile.add(states[2][2]);
        states[2][2].setVisible(true);

        bottomRightTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                markBoard(2, 2);

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
            frame.dispose();
            new TicTacToeGUI();
        } else if (e.getSource() == playCPU) {
            vsCPU = true;
            playCPU.setVisible(false);
            playLocal.setVisible(false);
            startText.setText("Choose your difficulty");
            playImpossible.setVisible(true);
            playRandom.setVisible(true);
        } else if (e.getSource() == playLocal) {
            vsCPU = false;
            playCPU.setVisible(false);
            playLocal.setVisible(false);
            startText.setVisible(false);
            setTiles();
        } else if (e.getSource() == playImpossible) {
            vsRandom = false;
            startText.setVisible(false);
            playImpossible.setVisible(false);
            playRandom.setVisible(false);
            setTiles();
            if (cpuFirst) {
                CPUmark();
            }
        } else if (e.getSource() == playRandom) {
            vsRandom = true;
            startText.setVisible(false);
            playImpossible.setVisible(false);
            playRandom.setVisible(false);
            setTiles();
            if (cpuFirst) {
                CPUmark();
            }
        }
    }
}
