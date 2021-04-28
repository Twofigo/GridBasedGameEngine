package games.TestGame.Dungeon.Inventory;

import engine.TextureHandler;
import games.TestGame.Dungeon.Inventory.Item;

import java.awt.*;

public class Weapon extends Item {
    private int damage;
    private int range;
    private int equipmentSlot;

    private Image textureEquiped;

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public Weapon(String textureName, String textureEquipedName,int equipmentSlot, int damage, int range) {
        super(textureName);
        this.textureEquiped = TextureHandler.getInstance().getTexture(textureEquipedName);
        this.equipmentSlot = equipmentSlot;
        this.damage = damage;
        this.range = range;
    }

    public void renderEquiped(Graphics g, int x, int y){
        g.drawImage(textureEquiped, x,y,100,100,null);
    }
}
