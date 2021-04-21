package games.TestGame.Dungeon;

import engine.Entity;
import engine.Tile;

import java.awt.*;

public class Wall extends Tile implements MoveInto {
    public Wall(Image texture) {
        super(texture);
    }
    public Wall(String name) {
        super(name);
    }
    @Override
    public boolean moveInto(Dungeon p, Entity e) {
        return false;
    }
}