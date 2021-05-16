package games.Sokoban;

import engine.Entity;
import engine.Tile;


public class Wall extends Tile implements MoveInto {
    public Wall(String name) {
        super(name);
    }
    @Override
    public boolean moveInto(Sokoban p, Entity e) {
        return false;
    }
}