package games.TestGame.Dungeon;

import engine.Graphics.BinaryMask;
import engine.Graphics.BoardRenderer;
import engine.TableTop;
import games.TestGame.Dungeon.World.Level;

import java.awt.*;

public class DungeonRenderer extends BoardRenderer {
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
    public void render(Graphics g) {
        if(tb==null) return;

        Level l = (Level)tb;
        BinaryMask bm = getVisable();
        bm.mergeAND(this.getDiscoveredMask());
        drawBoard(g,l.getBackground(),bm);
        ((Graphics2D)g).setPaint(new Color(0,9,20,(int)(256*(3.0/5))));
        ((Graphics2D)g).fillRect(-transC.getFocusX(),-transC.getFocusY(),transC.getInnerWidth(),transC.getInnerHeight());
        bm.mergeAND(this.getVisibilityMask());
        drawBoard(g,l.getBackground(),bm);
        drawBoard(g,l.getFloor(),bm);
        drawBoard(g,l.getForeground(),bm);
    }
}