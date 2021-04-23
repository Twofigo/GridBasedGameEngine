package games.TestGame.Dungeon.Inventory;

import engine.TextureHandler;
import games.TestGame.Dungeon.Inventory.Item;

import java.awt.*;

public class Equipable extends Item {
    private int armorRating;
    private int equipmentSlot;

    private Image textureEquiped;

    public int getArmorRating() {
        return armorRating;
    }

    public int getEquipmentSlot() {
        return equipmentSlot;
    }

    public Equipable(String textureName, String textureEquipedName,  int armorRating, int equipmentSlot) {
        super(textureName);
        this.textureEquiped = TextureHandler.getInstance().getTexture(textureEquipedName);
        this.armorRating = armorRating;
        this.equipmentSlot = equipmentSlot;
    }
    public void renderEquiped(Graphics g, int x, int y){
        g.drawImage(textureEquiped, x,y,100,100,null);
    }
}
