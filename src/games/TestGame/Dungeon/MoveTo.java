package games.TestGame.Dungeon;

import engine.*;
import games.TestGame.Dungeon.World.Level;

public class MoveTo extends Interaction {
    @Override
    public boolean action(PuppetMaster p, TableTop tb, Entity e, int x, int y) {
        Dungeon game = ((Dungeon)p);
        Level world = ((Level)tb);
        Board board = world.getMiddleground();
        Board floor = world.getFloor();

        if(board.OutOfBounds(x,y)) return false;
        Tile t = board.get(x,y);
        if(t instanceof MoveInto) {
            if (!((MoveInto)t).moveInto(game, e)) return false;
        }
        else if(t!=null) return false;
        board.pickup(e);
        board.place(e,x,y);

        t = floor.get(x,y);
        if(t instanceof MoveInto) {
            ((MoveInto)t).moveInto(game, e);
        }
        return true;
    }
}