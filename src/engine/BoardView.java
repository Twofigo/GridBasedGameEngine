package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class BoardView extends CanvasView{
    private BoardRenderer br;
    public BoardView(String name, TableTop tb) {
        super(name);
        br = new BoardRenderer(tb);
        addRenderer(br);
        int w = tb.width();
        this.setInnerSize(w*100, w*100);
        this.setOffset(w/2,w/2);
    }
    public void setZoom(double zoom){
        br.setZoom(zoom);
    }
    public double getZoom(){
        return br.getZoom();
    }
    public void setTableTop(TableTop tb) {
        br.setTableTop(tb);
    }
}

class BoardRenderer extends Renderer{
    TableTop tb;
    private double zoom;

    public BoardRenderer(TableTop tb) {
        setTableTop(tb);
        this.zoom = 1;
    }
    public void setTableTop(TableTop tb) {
        this.tb = tb;
    }

    @Override
    public void draw(Graphics g, double scalar) {
        if(tb==null) return;
        for (Board b:tb.getBoards()){
            drawBoard(g,b,scalar*zoom);
        }
    }
    public void setZoom(double zoom){
        this.zoom=zoom;
    }
    public double getZoom(){
        return this.zoom;
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
