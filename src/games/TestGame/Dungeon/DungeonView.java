package games.TestGame.Dungeon;

import engine.*;
import games.TestGame.Dungeon.World.Level;

import java.awt.*;

public class DungeonView extends BoardView {
    public DungeonView(String name, TableTop tb) {
        super(name, tb);
    }
    @Override
    public void setup(){
        setupDungeonView();
        draw();
    }
    protected void setupDungeonView(){
        setupCanvasView();
        this.br = new DungeonRenderer(this.tb);
        addRenderer(br);
        int w = tb.width();
        this.setOffset(w/2,w/2);
    }
    public BinaryMask getVisibilityMask() {
        return ((DungeonRenderer)br).getVisibilityMask();
    }
    public BinaryMask getDiscoveredMask() {
        return ((DungeonRenderer)br).getDiscoveredMask();
    }

}

class DungeonRenderer extends BoardRenderer {
    protected BinaryMask visibility;
    protected BinaryMask discovered;

    public DungeonRenderer(TableTop tb) {
        super(tb);
        this.visibility = new BinaryMask(tb.width(), tb.height());
        this.discovered = new BinaryMask(tb.width(), tb.height());
        visibility.clear(true);
        discovered.clear(true);
    }
    public BinaryMask getVisibilityMask() {
        return visibility;
    }
    public BinaryMask getDiscoveredMask() {
        return discovered;
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

        Level l = (Level)tb;
        BinaryMask bm = getVisable();
        bm.mergeAND(this.getDiscoveredMask());
        drawBoard(offscreen,l.getBackground(),bm);
        ((Graphics2D)offscreen).setPaint(new Color(0,0,0,(int)(256*(2.0/3))));
        ((Graphics2D)offscreen).fillRect(-trX,-trY,(int)(100*zoom),(int)(100*zoom));
        bm.mergeAND(this.getVisibilityMask());
        drawBoard(offscreen,l.getBackground(),bm);
        drawBoard(offscreen,l.getFloor(),bm);
        drawBoard(offscreen,l.getForeground(),bm);

        offscreen.translate(-trX, -trY);
        g.drawImage(this.offscreenImage, 0, 0,1000,1000, null);
    }
}
