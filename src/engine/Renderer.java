package engine;

import java.awt.*;

public abstract class Renderer{
    public abstract void draw(Graphics g);

    private final int innerWidth = 1000;
    private final int innerHeight = 1000;
    private int width = 1000;
    private int height = 1000;

    public void updateSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public int transX(int x){
        double scalar = ((double)width)/innerWidth;
        return (int)((x)/(scalar));
    }
    public int transY(int y){
        double scalar = ((double)height)/innerHeight;
        return (int)((y)/(scalar));
    }
}
