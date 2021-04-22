package games.TestGame.Dungeon.Inventory;

import engine.Entity;
import engine.Tile;
import games.TestGame.Dungeon.Dungeon;
import games.TestGame.Dungeon.MoveInto;
import games.TestGame.Dungeon.Player;

public class Item extends Entity implements MoveInto {
    public Item(String name) {
        super(name);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean moveInto(Dungeon p, Entity e) {
        if (!(e instanceof Player)) return false;
        p.interact(p.PICKUP, this, this.getX(), this.getY());
        return true;
    }
}
