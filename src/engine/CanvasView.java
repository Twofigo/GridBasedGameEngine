package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

/**
 *
 */
public class CanvasView extends View {

    protected CanvasComponent canvasC;

    /**
     * sets up a button panel and canvas area.
     * @param name
     */
    public CanvasView(String name) {
        super(name);
        buttonPanel.setLayout(new GridLayout(12,1,0,0));
        canvasC = new CanvasComponent();
        this.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.fill = GridBagConstraints.BOTH;
        con.gridy = 0;
        con.gridx = 0;
        con.weighty = 1.0;
        con.weightx = 3/4.0;
        this.add(canvasC, con);

        con.gridy = 0;
        con.gridx = 1;
        con.weightx = 1/4.0;
        this.add(buttonPanel, con);

        canvasC.draw();
        setVisible(true);
        draw();
    }

    /**
     * updates the size of the canvas
     * @param width
     * @param height
     */
    @Override
    public void updateSize(int width, int height) {
        canvasC.updateSize(height, height);
        draw();
    }

    /**
     *redraws the canvas
     */
    @Override
    public void draw() {
        this.repaint();
        canvasC.draw();
    }

    /**
     * @param renderer
     */
    public void addRenderer(Renderer renderer){
        canvasC.addRenderer(renderer);
    }

    /**
     * @param i
     * @return
     */
    public Renderer getRenderer(int i){
        return canvasC.getRenderer(i);
    }
}

/**
 * The canvas part of the canvas view with a width and height
 */
class CanvasComponent extends JPanel{
    private ArrayList<Renderer> renderers;
    private BufferedImage offscreenImage;
    private Graphics offscreen;

    private final int innerWidth = 1000;
    private final int innerHeight = 1000;
    private int width = 1000;
    private int height = 1000;

    /**
     * Constructor initiates the CanvasComponent
     */
    public CanvasComponent(){
        renderers = new ArrayList<Renderer>();
        this.offscreenImage = new BufferedImage(innerWidth, innerHeight, BufferedImage.TYPE_INT_ARGB);
        this.offscreen = this.offscreenImage.getGraphics();
    }

    /**
     * Adds renderer in case we want to add a specific graphic to a part of the canvas.
     * @param renderer
     */
    public void addRenderer(Renderer renderer){
        renderers.add(renderer);
    }

    /**
     * returns a specific renderer of the canvas
     * @param i
     * @return
     */
    public Renderer getRenderer(int i){
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
        for (Renderer r:renderers) {
            r.updateSize(width,height);
        }
    }

    /**
     * repaint
     */
    public void draw(){
        repaint();
    }

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g){
        offscreen.clearRect(0,0,innerWidth,innerHeight);
        for (Renderer r: renderers) {
            r.draw(offscreen);
        }
        g.drawImage(this.offscreenImage, 0, 0,width,height, null);
    }
}
