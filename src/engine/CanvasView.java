package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

public class CanvasView extends View {

    protected CanvasComponent canvasC;

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
    }

    @Override
    public void updateSize(int width, int height) {
        canvasC.updateSize(height, height);
        draw();
    }
    @Override
    public void draw() {
        this.repaint();
        canvasC.draw();
    }

    public int transX(int x){
        return canvasC.transX(x);
    }
    public int transY(int y){
        return canvasC.transY(y);
    }
    public void addRenderer(Renderer renderer){
        canvasC.addRenderer(renderer);
    }
}

class CanvasComponent extends JPanel{
    private ArrayList<Renderer> renderers;
    private BufferedImage offscreenImage;
    private Graphics offscreen;


    private final int innerWidth = 1000;
    private final int innerHeight = 1000;
    private int width = 1000;
    private int height = 1000;

    public CanvasComponent(){
        renderers = new ArrayList<Renderer>();
        this.offscreenImage = new BufferedImage(innerWidth, innerHeight, BufferedImage.TYPE_INT_ARGB);
        this.offscreen = this.offscreenImage.getGraphics();
    }
    public void addRenderer(Renderer renderer){
        renderers.add(renderer);
    }
    public void updateSize(int width, int height) {
        this.width = width;
        this.height = height;
        draw();
    }

    public void draw(){
        repaint();
    }
    @Override
    public void paint(Graphics g){
        offscreen.clearRect(0,0,innerWidth,innerHeight);
        for (Renderer r: renderers) {
            r.draw(offscreen);
        }
        g.drawImage(this.offscreenImage, 0, 0,width,height, null);
    }
    public int transX(int x){
        double scalar = width/innerWidth;
        return (int)((x)/(scalar));
    }
    public int transY(int y){
        double scalar = height/innerHeight;
        return (int)((y)/(scalar));
    }
}
