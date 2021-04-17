package engine;

import java.io.File;
import java.awt.*;

public class Tile {
    Image texture;

    public Tile(Image texture) {
        this.texture = texture;
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }

    public Image getTexture() {
        return texture;
    }
}
