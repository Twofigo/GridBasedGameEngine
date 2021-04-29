package games.TestGame.Dungeon.Inventory;

import engine.TextureHandler;
import games.TestGame.Dungeon.Inventory.Item;

import java.awt.*;

public class Weapon extends Equipable {
    private int damage;

    public int getDamage() {
        return damage;
    }

    public Weapon(String textureName, String textureEquipedName, int damage) {
        super(textureName, textureEquipedName, 0);
        this.damage = damage;
    }

}