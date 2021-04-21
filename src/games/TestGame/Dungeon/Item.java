package games.TestGame.Dungeon;

import engine.Entity;
import engine.Tile;

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
