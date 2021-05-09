package engine.Graphics;

import engine.Tile;
import engine.tools.TextureHandler;

import java.awt.*;

public abstract class Sprite {
    protected Image texture;

    protected int width;
    protected int height;
    protected int posX;
    protected int posY;

    protected final int lifespan;
    protected final long timeStamp;

    public int getLifespan() {
        return lifespan;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }
    public void setPosition(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    public Sprite(String textureName, int posX, int posY, int lifespan, long startTime) {
        this.texture = TextureHandler.getInstance().getTexture(textureName);
        this.lifespan = lifespan;
        this.width = 100;
        this.height = 100;
        this.posX = posX;
        this.posY = posY;
        this.timeStamp = startTime;
    }
    public abstract void render(Graphics g, long timeStamp);
    public abstract Sprite clone(int lifespan, int startTime);
    public Sprite clone(Sprite s){
        s.texture = this.texture;
        s.width = this.width;
        s.posX = this.posX;
        s.posY = this.posY;
        return s;
    }
}
