package engine.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TransformContext {
    private int focusX;
    private int focusY;

    private int outerWidth;
    private int outerHeight;

    private int innerWidth;
    private int innerHeight;

    public TransformContext(int innerWidth, int innerHeight) {
        this.innerWidth = this.outerWidth = innerWidth;
        this.innerHeight = this.outerHeight = innerWidth;
        this.focusX = innerWidth/2;
        this.focusY = innerHeight/2;
    }
    public void setInnerSize(int width, int height){
        innerWidth = width;
        innerHeight = height;
    }
    public void setOuterSize(int width, int height){
        outerWidth = width;
        outerHeight = height;
    }
    public void setFocus(int focusX, int focusY){
        this.focusX=focusX;
        this.focusY=focusY;
    }
    public int transX(int x){
        int trX = (int)((innerWidth/2-focusX-1));
        return (int)(x/((double)outerWidth/innerWidth)-trX);
    }
    public int transY(int y){
        int trY = (int)((innerHeight/2-focusY-1));
        return (int)(y/((double)outerHeight/innerHeight)-trY);
    }
    public int getFocusX() {
        return focusX;
    }

    public int getFocusY() {
        return focusY;
    }

    public int getOuterWidth() {
        return outerWidth;
    }

    public int getOuterHeight() {
        return outerHeight;
    }

    public int getInnerWidth() {
        return innerWidth;
    }

    public int getInnerHeight() {
        return innerHeight;
    }

    public BufferedImage updateBufferedImage(BufferedImage bf){
        if (bf.getWidth()!=innerWidth || bf.getHeight()!=innerHeight){
            return new BufferedImage((int)(innerWidth), (int)(innerHeight), BufferedImage.TYPE_INT_ARGB);
        }
        return bf;
    }
}
