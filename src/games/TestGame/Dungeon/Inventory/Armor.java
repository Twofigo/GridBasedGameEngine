package games.TestGame.Dungeon.Inventory;

import engine.Entity;

public class Armor extends Equipable{
    protected int armorRating;

    public Armor(String textureName, String textureEquipedName, int armorRating, int equipmentSlot) {
        super(textureName, textureEquipedName, equipmentSlot);
        this.armorRating = armorRating;
    }

    public int getArmorRating() {
        return armorRating;
    }

    public Armor clone(Entity e){
        Armor a = (Armor)super.clone(e);
        a.armorRating = this.armorRating;
        return a;
    }

    public Armor clone(){
        return clone(new Armor("","",0,0));
    }
}
