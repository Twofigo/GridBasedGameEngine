package games.TestGame.Dungeon.Inventory;

import engine.TextureHandler;
import games.TestGame.Dungeon.Inventory.Item;

import java.awt.*;

public class Weapon extends Equipable {
    private int damage;
    private int equipmentSlot;

    private Image textureEquiped;

    public int getDamage() {
        return damage;
    }

    public Weapon(String textureName, String textureEquipedName, int damage) {
        super(textureName, textureEquipedName, 0);
        this.damage = damage;
    }

    public void renderEquipped(Graphics g, int x, int y){
        g.drawImage(textureEquiped, x,y,100,100,null);
    }
}