package games.TestGame.Dungeon;

public class Equipable extends Item{
    private int armorRating;
    private int equipmentSlot;

    public int getArmorRating() {
        return armorRating;
    }

    public int getEquipmentSlot() {
        return equipmentSlot;
    }

    public Equipable(String name) {
        super(name);
    }
}
