package games.TestGame.Dungeon;

import engine.*;
import games.TestGame.Dungeon.World.Level;

public class Attack implements Interaction{

    @Override
    public boolean action(PuppetMaster p, TableTop tb, Entity e, int x, int y) {
        Dungeon game = ((Dungeon)p);
        Level world = ((Level)tb);
        Board board = world.getForeground();
        Board floor = world.getFloor();

        if(board.OutOfBounds(x,y)) return false;
        Tile t = board.get(x,y);
        if(t instanceof Creature) {
            ((Creature) e).health -= ((Monster) t).getDamage();
            ((Creature)t).health -= ((Player)e).getStrength();      //temp damage output from player
            if (!((MoveInto)t).moveInto(game, e)) return false;
        }
        else if(t!=null) return false;
        
        if(((Creature)t).health <= 0 && ((Creature)e).health <= 0)
        {
            return false;
        }
        if(((Creature)t).health<=0)
        {
            board.pickup(e);
            board.place(e,x,y);
        }
        else if(((Creature)e).health<=0)
        {
            board.pickup((Entity) t);
            board.place(t,e.getX(),e.getY());
        }


        t = floor.get(x,y);
        if(t instanceof MoveInto) {
            ((MoveInto)t).moveInto(game, e);
        }
        return true;
    }
}
