package engine;

import java.awt.*;
import java.awt.event.ActionListener;
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
        canvasC.setScalar(width*3.0/4);
    }
    @Override
    public void draw() {
        this.repaint();
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
    public void setScalar(double canvasWidth){
        canvasC.setScalar(canvasWidth);
    }
}

class CanvasComponent extends JPanel{
    private ArrayList<Renderer> renderers;

    private double scalar;
    private final int WIDTH = 1000;
    private final int HEIGHT = 1000;

    public double getScalar() {
        return scalar;
    }

    public CanvasComponent(){
        renderers = new ArrayList<Renderer>();
        this.scalar = 1;
    }
    public void addRenderer(Renderer renderer){
        renderers.add(renderer);
    }
    public void setScalar(double width){
        this.scalar=(width/WIDTH);
    }

    public void draw(){
        repaint();
    }
    @Override
    public void paint(Graphics g){
        for (Renderer r: renderers) {
            r.draw(g,scalar);
        }
    }
    public int transX(int x){
        return (int)((x)/(scalar));
    }
    public int transY(int y){
        return (int)((y)/(scalar));
    }
}
