package games.Dungeon;

import engine.*;
import games.Dungeon.World.Level;

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



        if(t instanceof Creature) {
            Creature d = (Creature) t;
            if(a instanceof Monster && d instanceof Monster) {
                return false;
            }

            int damageA =0;
            int damageD =0;

            if(a instanceof Player){
                damageA = d.getDamage() - ((Player)a).getArmorRating()-((Player)a).getEndurance();
                if(damageA < 1) {
                    damageA = 1;
                }
                ((Player)a).progressEndurance(damageA);
                ((Player)a).progressStrength(a.getDamage());
            }
            else if(a instanceof Monster){
                damageA = d.getDamage();
            }
            else {
                return false;
            }

            if(d instanceof Player){
                damageD = a.getDamage() - ((Player)d).getArmorRating()-((Player)d).getEndurance();
                if(damageD < 1) {
                    damageD = 1;
                }
                ((Player)d).progressEndurance(damageD);
                ((Player)d).progressStrength(d.getDamage());
            }
            else if(d instanceof Monster){
                damageD = a.getDamage();
            }
            else {
                return false;
            }

            a.setHealth(a.getHealth() - damageA);
            d.setHealth(d.getHealth() - damageD);      //temp damage output from player
            //if (!((MoveInto)t).moveInto(game, e)) return false;   //weird shit why cast tile type to MoveInto? also it generates tons of errors

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
        }
        else if(t!=null) return false;


        return true;
    }
}
