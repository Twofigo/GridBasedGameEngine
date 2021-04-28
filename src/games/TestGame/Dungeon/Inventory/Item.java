package games.TestGame.Dungeon.Inventory;

import engine.Entity;
import engine.PuppetMaster;
import engine.TableTop;
import games.TestGame.Dungeon.DungeonMaster;
import games.TestGame.Dungeon.MoveInto;
import games.TestGame.Dungeon.Player;

public class Item extends Entity implements MoveInto {
    public Item(String name) {
        super(name);
    }

    @Override
    public void update(PuppetMaster dm, TableTop l) {

    }


    @Override
    public boolean moveInto(DungeonMaster p, Entity e) {
        if (!(e instanceof Player)) return true;
        p.interact(p.PICKUP, this, this.getX(), this.getY());
        return true;
    }
}
