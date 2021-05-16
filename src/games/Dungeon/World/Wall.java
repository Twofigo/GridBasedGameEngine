package games.Dungeon.World;

import engine.Entity;
import engine.Tile;
import games.Dungeon.DungeonMaster;
import games.Dungeon.MoveInto;

public class Wall extends Tile implements MoveInto {
    public Wall(String name) {
        super(name);
    }
    @Override
    public boolean moveInto(DungeonMaster p, Entity e) {
        return false;
    }
}