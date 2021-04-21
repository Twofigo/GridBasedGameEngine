package engine;

import java.awt.*;

abstract public class Entity extends Tile{
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public Entity(String name) {
        super(name);
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    public abstract void update();
}
