package games.Dungeon;

import engine.*;
import engine.Graphics.CanvasView;

public class DungeonView extends CanvasView {
    public DungeonView(String name, TableTop tb) {
        super(name);
        DungeonRenderer dr = new DungeonRenderer(tb);
        addRenderer(dr);
        int w = tb.width();
        dr.setOffset(w/2,w/2);
        draw();
    }
    public DungeonRenderer getDungeonRenderer(){
        return (DungeonRenderer) getRenderer(0);
    }
}
