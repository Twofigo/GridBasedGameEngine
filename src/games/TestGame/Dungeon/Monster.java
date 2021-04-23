package games.TestGame.Dungeon;

import engine.Entity;
import engine.Interaction;
import engine.PuppetMaster;
import engine.TableTop;

public class Monster extends Creature{

    private int damage;
    public Monster(String name, int dmg) {
        super(name);
        damage = dmg;
    }

    @Override
    public void update() {

    }

}
