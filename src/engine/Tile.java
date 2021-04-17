package engine;

import java.io.File;
import java.awt.*;

public class Tile {
    Image texture;

    public Tile(Image texture) {
        this.texture = texture;
    }
    public Tile(String name) {
        this.texture = TextureHandler.getInstance().getTexture(name);
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }

    public Image getTexture() {
        return texture;
    }
}
