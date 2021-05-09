package engine.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Renderer{
    protected TransformContext transC;

    public Renderer(int innerWidth, int innerHeight) {
        this.transC = new TransformContext(innerWidth, innerHeight);
    }
    public abstract void render(Graphics g);

    public void draw(Graphics g){
        Graphics g2 = transC.getContext();
        this.render(g2);
        transC.drawTo(g);
    }
    public TransformContext getTransformContext() {
        return transC;
    }
    public void setTransformContext(TransformContext transC) {
        this.transC = transC;
    }

    public void setInnerSize(int width, int height){
        transC.setInnerSize(width, height);
    }
    public void setOuterSize(int width, int height){
        transC.setOuterSize(width, height);
    }
    public void setFocus(int focusX, int focusY){
        transC.setFocus(focusX, focusY);
    }
    public int transX(int x){
        return transC.transX(x);
    }
    public int transY(int y){
        return transC.transY(y);
    }
}
