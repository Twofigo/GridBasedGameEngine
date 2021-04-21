package games.TestGame.Sokoban;

import engine.Entity;
import engine.Tile;
import games.TestGame.Sokoban.MoveInto;
import games.TestGame.Sokoban.Sokoban;

import java.awt.*;

public class Wall extends Tile implements MoveInto {
    public Wall(Image texture) {
        super(texture);
    }
    public Wall(String name) {
        super(name);
    }
    @Override
    public boolean moveInto(Sokoban p, Entity e) {
        return false;
    }
}