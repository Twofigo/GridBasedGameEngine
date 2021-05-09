package engine.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Renderer{
    protected TransformContext transC;

    private BufferedImage offscreenImage;
    private Graphics offscreen;

    public Renderer(int innerWidth, int innerHeight) {
        this.transC = new TransformContext(innerWidth, innerHeight);
        this.offscreenImage = new BufferedImage((int)(innerWidth), (int)(innerHeight), BufferedImage.TYPE_INT_ARGB);
    }
    public abstract void render(Graphics g);


    protected Graphics getContext(){
        this.offscreenImage = transC.updateBufferedImage(this.offscreenImage);
        offscreen = this.offscreenImage.getGraphics();
        //offscreen.clearRect(0,0,innerWidth,innerHeight);
        //clear
        ((Graphics2D)offscreen).setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
        ((Graphics2D)offscreen).setPaint(new Color(0,0,0,0));
        ((Graphics2D)offscreen).fillRect(0,0,transC.getInnerWidth(),transC.getInnerHeight());
        ((Graphics2D)offscreen).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

        int trX = (int)((transC.getInnerWidth()/2-transC.getFocusX()-1));
        int trY = (int)((transC.getInnerHeight()/2-transC.getFocusY()-1));
        offscreen.translate(trX, trY);
        return offscreen;
    }
    protected Graphics getRawContext(){
        offscreen = this.offscreenImage.getGraphics();
        int trX = (int)((transC.getInnerWidth()/2-transC.getFocusX()-1));
        int trY = (int)((transC.getInnerHeight()/2-transC.getFocusY()-1));
        offscreen.translate(trX, trY);
        return offscreen;
    }
    protected void drawTo(Graphics g){
        g.drawImage(this.offscreenImage, 0, 0,transC.getOuterWidth(),transC.getOuterHeight(), null);
        offscreen.dispose();
        offscreen = null;
    }

    public void draw(Graphics g){
        Graphics g2 = getContext();
        this.render(g2);
        drawTo(g);
    }
    public void redraw(Graphics g){
        Graphics g2 = getRawContext();
        drawTo(g);
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
