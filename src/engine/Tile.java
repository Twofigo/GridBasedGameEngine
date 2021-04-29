package engine;

import java.io.File;
import java.awt.*;

public class Tile {
    protected Image texture;

    public Tile(String textureName) {
        this.texture = TextureHandler.getInstance().getTexture(textureName);
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }

    public Image getTexture() {
        return texture;
    }
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x,y,100,100,null);
    }

}
