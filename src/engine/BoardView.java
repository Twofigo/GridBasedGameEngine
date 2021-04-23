package engine;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class BoardView extends CanvasView{
    protected BoardRenderer br;
    protected TableTop tb;
    public BoardView(String name, TableTop tb) {
        super(name);
        this.tb = tb;
    }
    @Override
    public void setup(){
        setupBoardView();
        draw();
    }
    protected void setupBoardView(){
        setupCanvasView();
        this.br = new BoardRenderer(this.tb);
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