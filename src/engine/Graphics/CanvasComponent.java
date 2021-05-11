package engine.Graphics;

import engine.Graphics.RealTimeRenderer;
import engine.Graphics.Renderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The canvas part of the canvas view with a width and height
 */
public class CanvasComponent extends JPanel {
    private ArrayList<Renderer> renderers;
    private int width = 10;
    private int height = 10;
    private long timeStamp;
    private boolean redraw = false;

    /**
     * Constructor initiates the CanvasComponent
     */
    public CanvasComponent(){
        renderers = new ArrayList<engine.Graphics.Renderer>();
        timeStamp = 0;
    }

    /**
     * Adds renderer in case we want to add a specific graphic to a part of the canvas.
     * @param renderer
     */
    public void addRenderer(engine.Graphics.Renderer renderer){
        renderers.add(renderer);
    }

    /**
     * returns a specific renderer of the canvas
     * @param i
     * @return
     */
    public engine.Graphics.Renderer getRenderer(int i){
        return renderers.get(i);
    }

    /**
     * changes the width and height of the canvas and also updates the renderers
     * @param width
     * @param height
     */
    public void updateSize(int width, int height) {
        this.width = width;
        this.height = height;
        for (engine.Graphics.Renderer r: renderers) {
            r.setOuterSize(width, height);
        }
    }

    /**
     * repaint
     */
    public void draw(){
        this.redraw = true;
        repaint();
    }
    public void draw(long timeStamp){
        this.timeStamp = timeStamp;
        repaint();
    }

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,width, height);


        if(redraw){
            for (engine.Graphics.Renderer r : renderers) {
                r.draw(g);
            }
            redraw = false;
        }
        if(this.timeStamp!=0) {
            for (engine.Graphics.Renderer r : renderers) {
                if(r instanceof RealTimeRenderer)
                    ((RealTimeRenderer)r).draw(g,this.timeStamp);
                else{
                    r.redraw(g);
                }
            }

            this.timeStamp = 0;
        }
    }
}