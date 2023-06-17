package character;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Character {
    BufferedImage image;

    public double worldX, worldY;
    public double speed;
    public String direction = "right";

    public Rectangle solidArea;
    public boolean collisionOn;
    public boolean dead = false;
    public boolean won = false;
}
