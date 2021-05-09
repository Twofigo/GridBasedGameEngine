package engine.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TransformContext {
    private int focusX;
    private int focusY;

    private BufferedImage offscreenImage;
    private Graphics offscreen;

    private int outerWidth;
    private int outerHeight;

    private int innerWidth;
    private int innerHeight;

    public TransformContext(int innerWidth, int innerHeight) {
        this.innerWidth = this.outerWidth = innerWidth;
        this.innerHeight = this.outerHeight = innerWidth;
        this.focusX = innerWidth/2;
        this.focusY = innerHeight/2;
        this.offscreenImage = new BufferedImage((int)(innerWidth), (int)(innerHeight), BufferedImage.TYPE_INT_ARGB);
        this.offscreen = null;
    }
    public void setInnerSize(int width, int height){
        innerWidth = width;
        innerHeight = height;
        this.offscreenImage = new BufferedImage((int)(innerWidth), (int)(innerHeight), BufferedImage.TYPE_INT_ARGB);
    }
    public void setOuterSize(int width, int height){
        outerWidth = width;
        outerHeight = height;
    }
    Graphics getContext(){
        offscreen = this.offscreenImage.getGraphics();
        offscreen.clearRect(0,0,innerWidth,innerHeight);
        int trX = (int)((innerWidth/2-focusX-1));
        int trY = (int)((innerHeight/2-focusY-1));
        offscreen.translate(trX, trY);
        return offscreen;
    }
    void drawTo(Graphics g){
        g.drawImage(this.offscreenImage, 0, 0,1000,1000, null);
        offscreen.dispose();
        offscreen = null;
    }
    public void setFocus(int focusX, int focusY){
        this.focusX=focusX;
        this.focusX=focusY;
    }
    public int transX(int x){
        int trX = (int)((innerWidth/2-focusX-1));
        return (int)(x/(outerWidth/(innerWidth-trX)));
    }
    public int transY(int y){
        int trY = (int)((innerHeight/2-focusY-1));
        return (int)(y/(outerHeight/(innerHeight-trY)));
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
}
