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
    public void setZoom(double zoom){
        canvasC.setZoom(zoom);
    }
    public void setOffset(double offsetX, double offsetY){
        canvasC.setOffset(offsetX, offsetY);
    }
    public void addRenderer(Renderer renderer){
        canvasC.addRenderer(renderer);
    }
    public void setInnerSize(int width, int height){
        canvasC.setInnerSize(width, height);
    }
    public void setScalar(double canvasWidth){
        canvasC.setScalar(canvasWidth);
    }
}

class CanvasComponent extends JPanel{
    private ArrayList<Renderer> renderers;

    private double zoom;
    private double scalar;
    private int offsetX;
    private int offsetY;

    private int width;
    private int height;

    public CanvasComponent(){
        renderers = new ArrayList<Renderer>();
        this.zoom = 1;
        this.scalar = 1;
        this.setInnerSize(12*100, 12*100);
        this.setOffset(6,6);
    }
    public void setInnerSize(int width, int height){
        this.width = width;
        this.height = height;
        this.draw();
    }
    public void addRenderer(Renderer renderer){
        renderers.add(renderer);
    }
    public void setZoom(double zoom){
        this.zoom=zoom;
    }
    public void setScalar(double canvasWidth){
        this.scalar=(canvasWidth/width);
    }
    public void setOffset(double offsetX, double offsetY){
        this.offsetX=(int)(offsetX*100);
        this.offsetY=(int)(offsetY*100);
    }
    public void draw(){
        repaint();
    }
    @Override
    public void paint(Graphics g){
        int trX = (int)((width*0.5-offsetX*zoom)*scalar);;
        int trY = (int)((height*0.5-offsetY*zoom)*scalar);
        g.translate(trX, trY);

        for (Renderer r: renderers) {
            r.draw(g,zoom*scalar);
        }
    }
    public int transX(int x){
        int trX = (int)((width*0.5-offsetX*zoom)*scalar);
        return (int)((x-trX)/(zoom*scalar));
    }
    public int transY(int y){
        int trY = (int)((height*0.5-offsetY*zoom)*scalar);
        return (int)((y-trY)/(zoom*scalar));
    }
}
