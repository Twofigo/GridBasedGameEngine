package games.TestGame.Sokoban;

import engine.Entity;
import engine.Tile;
import games.TestGame.Sokoban.MoveInto;
import games.TestGame.Sokoban.Sokoban;

import java.awt.*;

public class Plate extends Tile implements MoveInto {
    public Plate(Image texture) {
        super(texture);
    }
    public Plate(String textureName){
        super(textureName);
    }
    @Override
    public boolean moveInto(Sokoban p, Entity e) {
        return true;
    }
}