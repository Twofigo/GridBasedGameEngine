package games.TestGame.Dungeon.Inventory;

import engine.Entity;
import games.TestGame.Dungeon.DungeonMaster;

public class EquipmentSlot extends Entity implements PlaceAt{
    private int slot;

    public EquipmentSlot(String name, int slot) {
        super(name);
        this.slot = slot;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean placeAt(DungeonMaster d, Item e) {
        if (!(e instanceof Equipable)) return false;
        Equipable eq = (Equipable)e;
        if (eq.getEquipmentSlot()!=slot) return false;
        Inventory inv = d.getInventory();
        if (inv.getForeground().get(getX(), getY())!=null) return false;
        d.getPlayer().equip(eq);
        return true;
    }

    @Override
    public boolean moveFrom(DungeonMaster d, Item e) {
        if (!(e instanceof Equipable)) return true;
        Equipable eq = (Equipable)e;
        d.getPlayer().unEquip(eq.getEquipmentSlot());
        return true;
    }
}

