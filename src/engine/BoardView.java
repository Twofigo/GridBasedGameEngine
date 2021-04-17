package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.*;

public class BoardView extends View {
    private JPanel buttonPanel;
    int buttonCount;

    private BoardCanvas boardC;

    public BoardView(String name, String[] options, ActionListener[] actions, TableTop tb) {
        super(name);
        buttonPanel = makeButtonRow(options,actions);
        buttonPanel.setLayout(new GridLayout(12,1,0,0));
        buttonCount = options.length;
        boardC = new BoardCanvas(tb);

        this.setLayout(new GridBagLayout());

        GridBagConstraints con = new GridBagConstraints();

        con.fill = GridBagConstraints.BOTH;
        con.gridy = 0;
        con.gridx = 0;
        con.weighty = 1.0;
        con.weightx = 3/4.0;
        this.add(boardC, con);

        con.gridy = 0;
        con.gridx = 1;
        con.weightx = 1/4.0;
        this.add(buttonPanel, con);

        boardC.draw();
        setVisible(true);
    }
    public void draw(){
        boardC.draw();
    }
    public void setZoom(double zoom){
        boardC.setZoom(zoom);
    }
    public void setOffset(double offsetX, double offsetY){
        boardC.setOffset(offsetX, offsetY);
    }

    @Override
    protected void updateSize(int width, int height) {
        boardC.setScalar(width*3.0/4);
        //boardC.draw();
    }
}

class BoardCanvas extends JPanel{
    TableTop tb;

    private double zoom;
    private double scalar;
    private int offsetX;
    private int offsetY;

    private int width;
    private int height;

    public BoardCanvas(TableTop tb){
        this.setZoom(1);
        this.setTable(tb);
    }
    public void setTable(TableTop tb){
        this.tb = tb;
        this.width = tb.width()*100;
        this.height = tb.width()*100;
        this.setScalar(width);
        this.setOffset(tb.width()/2.0,tb.height()/2.0);
        this.draw();
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
        for (Board b:tb.getBoards()){
            drawBoard(g,b,scalar*zoom);
        }
    }
    protected void drawBoard(Graphics g, Board b, double scalar){
        int index=0;
        int w;
        int h;
        Iterator<Tile> itr = b.iterator();
        for(Tile t; itr.hasNext();) {
            t = itr.next();
            w = index%b.width();
            h = index/b.width();
            index++;
            if(t==null) continue;
            g.drawImage(t.getTexture(), (int)(scalar*(w*100)),(int)(scalar*(h*100)), (int)(scalar*100)+1,(int)(scalar*100)+1,null);
            //System.out.println("i:"+index+" x:"+tranX(w*100)+" y:"+tranY(h*100)+" w:"+(int)(100*scalar*zoom+0.5)+" h:"+(int)(100*scalar*zoom+0.5));
        }
        //super.paint(g);
    }
}
