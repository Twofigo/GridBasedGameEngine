package games.TestGame.Dungeon;

import engine.*;
import games.TestGame.Dungeon.World.Level;

public class MoveTo implements Interaction {
    @Override
    public boolean action(PuppetMaster p, TableTop tb, Entity e, int x, int y) {
        DungeonMaster game = ((DungeonMaster)p);
        Level world = ((Level)tb);
        Board back = world.getBackground();
        Board board = world.getForeground();
        Board floor = world.getFloor();

        if(back.OutOfBounds(x,y)) return false;
        Tile t = back.get(x,y);
        Tile c = board.get(x,y);
        if (e.getX()==x && e.getY()==y){
            return false;
        }

        if(t instanceof MoveInto) {
            if (!((MoveInto)t).moveInto(game, e)) return false;
        }

        if((c instanceof Creature)){
            Attack a = new Attack();
            a.action(p,tb,e,x,y);
            return false;
        }
        board.pickup(e);
        board.place(e,x,y);


        t = floor.get(x,y);
        if(t instanceof MoveInto) {
            ((MoveInto)t).moveInto(game, e);
        }
        return true;
    }
}