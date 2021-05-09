package engine.Graphics;

import engine.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class BoardRenderer extends Renderer {
    protected TableTop tb;
    protected double zoom = 1;

    public BoardRenderer(TableTop tb) {
        super((int)(tb.width()*100), (int)(tb.width()*100));
        this.zoom = tb.width();
        setTableTop(tb);
    }
    public void setTableTop(TableTop tb) {
        this.tb = tb;
    }

    @Override
    public void render(Graphics g) {
        if(tb==null) return;

        BinaryMask bm = getVisable();
        for (Board b:tb.getBoards()){
            drawBoard(g,b,bm);
        }
    }
    public void drawBoard(Graphics g, Board b) {
        drawBoard(g,b,null);
    }

    public void drawBoard(Graphics g, Board b, BinaryMask mask) {
        int index=0;
        int w;
        int h;
        Iterator<Tile> itr = b.iterator();
        for(Tile t; itr.hasNext();) {
            t = itr.next();
            w = index%b.width();
            h = index/b.width();
            index++;
            if (t == null) continue;

            if(mask==null || mask.get(w,h)) {

                t.render(g, w * 100, h * 100);
            }
            //System.out.println("i:"+index+" x:"+tranX(w*100)+" y:"+tranY(h*100)+" w:"+(int)(100*scalar*zoom+0.5)+" h:"+(int)(100*scalar*zoom+0.5));
        }
    }
    public BinaryMask getVisable(){
        BinaryMask bm = new BinaryMask(tb.width(),tb.height());
        bm.clear(false);
        bm.clearRect(true
                , (int)((transC.getFocusX()-transC.getInnerWidth()*0.5)/100-0.5)
                , (int)((transC.getFocusY()-transC.getInnerHeight())/100-0.5)
                , (int)(transC.getInnerWidth()/100+2)
                , (int)(transC.getInnerHeight()/100+2));
        bm.clear(true);
        return bm;
    }
    public void setZoom(double zoom){
        this.zoom = zoom;
        transC.setInnerSize((int)(zoom*100), (int)(zoom*100));
    }
    public double getZoom(){
        return this.zoom;
    }
    public void setOffset(double x, double y){
        this.setFocus((int)(x*100),(int)(y*100));
    }
}
