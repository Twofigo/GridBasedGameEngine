package games.TestGame.Dungeon.Inventory;

import engine.TextureHandler;
import games.TestGame.Dungeon.Inventory.Item;

import java.awt.*;

public class Equipable extends Item {
    private int equipmentSlot;

    protected Image textureEquiped;

    public int getEquipmentSlot() {
        return equipmentSlot;
    }

    public Equipable(String textureName, String textureEquipedName, int equipmentSlot) {
        super(textureName);
        this.textureEquiped = TextureHandler.getInstance().getTexture(textureEquipedName);

        this.equipmentSlot = equipmentSlot;
    }
    public void renderEquipped(Graphics g, int x, int y){
        g.drawImage(textureEquiped, x,y,100,100,null);
    }

    public Equipable clone(){
        Equipable e = (Equipable)super.clone();
        e.textureEquiped = this.textureEquiped;
        e.equipmentSlot = this.equipmentSlot;
        return e;
    }
}
