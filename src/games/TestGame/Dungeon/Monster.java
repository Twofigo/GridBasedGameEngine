package games.TestGame.Dungeon;

import engine.Entity;
import engine.Interaction;
import engine.PuppetMaster;
import engine.TableTop;

public class Monster extends Creature{

    private int damage;
    public Monster(String name, int health, int dmg) {
        super(name, health);
        damage = dmg;
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
