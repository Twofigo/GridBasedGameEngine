package games.Dungeon.World;

import engine.Entity;
import engine.Tile;
import games.Dungeon.DungeonMaster;
import games.Dungeon.MoveInto;
import games.Dungeon.Player;

public class Exit extends Tile implements MoveInto {
    public Exit(String textureName) {
        super(textureName);
    }

    @Override
    public boolean moveInto(DungeonMaster p, Entity e) {
        if(e instanceof Player){
            p.setDifficulty(p.getDifficulty()+1);
            p.goDeeper();
            return false;
        }
        return true;
    }
}
