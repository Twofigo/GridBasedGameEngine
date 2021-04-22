package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
        int trX = (int)(((br.getZoom()*50)-br.getOffsetX()));
        return (int)((transX(x)/(1000.0/(br.getZoom()*100))-trX));
    }
    public int boardTransY(int y){
        int trY = (int)(((br.getZoom()*50)-br.getOffsetY()));
        return (int)((transY(y)/(1000.0/(br.getZoom()*100))-trY));
    }
}

class BoardRenderer extends Renderer{
    TableTop tb;
    private double zoom;
    private int offsetX;
    private int offsetY;

    private BufferedImage offscreenImage;
    private Graphics offscreen;
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

        int trX = (int)((zoom*50-offsetX));;
        int trY = (int)((zoom*50-offsetY));

        //int trX = 0;
        //int trY = 0;
        offscreen.translate(trX, trY);
        for (Board b:tb.getBoards()){
            drawBoard(offscreen,b);
        }
        offscreen.translate(-trX, -trY);
        g.drawImage(this.offscreenImage, 0, 0,1000,1000, null);
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
            t.render(g,w*100, h*100);
            //System.out.println("i:"+index+" x:"+tranX(w*100)+" y:"+tranY(h*100)+" w:"+(int)(100*scalar*zoom+0.5)+" h:"+(int)(100*scalar*zoom+0.5));
        }
    }
}
