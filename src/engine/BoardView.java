package engine;

import engine.*;
import games.TestGame.Dungeon.World.Level;

import java.awt.*;

public class BoardView extends CanvasView{
    public BoardView(String name, TableTop tb) {
        super(name);
        BoardRenderer br = new BoardRenderer(tb);
        addRenderer(br);
        int w = tb.width();
        br.setOffset(w/2,w/2);
        draw();
    }
    public BoardRenderer getBoardRenderer(){
        return (BoardRenderer) getRenderer(0);
    }
}
