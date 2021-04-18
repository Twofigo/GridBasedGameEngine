package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class BoardView extends CanvasView{
    private BoardRenderer br;
    public BoardView(String name, String[] options, ActionListener[] actions, TableTop tb) {
        super(name, options, actions);
        br = new BoardRenderer(tb);
        addRenderer(br);
        int w = tb.width();
        this.setInnerSize(w*100, w*100);
        this.setOffset(w/2,w/2);
    }
    public void setTableTop(TableTop tb) {
        br.setTableTop(tb);
    }
}

class BoardRenderer extends Renderer{
    TableTop tb;

    public BoardRenderer(TableTop tb) {
        setTableTop(tb);
    }
    public void setTableTop(TableTop tb) {
        this.tb = tb;
    }

    @Override
    public void draw(Graphics g, double scalar) {
        if(tb==null) return;
        for (Board b:tb.getBoards()){
            drawBoard(g,b,scalar);
        }
    }
    public void drawBoard(Graphics g, Board b, double scalar) {
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
    }
}