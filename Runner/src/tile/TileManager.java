package tile;

import character.Player;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Player player;

    public Tile tile[];
    int tileMapNum[][];
    public int[][] mapTileNum;

    public TileManager(GamePanel gp, Player player){
        this.player = player;
        this.gp = gp;
        tile = new Tile[5];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/res/maps/map2.txt");
        getTileImage();
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br =  new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldCol){
                String line = br.readLine();

                while( col < gp.maxWorldCol ){
                    String[] number = line.split(" ");
                    int num = Integer.parseInt(number[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/2.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png"));
            tile[4].collision = true;
            tile[4].finishLine = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tree.png"));
            tile[3].kill = true;
            tile[3].collision = true;
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];

            double worldX = worldCol * gp.tileSize;
            double worldY = worldRow * gp.tileSize;
            double screenX = worldX - gp.player.worldX + gp.player.screenX;
            double screenY = worldY - gp.player.worldY + gp.player.screenY;
//
//            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
//                     worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
//                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
//                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)

            g2.drawImage(tile[tileNum].image, (int)screenX, (int)screenY, gp.tileSize, gp.tileSize, null);

            worldCol++;
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
