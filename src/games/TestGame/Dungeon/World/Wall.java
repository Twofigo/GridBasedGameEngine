package games.TestGame.Dungeon.World;

import engine.Entity;
import engine.Tile;
import games.TestGame.Dungeon.Dungeon;
import games.TestGame.Dungeon.MoveInto;

import java.awt.*;

public class Wall extends Tile implements MoveInto {
    public Wall(String name) {
        super(name);
    }
    @Override
    public boolean moveInto(Dungeon p, Entity e) {
        return false;
    }
}