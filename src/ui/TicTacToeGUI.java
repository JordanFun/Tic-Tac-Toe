package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TicTacToeGUI {

    private static int FRAME_WIDTH = 1600;
    private static int FRAME_HEIGHT = 900;
    private JFrame frame;
    private JPanel backgroundColor;
    private static int LINE_WIDTH = 10;
    private static int LINE_HEIGHT = FRAME_WIDTH / 2;

    public TicTacToeGUI() {
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
        topHorizontalLine.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2), (FRAME_HEIGHT / 3) + (LINE_WIDTH / 2),
                LINE_HEIGHT, LINE_WIDTH);
        topHorizontalLine.setLayout(new BorderLayout());
        topHorizontalLine.setBackground(Color.white);

        backgroundColor.add(topHorizontalLine);
        topHorizontalLine.setVisible(true);
    }

    private void createBottomHorizontalLine() {
        JPanel bottomHorizontalLine = new JPanel();
        bottomHorizontalLine.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2), (2 * FRAME_HEIGHT / 3) - (LINE_WIDTH / 2),
                LINE_HEIGHT, LINE_WIDTH);
        bottomHorizontalLine.setLayout(new BorderLayout());
        bottomHorizontalLine.setBackground(Color.white);

        backgroundColor.add(bottomHorizontalLine);
        bottomHorizontalLine.setVisible(true);
    }

    private void createLeftVerticalLine() {
        JPanel leftVerticalLine = new JPanel();
        leftVerticalLine.setBounds((FRAME_WIDTH / 2) - ((LINE_HEIGHT / 6) + LINE_WIDTH), ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2),
                LINE_WIDTH, LINE_HEIGHT);
        leftVerticalLine.setLayout(new BorderLayout());
        leftVerticalLine.setBackground(Color.white);

        backgroundColor.add(leftVerticalLine);
        leftVerticalLine.setVisible(true);
    }

    private void createRightVerticalLine() {
        JPanel rightVerticalLine = new JPanel();
        rightVerticalLine.setBounds((FRAME_WIDTH / 2) + ((LINE_HEIGHT / 6) + LINE_WIDTH), ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2),
                LINE_WIDTH, LINE_HEIGHT);
        rightVerticalLine.setLayout(new BorderLayout());
        rightVerticalLine.setBackground(Color.white);

        backgroundColor.add(rightVerticalLine);
        rightVerticalLine.setVisible(true);
    }


    private void setTiles() {
        setTopLeftTile();
    }

    private void setTopLeftTile() {
        JPanel topLeftTile = new JPanel();
        topLeftTile.setBounds((FRAME_WIDTH / 2) - (LINE_HEIGHT / 2),
                ((FRAME_HEIGHT - LINE_HEIGHT) / 2) - (LINE_WIDTH / 2),
                (LINE_HEIGHT / 3) - (LINE_WIDTH / 2),
                (LINE_HEIGHT / 3) - (LINE_WIDTH / 2));

        topLeftTile.setLayout(new BorderLayout());
        topLeftTile.setBackground(Color.black);

        topLeftTile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                topLeftTile.setBackground(Color.red);
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




}
