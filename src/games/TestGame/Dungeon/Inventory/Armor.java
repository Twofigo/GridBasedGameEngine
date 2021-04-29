package games.TestGame.Dungeon.Inventory;

public class Armor extends Equipable{
    protected int armorRating;

    public Armor(String textureName, String textureEquipedName, int armorRating, int equipmentSlot) {
        super(textureName, textureEquipedName, equipmentSlot);
        this.armorRating = armorRating;
    }

    public int getArmorRating() {
        return armorRating;
    }

    public Armor clone(){
        Armor a = (Armor)super.clone();
        a.armorRating = this.armorRating;
        return a;
    }
}
