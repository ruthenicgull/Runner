package main;

import javax.swing.*;
import java.awt.*;
import character.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    // Tile size and screen size
    public final int tileSize = 48;
    public final int screenCol = 16;
    public final int screenRow = 10;
    public final int screenWidth = tileSize * screenCol; //768
    public final int screenHeight = tileSize * screenRow; //576

    //GAME STATES
    public boolean gameStart = false;
    public boolean gameOver = false;
    public boolean gameWon = false;

    //FPS
    final int FPS = 60;

    //WORLD SETTINGS

    public final int maxWorldCol = 100;
    public final int maxWorldRow = 20;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);

    }

    Thread thread;
    KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);
    public TileManager tileM = new TileManager(this,player);
    public CollisionDetector collisionDetector = new CollisionDetector(this);

    public void startGameThread(){
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        // GAME LOOP
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (thread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    //YOU WIN SCREEN
    public void youWinScreen(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(0,0,screenWidth,screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));
        text = "You Win!";
        x = tileSize*3;
        y = tileSize*3;
        g2.setColor(Color.black);
        g2.drawString(text,x,y);
    }

    //GAME OVER SCREEN
    public void gameOverScreen(Graphics2D g2){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,screenWidth,screenHeight);

        int x;
        int y;
        String text;
        String text2;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));
        text = "Game Over";
        x = tileSize*2;
        y = tileSize*3;
        g2.setColor(Color.white);
        g2.drawString(text,x,y);
    }

    //START SCREEN
    void startScreen(Graphics2D g2){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,screenWidth,screenHeight);

        int x;
        int y;
        String text;
        String text2;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));
        text = "2D Runner";
        x = tileSize*2;
        y = tileSize*3;
        g2.setColor(Color.white);
        g2.drawString(text,x,y);
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC,40f));
        text2 = "press SPACE to start";
        x = tileSize*3;
        y = tileSize*8;
        g2.setColor(Color.gray);
        g2.drawString(text2,x,y);
    }

    //UPDATE
    public void update(){
        if(keyH.start == true){
            gameStart = true;
        }
        player.update();
    }

    //DRAW
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (player.won == true) {
            youWinScreen(g2);
        }

        if(gameStart == false){
            startScreen(g2);
        }else {
            if(gameOver == false && gameWon == false) {
                tileM.draw(g2);
                player.draw(g2);
            }
            if(gameOver == true){
                gameOverScreen(g2);
            }
            if (gameWon == true) {
                youWinScreen(g2);
            }
        }


        g2.dispose();
    }

}


