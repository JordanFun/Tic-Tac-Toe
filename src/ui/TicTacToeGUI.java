package ui;

import javax.swing.*;
import java.awt.*;

public class TicTacToeGUI {

    private static int FRAME_WIDTH = 1600;
    private static int FRAME_HEIGHT = 900;
    private JFrame frame;
    private JPanel backgroundColor;

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
        JPanel topHorizontalLine = new JPanel();
        topHorizontalLine.setBounds(FRAME_WIDTH / 2, FRAME_HEIGHT / 2, 100, 100);
        topHorizontalLine.setLayout(new BorderLayout());
        topHorizontalLine.setBackground(Color.white);

        backgroundColor.add(topHorizontalLine);
        topHorizontalLine.setVisible(true);
    }

    private void setTiles() {
        
    }




}
