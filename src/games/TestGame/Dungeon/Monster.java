package games.TestGame.Dungeon;

import engine.Entity;
import engine.Interaction;
import engine.PuppetMaster;
import engine.TableTop;
import engine.tools.Dijkstra;
import games.TestGame.Dungeon.World.Level;

import java.awt.*;

public class Monster extends Creature{

    private int damage;
    private Dijkstra path;

    public Monster(String name, int health, int dmg) {
        super(name, health);
        damage = dmg;
    }

    public void update(DungeonMaster dm, Level l) {
        while (path!=null && path.hasNext()){
            Point p = path.next();
            if(p.x == getX() && p.y == getY()) continue; // don't walk into yourself
            if(!dm.interact(dm.MOVETO,this,p.x, p.y)) {
               path = null;
            }
            break;
        }
        else{
            int x = this.getX();
            int y = this.getY();
            //path = null;
            //Dijkstra.printMatrix(l.getMazeMatrix());
            path = new Dijkstra(l.getMazeMatrix(),x,y,dm.getPlayer().getX(),dm.getPlayer().getY());
            //Dijkstra.printMatrix(Dijkstra.pathfinder(l.getMazeMatrix(),x,y,dm.getPlayer().getX(),dm.getPlayer().getY()));
        }

    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void update(PuppetMaster dm, TableTop l) {
        update((DungeonMaster)dm, (Level)l);
    }
}
