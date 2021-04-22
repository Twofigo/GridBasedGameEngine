package games.TestGame.Dungeon.Inventory;

import games.TestGame.Dungeon.Dungeon;

public interface PlaceAt {
    public boolean placeAt(Dungeon d, Item e);
    public boolean moveFrom(Dungeon d, Item e);
}
