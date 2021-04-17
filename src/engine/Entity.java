package engine;

import java.awt.*;

abstract public class Entity extends Tile{
    int x;
    int y;

    public Entity(Image texture) {
        super(texture);
    }

    abstract void update();
}
