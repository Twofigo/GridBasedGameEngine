package games.TestGame.Dungeon.World;

import engine.Entity;
import engine.Tile;
import games.TestGame.Dungeon.DungeonMaster;
import games.TestGame.Dungeon.MoveInto;

public class Wall extends Tile implements MoveInto {
    public Wall(String name) {
        super(name);
    }
    @Override
    public boolean moveInto(DungeonMaster p, Entity e) {
        return false;
    }
}