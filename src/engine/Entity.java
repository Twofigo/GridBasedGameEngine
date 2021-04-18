package engine;

import java.awt.*;

abstract public class Entity extends Tile{
    private int x;
    private int y;

    public Entity(Image texture) {
        super(texture);
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    abstract void update();
}
