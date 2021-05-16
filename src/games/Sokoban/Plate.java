package games.Sokoban;

import engine.Entity;
import engine.Tile;

public class Plate extends Tile implements MoveInto {
    public Plate(String textureName){
        super(textureName);
    }
    @Override
    public boolean moveInto(Sokoban p, Entity e) {
        return true;
    }
}