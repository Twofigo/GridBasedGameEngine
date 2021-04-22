package games.TestGame.Dungeon.Inventory;

import engine.Entity;
import engine.Tile;
import games.TestGame.Dungeon.Dungeon;

public class EmptySpace extends Tile implements PlaceAt{

    public EmptySpace(String name) {
        super(name);
    }

    @Override
    public boolean placeAt(Dungeon p, Item e) {
        return false;
    }

    @Override
    public boolean moveFrom(Dungeon d, Item e) {
        return true;
    }
}
