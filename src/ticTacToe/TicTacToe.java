package ticTacToe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    private static final Integer WIDTH = 600;
    private static final Integer HEIGHT = 650;
    private static final String TITLE = "Tic-Tac-Toe";
    private static final Integer SIZE = 3;

    private static final String PLAYER_X = "X";
    private static final String PLAYER_O = "O";
    private String CURRENT_PLAYER = PLAYER_X;

    private final Frame frame = new JFrame();

    private final JPanel textPanel = new JPanel();
    private final JLabel textLabel = new JLabel();
    private final JButton[][] field = new JButton[SIZE][SIZE];
    private Boolean gameOver = false;

    public TicTacToe() {
        this.initField();
        this.initFrame();
    }

    private void initFrame() {
        this.frame.setVisible(true);
        this.frame.setSize(WIDTH, HEIGHT);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.frame.setLayout(new BorderLayout());

        this.textLabel.setBackground(Color.DARK_GRAY);
        this.textLabel.setForeground(Color.WHITE);
        this.textLabel.setHorizontalAlignment(JLabel.CENTER);
        this.textLabel.setText(TITLE);
        this.textLabel.setOpaque(true);
        this.textLabel.setFont(new Font("Times", Font.BOLD, 50));

        this.textPanel.setLayout(new BorderLayout());
        this.textPanel.add(this.textLabel);

        this.frame.add(this.textLabel, BorderLayout.NORTH);
        this.frame.add(this.initField());
    }

    private void setWinner(JButton tile) {
        tile.setForeground(Color.GREEN);
        tile.setBackground(Color.GRAY);
        this.textLabel.setText(CURRENT_PLAYER.equals(PLAYER_X) ? PLAYER_O : PLAYER_X + " is the winner!");
    }


    private Boolean checkHorizontals(int i) {
        int count = 0;
        for (int col = 0; col < SIZE; col++) {
            if (this.field[i][0].getText().equals(this.field[i][col].getText())) {
                count++;
            }
        }
        return count == SIZE;
    }

    private Boolean checkVerticals(int i) {
        int count = 0;
        for (int row = 0; row < SIZE; row++) {
            if (this.field[0][i].getText().equals(this.field[row][i].getText())) {
                count++;
            }
        }
        return count == SIZE;
    }

    private Boolean checkFirstDiagonal() {
        int count = 0;
        for (int j = 0; j < SIZE; j++) {
            if (this.field[j][SIZE - j - 1].getText().equals(this.field[0][SIZE - 1].getText())) {
                count++;
            }
        }
        return count == SIZE;
    }

    private Boolean checkSecondDiagonal() {
        int count = 0;
        for (int j = 0; j < SIZE; j++) {
            if (this.field[j][j].getText().equals(this.field[0][0].getText())) {
                count++;
            }
        }
        return count == SIZE;
    }

    private void checkWinner() {

        for (int i = 0; i < SIZE; i++) {
            if (!this.field[0][0].getText().isEmpty() && checkSecondDiagonal()) {
                gameOver = true;
                for (int j = 0; j < SIZE; j++) {
                    setWinner(this.field[j][j]);
                }
            }

            if (!this.field[0][SIZE - 1].getText().isEmpty() && checkFirstDiagonal()) {
                gameOver = true;
                for (int j = 0; j < SIZE; j++) {
                    setWinner(this.field[j][SIZE - j - 1]);
                }
            }
            if (!this.field[i][0].getText().isEmpty()) {
                if (checkHorizontals(i)) {
                    gameOver = true;
                    for (int j = 0; j < SIZE; j++) {
                        setWinner(this.field[i][j]);
                    }
                }
            }
            if (!this.field[0][i].getText().isEmpty() && checkVerticals(i)) {
                gameOver = true;
                for (int j = 0; j < SIZE; j++) {
                    setWinner(this.field[j][i]);
                }
            }
        }
    }


    private JPanel initField() {
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(SIZE, SIZE));
        boardPanel.setBackground(Color.darkGray);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JButton tile = new JButton();
                this.field[row][col] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) {
                            return;
                        }
                        var tile = (JButton) e.getSource();
                        if (tile.getText().isEmpty()) {
                            checkWinner();
                            if (!gameOver) {
                                tile.setText(CURRENT_PLAYER);
                                CURRENT_PLAYER = CURRENT_PLAYER.equals(PLAYER_X) ? PLAYER_O : PLAYER_X;
                            }
                        }
                    }
                });
            }
        }
        return boardPanel;
    }
}
