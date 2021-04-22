package games.TestGame.Dungeon.Inventory;

import games.TestGame.Dungeon.Inventory.Item;

public class Weapon extends Item {
    private int damage;
    private int range;

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public Weapon(String name) {
        super(name);
    }
}
