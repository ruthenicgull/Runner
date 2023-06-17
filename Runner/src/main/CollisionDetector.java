package main;

import character.Character;

public class CollisionDetector {

    GamePanel gp;

    public CollisionDetector(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Character character){
        double characterLeftWorldX = character.worldX + character.solidArea.x;
        double characterRightWorldX = character.worldX + character.solidArea.x + character.solidArea.width;
        double characterTopWorldY = character.worldY + character.solidArea.y;
        double characterBottomWorldY = character.worldY + character.solidArea.y + character.solidArea.height;

        double characterLeftCol = characterLeftWorldX / gp.tileSize;
        double characterRightCol = characterRightWorldX / gp.tileSize;
        double characterTopRow = characterTopWorldY / gp.tileSize;
        double characterBottomRow = characterBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (character.direction){
            case "up":
                characterTopRow = (characterTopWorldY - character.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[(int) characterLeftCol][(int) characterTopRow];
                tileNum2 = gp.tileM.mapTileNum[(int) characterRightCol][(int) characterTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    character.collisionOn = true;
                    if(gp.tileM.tile[tileNum1].kill == true || gp.tileM.tile[tileNum2].kill == true){
                        character.dead = true;
                    }
                    if(gp.tileM.tile[tileNum1].finishLine == true || gp.tileM.tile[tileNum2].finishLine == true){
                        character.won = true;
                    }
                }
                break;
            case "down":
                characterBottomRow = (characterBottomWorldY + character.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[(int) characterLeftCol][(int) characterBottomRow];
                tileNum2 = gp.tileM.mapTileNum[(int) characterRightCol][(int) characterBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    character.collisionOn = true;
                    if(gp.tileM.tile[tileNum1].kill == true || gp.tileM.tile[tileNum2].kill == true){
                        character.dead = true;
                    }
                    if(gp.tileM.tile[tileNum1].finishLine == true || gp.tileM.tile[tileNum2].finishLine == true){
                        character.won = true;
                    }
                }
                break;
            case "left":
                characterLeftCol = (characterLeftWorldX - character.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[(int) characterLeftCol][(int) characterTopRow];
                tileNum2 = gp.tileM.mapTileNum[(int) characterLeftCol][(int) characterBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    character.collisionOn = true;
                    if(gp.tileM.tile[tileNum1].kill == true || gp.tileM.tile[tileNum2].kill == true){
                        character.dead = true;
                    }
                    if(gp.tileM.tile[tileNum1].finishLine == true || gp.tileM.tile[tileNum2].finishLine == true){
                        character.won = true;
                    }
                }
                break;
            case "right":
                characterRightCol = (characterRightWorldX + character.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[(int) characterRightCol][(int) characterTopRow];
                tileNum2 = gp.tileM.mapTileNum[(int) characterRightCol][(int) characterBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    character.collisionOn = true;
                    if(gp.tileM.tile[tileNum1].kill == true || gp.tileM.tile[tileNum2].kill == true){
                        character.dead = true;
                    }
                    if(gp.tileM.tile[tileNum1].finishLine == true || gp.tileM.tile[tileNum2].finishLine == true){
                        character.won = true;
                    }
                }
                break;
        }
    }
}
