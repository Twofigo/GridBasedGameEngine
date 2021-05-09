package engine.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
    public void addRenderer(engine.Graphics.Renderer renderer){
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
    private ArrayList<engine.Graphics.Renderer> renderers;
    int width = 10;
    int height = 10;

    /**
     * Constructor initiates the CanvasComponent
     */
    public CanvasComponent(){
        renderers = new ArrayList<engine.Graphics.Renderer>();
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

        for (engine.Graphics.Renderer r: renderers) {
            r.draw(g);
        }
    }
}
