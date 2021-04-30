package games.TestGame.Dungeon;

import engine.Entity;
import engine.Interaction;
import engine.PuppetMaster;
import engine.TableTop;
import engine.tools.Dijkstra;
import games.TestGame.Dungeon.World.Level;
import java.util.Random;

import java.awt.*;

public class Monster extends Creature {

    private int damage;
    private Dijkstra path;
    private int behaviour;

    public Monster(String name, int health, int dmg) {
        super(name, health);
        damage = dmg;
        behaviour = 0;
    }

    public void update(DungeonMaster dm, Level l) {
        // if path exits
        int px = dm.getPlayer().getX();
        int py = dm.getPlayer().getY();
        int x = this.getX();
        int y = this.getY();

        if (x+y == 0) return;// probably dead

        // 0 = roam
        // 1 = track player
        // 2 = chase
        if (dm.isVisable(this.getX(), this.getY(), px, py)) {
            behaviour = 2;
            path = null;
        } else if (behaviour == 2) {
            behaviour = 1;
        }

        if(behaviour == 0){ // roam
            int ran = (int)(Math.random()*4);
            int dx = (ran%2==0)?1-ran:0;
            int dy = (ran%2==1)?2-ran:0;
            dm.interact(dm.MOVETO,this,x+dx,y+dy);
            /*
            Point[] directions = {
                    new Point(0,1), //upp
                    new Point(1,0), //höger
                    new Point(0,-1),//ner
                    new Point(-1,0) //Vänster
            };
            */

        }
        else if(behaviour ==1){ // track
            if (path==null){
                path = new Dijkstra(l.getMazeMatrix(), x, y, px, py);
                if (!path.findPath()) {
                    path = null;
                    return;
                }
            }
            while (path.hasNext()) {
                Point p = path.next();
                if (p.x == getX() && p.y == getY()) continue; // don't walk into yourself
                if (!dm.interact(dm.MOVETO, this, p.x, p.y)) {
                    path = null;
                    break;
                }
                return;
            }
            behaviour = 0;
        }
        else if (behaviour ==2){
            int dx = 0;
            int dy = 0;

            if(Math.abs(x-px) > Math.abs(y-py)){
                dx = px-x;
                dx = (dx<0)?-1:1;
                dy = 0;
            }
            else{
                dy = py-y;
                dy = (dy<0)?-1:1;
                dx = 0;
            }
            dm.interact(dm.MOVETO,this,x+dx,y+dy);
        }
}

    public int getDamage() {
        return damage;
    }

    @Override
    public void update(PuppetMaster dm, TableTop l) {
        update((DungeonMaster)dm, (Level)l);
    }

    public Monster clone(){
        Monster m = new Monster("", this.maxHealth, this.damage);
        m.x = this.x;
        m.y = this.y;
        m.texture = this.texture;

        m.maxHealth = this.maxHealth;
        m.health = this.health;

        m.texturePath = this.texturePath;
        m.name = this.name;
        m.description = this.description;
        return m;
    }

    @Override
    public Entity clone(Entity thing) {
        return null;
    }
}
