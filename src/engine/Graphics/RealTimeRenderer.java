package engine.Graphics;

import java.awt.*;

public abstract class RealTimeRenderer extends Renderer{
    public RealTimeRenderer(int innerWidth, int innerHeight) {
        super(innerWidth, innerHeight);
    }

    public abstract void render(Graphics g, long timeStamp);

    public void draw(Graphics g, long timeStamp){
        Graphics g2 = transC.getContext();
        this.render(g2);
        transC.drawTo(g);
    }
}
