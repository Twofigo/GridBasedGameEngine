package games.TestGame.Dungeon;

import engine.*;
import games.TestGame.Dungeon.World.Level;

public class Attack implements Interaction{

    @Override
    public boolean action(PuppetMaster p, TableTop tb, Entity e, int x, int y) {
        DungeonMaster game = ((DungeonMaster)p);
        Level world = ((Level)tb);
        Board board = world.getForeground();
        Board floor = world.getFloor();

        if(board.OutOfBounds(x,y)) return false;
        Tile t = board.get(x,y);

        Creature a = (Creature)e;
        Creature d = (Creature)t;

        int playerDamage = d.getDamage() - ((Player)a).getArmorRating();

        if(playerDamage < 1){
            playerDamage= 1;
        }

        if(t instanceof Creature) {
            a.setHealth(a.getHealth() - playerDamage);
            d.setHealth(d.getHealth() - a.getDamage());      //temp damage output from player
            //if (!((MoveInto)t).moveInto(game, e)) return false;   //weird shit why cast tile type to MoveInto? also it generates tons of errors
            System.out.println(((Creature) e).getHealth());
            System.out.println(((Creature) t).getHealth());
        }
        else if(t!=null) return false;

        if(a.getHealth() <= 0)
        {
            board.pickup(a);
            if(a instanceof Player){
                game.end();
            }
        }
        if(d.getHealth()<=0)
        {
            board.pickup(d);
            if(d instanceof Player){
                game.end();
            }
        }
        return true;
    }
}
