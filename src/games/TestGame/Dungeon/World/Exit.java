package games.TestGame.Dungeon.World;

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
            p.setDifficulty(p.getDifficulty()+1);
            p.goDeeper();
            return false;
        }
        return true;
    }
}
