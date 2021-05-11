package engine.Graphics;

import java.awt.*;


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

    @Override
    public void draw(long timeStamp) {
        canvasC.draw(timeStamp);
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

