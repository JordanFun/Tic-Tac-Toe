package ui;

import main.Board;
import main.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TicTacToeGUI {

    private Board board;

    private static int FRAME_WIDTH = 1600;
    private static int FRAME_HEIGHT = 900;
    private JFrame frame;
    private JPanel backgroundColor;
    private static int LINE_WIDTH = 10;
    private static int LINE_HEIGHT = FRAME_WIDTH / 2;
    private static int TILE_WIDTH = ((LINE_HEIGHT) - (2 * LINE_WIDTH)) / 3;

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

    private void setUpBoard() {
        backgroundColor = new JPanel();
        backgroundColor.setLayout(null);
        backgroundColor.setBackground(Color.darkGray);
        backgroundColor.setVisible(true);
        frame.add(backgroundColor);

        createLines();
        setTiles();
    }

    private void createLines() {
        createTopHorizontalLine();
        createBottomHorizontalLine();
        createLeftVerticalLine();
        createRightVerticalLine();
    }



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

    private void setTopLeftTile() {
        JPanel topLeftTile = new JPanel();

        topLeftTile.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2),
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2),
                TILE_WIDTH,
                TILE_WIDTH);

        topLeftTile.setLayout(new BorderLayout());
        topLeftTile.setBackground(Color.darkGray);

        topLeftTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    board.markTile(0,0);
                    if (board.getTile(0, 0).getState() == Tile.State.X) {
                        topLeftTile.setBackground(Color.blue);
                    } else if (board.getTile(0, 0).getState() == Tile.State.O) {
                        topLeftTile.setBackground(Color.red);
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

        middleLeftTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    board.markTile(0,1);
                    if (board.getTile(0, 1).getState() == Tile.State.X) {
                        middleLeftTile.setBackground(Color.blue);
                    } else if (board.getTile(0, 1).getState() == Tile.State.O) {
                        middleLeftTile.setBackground(Color.red);
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

        bottomLeftTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    board.markTile(0,2);
                    if (board.getTile(0, 2).getState() == Tile.State.X) {
                        bottomLeftTile.setBackground(Color.blue);
                    } else if (board.getTile(0, 2).getState() == Tile.State.O) {
                        bottomLeftTile.setBackground(Color.red);
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

        topMiddleTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    board.markTile(1,0);
                    if (board.getTile(1, 0).getState() == Tile.State.X) {
                        topMiddleTile.setBackground(Color.blue);
                    } else if (board.getTile(1, 0).getState() == Tile.State.O) {
                        topMiddleTile.setBackground(Color.red);
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

        centerTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    board.markTile(1,1);
                    if (board.getTile(1, 1).getState() == Tile.State.X) {
                        centerTile.setBackground(Color.blue);
                    } else if (board.getTile(1, 1).getState() == Tile.State.O) {
                        centerTile.setBackground(Color.red);
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

        bottomMiddleTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    board.markTile(1,2);
                    if (board.getTile(1, 2).getState() == Tile.State.X) {
                        bottomMiddleTile.setBackground(Color.blue);
                    } else if (board.getTile(1, 2).getState() == Tile.State.O) {
                        bottomMiddleTile.setBackground(Color.red);
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

        topRightTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    board.markTile(2,0);
                    if (board.getTile(2, 0).getState() == Tile.State.X) {
                        topRightTile.setBackground(Color.blue);
                    } else if (board.getTile(2, 0).getState() == Tile.State.O) {
                        topRightTile.setBackground(Color.red);
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

        middleRightTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    board.markTile(2,1);
                    if (board.getTile(2, 1).getState() == Tile.State.X) {
                        middleRightTile.setBackground(Color.blue);
                    } else if (board.getTile(2, 1).getState() == Tile.State.O) {
                        middleRightTile.setBackground(Color.red);
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

        bottomRightTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.getGameIsOver()) {
                    board.markTile(2,2);
                    if (board.getTile(2, 2).getState() == Tile.State.X) {
                        bottomRightTile.setBackground(Color.blue);
                    } else if (board.getTile(2, 2).getState() == Tile.State.O) {
                        bottomRightTile.setBackground(Color.red);
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
}
