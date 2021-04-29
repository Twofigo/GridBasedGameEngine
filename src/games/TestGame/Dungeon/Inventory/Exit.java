package games.TestGame.Dungeon.Inventory;

import engine.Entity;
import engine.Tile;
import games.TestGame.Dungeon.DungeonMaster;
import games.TestGame.Dungeon.MoveInto;
import games.TestGame.Dungeon.Player;

public class Exit extends Tile implements MoveInto {
    public Exit(String textureName) {
        super(textureName);
    }

    @Override
    public boolean moveInto(DungeonMaster p, Entity e) {
        if(e instanceof Player){
            return false;
        }
        return true;
    }
}
