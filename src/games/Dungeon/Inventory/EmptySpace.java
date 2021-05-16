package games.Dungeon.Inventory;

import engine.Tile;
import games.Dungeon.DungeonMaster;

public class EmptySpace extends Tile implements PlaceAt{

    public EmptySpace(String name) {
        super(name);
    }

    @Override
    public boolean placeAt(DungeonMaster p, Item e) {
        return false;
    }

    @Override
    public boolean moveFrom(DungeonMaster d, Item e) {
        return true;
    }
}
