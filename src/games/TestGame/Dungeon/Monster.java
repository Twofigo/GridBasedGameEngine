package games.TestGame.Dungeon;

import engine.Entity;
import engine.Interaction;
import engine.PuppetMaster;
import engine.TableTop;

public class Monster extends Creature{

    private int damage;
    public Monster(String name, int dmg, int health) {
        super(name);
        damage = dmg;
        super.health = health;
    }

    @Override
    public void update() {
        int x = this.getX();
        int y = this.getY();
    }

    public int getDamage() {
        return damage;
    }
}
