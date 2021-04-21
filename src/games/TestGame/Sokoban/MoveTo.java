package games.TestGame.Sokoban;

import engine.*;

public class MoveTo extends Interaction {
    @Override
    public boolean action(PuppetMaster p, TableTop tb, Entity e, int x, int y) {
        Sokoban game = ((Sokoban)p);
        BasicTableTop world = ((BasicTableTop)tb);
        Board b = world.getForeground();
        if(b.OutOfBounds(x,y)) return false;
        Tile t = b.get(x,y);
        if(t==null){
            b.pickup(e);
            b.place(e,x,y);
            return true;
        }
        if(t instanceof MoveInto){
            if(((MoveInto)t).moveInto(game,e)){
                b.pickup(e);
                b.place(e,x,y);
                return true;
            }
        }
        return false;
    }
}