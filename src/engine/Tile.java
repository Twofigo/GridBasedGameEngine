package engine;

import engine.tools.TextureHandler;

import java.awt.*;

/**
 * The most basic graphical structure in the framework
 */
public class Tile {
    protected Image texture;

    /**
     * Constructor
     * @param textureName
     */
    public Tile(String textureName) {
        this.texture = TextureHandler.getInstance().getTexture(textureName);
    }

    /**
     * sets the texture on the tile
     * @param texture
     */
    public void setTexture(Image texture) {
        this.texture = texture;
    }

    /**
     * returns texture from tile
     * @return
     */
    public Image getTexture() {
        return texture;
    }

    /**
     * draws the tile at a specified location
     * @param g
     * @param x
     * @param y
     */
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x,y,100,100,null);
    }

}
