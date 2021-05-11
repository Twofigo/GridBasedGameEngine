package games.TestGame.Sokoban;

import engine.*;

public class Actuate implements Interaction {
    @Override
    public boolean action(PuppetMaster p, TableTop tb, Entity e, int x, int y) {
        assert(p!=null);
        assert(tb!=null);
        assert(e!=null);
        assert(p instanceof Sokoban);
        assert(tb instanceof Level);

        if (!(e instanceof Box)) return false;
        Box box = (Box)e;

        Sokoban game = ((Sokoban)p);
        Level world = ((Level)tb);
        Board fg = world.getMiddleground();
        assert(fg!=null);

        if(fg.OutOfBounds(x,y)) return false;


        Tile floor = fg.get(x,y);

        if (floor instanceof Plate){
            if(!box.isActive()){
                box.setActive(true);
                game.updateVictoryProgress(1);
            }
            return true;
        }
        else if (box.isActive()){
            box.setActive(false);
            game.updateVictoryProgress(-1);
        }
        return false;
    }
}