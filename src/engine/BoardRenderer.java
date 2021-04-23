package engine;

import engine.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class BoardRenderer extends Renderer {
    protected TableTop tb;
    protected double zoom;
    protected int offsetX;
    protected int offsetY;

    protected BufferedImage offscreenImage;
    protected Graphics offscreen;
    public BoardRenderer(TableTop tb) {
        setTableTop(tb);
        setZoom(tb.width());
    }
    public void setTableTop(TableTop tb) {
        this.tb = tb;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    @Override
    public void draw(Graphics g) {
        if(tb==null) return;

        int trX = (int)((zoom*50-offsetX-1));;
        int trY = (int)((zoom*50-offsetY-1));

        //int trX = 0;
        //int trY = 0;
        offscreen.clearRect(0,0,(int)(100*zoom),(int)(100*zoom));
        offscreen.translate(trX, trY);
        BinaryMask bm = getVisable();
        for (Board b:tb.getBoards()){
            drawBoard(offscreen,b,bm);
        }
        offscreen.translate(-trX, -trY);
        g.drawImage(this.offscreenImage, 0, 0,1000,1000, null);
    }
    public void setZoom(double zoom){
        this.zoom=zoom;
        this.offscreenImage = new BufferedImage((int)(zoom*100), (int)(zoom*100), BufferedImage.TYPE_INT_ARGB);
        this.offscreen = this.offscreenImage.getGraphics();
    }
    public double getZoom(){
        return this.zoom;
    }
    public void setOffset(double offsetX, double offsetY){
        this.offsetX=(int)(offsetX*100);
        this.offsetY=(int)(offsetY*100);
    }
    public void drawBoard(Graphics g, Board b) {
        drawBoard(g,b,null);
    }
    public BinaryMask getVisable(){
        BinaryMask bm = new BinaryMask(tb.width(),tb.height());
        bm.clear(false);
        bm.clearRect(true, (int)(offsetX/100-zoom*0.5-0.5), (int)(offsetY/100-zoom*0.5-0.5), (int)(zoom+2), (int)(zoom+2));
        return bm;
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
}
