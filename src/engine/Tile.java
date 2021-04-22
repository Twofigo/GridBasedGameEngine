package engine;

import java.io.File;
import java.awt.*;

public class Tile {
    private Image texture;

    public Tile(String name) {
        this.texture = TextureHandler.getInstance().getTexture(name);
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x,y,100,100,null);
    }
}
