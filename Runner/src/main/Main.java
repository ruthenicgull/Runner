package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("2D Runner");
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setVisible(true);
        window.setLocationRelativeTo(null);

        gamePanel.startGameThread();
    }
}