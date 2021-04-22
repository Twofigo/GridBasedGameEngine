package games.TestGame.Dungeon;

import engine.Entity;

public abstract class Creature extends Entity {

    protected int health;
    protected String texturePath;

    public Creature(String name) {
        super(name);
    }
}
