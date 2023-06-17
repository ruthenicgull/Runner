package character;

import main.GamePanel;
import main.KeyHandler;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Character{

    GamePanel gp;
    KeyHandler keyH;

    int counter = 0;

    public final double screenX;
    public final double screenY;

    public Player(GamePanel gp, KeyHandler keyHandler){
        this.gp = gp;
        this.keyH = keyHandler;

        screenX = gp.screenWidth/4;
        screenY = gp.screenHeight/2;

        worldX = gp.tileSize * 5;
        worldY = gp.tileSize *6;
        speed = 4;

        solidArea = new Rectangle(8, 16, 32,32);

        getPlayerImage();
    }

    public void getPlayerImage(){
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(gp.gameStart == true){
            if(won == true){
                gp.gameWon = true;
            }
            if(dead == true){
                gp.gameOver = true;
            }
            collisionOn = false;
            gp.collisionDetector.checkTile(this);
            worldX += speed;
            if((keyH.downPressed == true || keyH.leftPressed == true
                    || keyH.upPressed == true || keyH.rightPressed == true) && collisionOn == false) {
                if (keyH.upPressed == true) {
                    direction = "up";
                    worldY -= speed;
                }
                if (keyH.downPressed == true) {
                    direction = "down";
                    worldY += speed;
                }
                if (keyH.leftPressed == true) {
                    direction = "left";
                    worldX -= speed;
                }
                if (keyH.rightPressed == true) {
                    direction = "right";
                    worldX += speed;
                }
            }
        }


        //CHECK TILE COLLISION


        //IF COLLISION IS NOT "ON", THEN PLAYER CAN MOVE
//        if (collisionOn == false){
//            switch (direction){
//                case "up":
//                    worldY -= speed;
//                    break;
//                case "down":
//                    worldY += speed;
//                    break;
//                case "left":
//                    worldX -= speed;
//                    break;
//                case "right":
//                    worldX += speed;
//                    break;
//            }
//
//        }
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.cyan);
//        g2.fillRect((int)worldX,(int)worldY, gp.tileSize, gp.tileSize);
//        g2.drawImage(image,(int)worldX,(int)worldY, gp.tileSize, gp.tileSize, null);
        g2.drawImage(image,(int)screenX,(int)screenY, gp.tileSize, gp.tileSize, null);
    }
}