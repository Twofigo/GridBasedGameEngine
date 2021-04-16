package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BoardView extends View {
    private JPanel buttonPanel;
    int buttonCount;

    private BoardCanvas boardC;

    public BoardView(String[] options, ActionListener[] actions, Board b) {
        super();
        buttonPanel = makeButtonRow(options,actions);
        buttonPanel.setLayout(new GridLayout(12,1,0,0));
        buttonCount = options.length;
        boardC = new BoardCanvas(b);

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

    @Override
    protected void updateSize(int width, int height) {
        //boardC.setPreferredSize(new Dimension(width, width));
        //buttonPanel.setPreferredSize(new Dimension(width, height-width-40));
        boardC.setScalar(width*3.0/4);
        boardC.draw();
    }
}

class BoardCanvas extends JPanel{
    Board b;

    private double zoom;
    private double scalar;
    private int offsetX;
    private int offsetY;

    private int width;
    private int height;

    public BoardCanvas(Board b){
        this.setZoom(1);
        this.setBoard(b);
    }
    public void setBoard(Board b){
        this.b = b;
        this.width = b.width()*100;
        this.height = b.height()*100;
        this.setScalar(width);
        this.setOffset(b.width()/2.0,b.height()/2.0);
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
        int index=0;
        int w;
        int h;
        for(Object t : b) {
            w = index%b.width();
            h = index/b.width();
            index++;
            t = (Tile) (t);
            if((w%2+h%2)%2==1) {
                g.setColor(Color.GREEN);
            }
            else{
                g.setColor(Color.RED);
            }
            g.fillRect(tranX(w*100),tranY(h*100), (int)(100*scalar*zoom)+1,(int)(100*scalar*zoom)+1);
            //System.out.println("i:"+index+" x:"+tranX(w*100)+" y:"+tranY(h*100)+" w:"+(int)(100*scalar*zoom+0.5)+" h:"+(int)(100*scalar*zoom+0.5));
        }
    }

    private int tranX(int x){
        return (int)(((x-offsetX)*zoom+(width/2))*scalar);
    }
    private int tranY(int y){
        return (int)(((y-offsetY)*zoom+(height/2))*scalar);
    }
}
