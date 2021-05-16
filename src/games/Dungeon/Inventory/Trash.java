package games.Dungeon.Inventory;

import engine.Entity;
import engine.PuppetMaster;
import engine.TableTop;
import games.Dungeon.DungeonMaster;

public class Trash extends Entity implements PlaceAt{
    public Trash(String name) {
        super(name);
    }

    @Override
    public void update(PuppetMaster dm, TableTop l) {

    }
    @Override
    public Entity clone() {
        return null;
    }

    @Override
    public Entity clone(Entity thing) {
        return null;
    }

    @Override
    public boolean placeAt(DungeonMaster d, Item e) {
        d.getInventory().getForeground().pickup(getX(),getY());
        return true;
    }

    @Override
    public boolean moveFrom(DungeonMaster d, Item e) {
        return true;
    }
}
