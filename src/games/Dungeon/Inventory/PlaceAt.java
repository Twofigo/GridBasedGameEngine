package games.Dungeon.Inventory;

import games.Dungeon.DungeonMaster;

public interface PlaceAt {
    public boolean placeAt(DungeonMaster d, Item e);
    public boolean moveFrom(DungeonMaster d, Item e);
}
