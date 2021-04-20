package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class BoardView extends CanvasView{
    private BoardRenderer br;
    private TableTop tb;
    public BoardView(String name, TableTop tb) {
        super(name);
        this.tb = tb;
        this.br = new BoardRenderer(tb);
        addRenderer(br);
        int w = tb.width();
        this.setOffset(w/2,w/2);
    }
    public void setZoom(double zoom){
        br.setZoom(zoom);
    }
    public double getZoom(){
        return br.getZoom();
    }
    public void setTableTop(TableTop tb) {
        this.tb = tb;
        br.setTableTop(tb);
    }
    public void setOffset(double offsetX, double offsetY){
        br.setOffset(offsetX, offsetY);
    }

    public int boardTransX(int x){
        // translate between coordinate systems they said. will be fun they said.
        // NO! THIS IS NOT FUN; THIS IS DEGRADING MY WILL TO LIVE FOR EACH SYMBOL I TYPE WRONG
        int trX = (int)(((tb.width()*100)*0.5-br.getOffsetX()*br.getZoom()));
        return (int)((transX(x)/(1000.0/(tb.width()*100))-trX)/getZoom());
    }
    public int boardTransY(int y){
        int trY = (int)(((tb.height()*100)*0.5-br.getOffsetY()*br.getZoom()));
        return (int)((transY(y)/(1000.0/(tb.width()*100))-trY)/getZoom());
    }
}

class BoardRenderer extends Renderer{
    TableTop tb;
    private double zoom;
    private int offsetX;
    private int offsetY;

    public BoardRenderer(TableTop tb) {
        setTableTop(tb);
        this.zoom = 1;
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

        double scalar=1000.0/(tb.width()*100);
        int trX = (int)((tb.width()*100*0.5-offsetX*zoom)*scalar);;
        int trY = (int)((tb.height()*100*0.5-offsetY*zoom)*scalar);

        //int trX = 0;
        //int trY = 0;
        g.translate(trX, trY);
        for (Board b:tb.getBoards()){
            drawBoard(g,b,scalar*zoom);
        }
        g.translate(-trX, -trY);
    }
    public void setZoom(double zoom){
        this.zoom=zoom;
    }
    public double getZoom(){
        return this.zoom;
    }
    public void setOffset(double offsetX, double offsetY){
        this.offsetX=(int)(offsetX*100);
        this.offsetY=(int)(offsetY*100);
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
