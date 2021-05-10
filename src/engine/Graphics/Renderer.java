package engine.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The renderer describes something drawn on the canvas.
 * The abtract member render() is overwritten to draw whanever you want to it.
 *
 */
public abstract class Renderer{
    protected TransformContext transC;

    private BufferedImage offscreenImage;
    private Graphics offscreen;

    /**
     * @param innerWidth width of the inner coordinate system
     * @param innerHeight height of the inner coordinate system
     */
    public Renderer(int innerWidth, int innerHeight) {
        this.transC = new TransformContext(innerWidth, innerHeight);
        this.offscreenImage = new BufferedImage((int)(innerWidth), (int)(innerHeight), BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * Abstract function for drawing. The scaling and offset is already applied to the Graphics object
     *
     * Do not call this method directly, it's called by draw() with the appropriate scaling and
     * transformations from the TransformContext object.
     *
     * @param g Graphics context that is drawn to.
     */
    public abstract void render(Graphics g);

    /**
     * Draws the renderer to the given Graphics object.
     * It calls opon the render(Graphics g) method.
     *
     * Called by the CanvasView
     *
     * @param g Graphics context that is drawn to.
     */
    public void draw(Graphics g){
        Graphics g2 = getContext();
        this.render(g2);
        drawTo(g);
    }

    /**
     * Redraws the last thing that was drawn, to the given Graphics object.
     * Does NOT re-run the render(Graphics g) method
     *
     * Called by the CanvasView
     *
     * @param g Graphics context that is drawn to.
     */
    public void redraw(Graphics g){
        Graphics g2 = getRawContext();
        drawTo(g);
    }


    /**
     * @return TransformContext the object
     */
    public TransformContext getTransformContext() {
        return transC;
    }

    /**
     * Sets the TransformContext to given object.
     *
     * This enables transformations to synchronize between renderers.
     *
     * @param transC TransformContext
     */
    public void setTransformContext(TransformContext transC) {
        this.transC = transC;
    }


    /**
     * Sets the size of the inner coordinate sytem
     *
     * @param width width of the inner coordinate system
     * @param height height of the inner coordinate system
     */
    public void setInnerSize(int width, int height){
        transC.setInnerSize(width, height);
    }
    /**
     * Sets the size of the outer coordinate system
     *
     * This is set automatically by CanvasView whenever needed.
     *
     * @param width width of the outer coordinate system
     * @param height height of the outer coordinate system
     */
    public void setOuterSize(int width, int height){
        transC.setOuterSize(width, height);
    }

    /**
     * Sets the coordinate that should be at the center of the canvas.
     *
     * @param focusX coordinate x
     * @param focusY coordinate y
     */
    public void setFocus(int focusX, int focusY){
        transC.setFocus(focusX, focusY);
    }
    /**
     * Translates a coordinate from the outer coordinate system, to the inner coordinate system
     *
     * useful for mouse inputs.
     *
     * @param x coordinate x
     */
    public int transX(int x){
        return transC.transX(x);
    }
    /**
     * Translates a coordinate from the outer coordinate system, to the inner coordinate system
     *
     * useful for mouse inputs.
     *
     * @param y coordinate y
     */
    public int transY(int y){
        return transC.transY(y);
    }

    /**
     * Returns A cleared Graphics object (for the off screen canvas),
     * that is correctly transformed according to the TransformationContext object.
     *
     * Used by draw(Graphics g)
     *
     * @return Graphics object
     */
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
    /**
     * Returns A NON cleared Graphics object (for the off screen canvas),
     * that is correctly transformed according to the TransformationContext object.
     *
     * Useful for redrawing what was drawn last. Used by redraw(Graphics g)
     *
     * @return Graphics object
     */
    protected Graphics getRawContext(){
        offscreen = this.offscreenImage.getGraphics();
        int trX = (int)((transC.getInnerWidth()/2-transC.getFocusX()-1));
        int trY = (int)((transC.getInnerHeight()/2-transC.getFocusY()-1));
        offscreen.translate(trX, trY);
        return offscreen;
    }
    /**
     * Draws the offscreen canvas to the given Graphics object.
     *
     * @return Graphics object
     */
    protected void drawTo(Graphics g){
        g.drawImage(this.offscreenImage, 0, 0,transC.getOuterWidth(),transC.getOuterHeight(), null);
        offscreen.dispose();
        offscreen = null;
    }
}
