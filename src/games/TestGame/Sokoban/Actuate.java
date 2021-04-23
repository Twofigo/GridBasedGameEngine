package games.TestGame.Sokoban;

import engine.*;

public class Actuate implements Interaction {
    @Override
    public boolean action(PuppetMaster p, TableTop tb, Entity e, int x, int y) {
        if (!(e instanceof Box)) return false;
        Box box = (Box)e;
        Sokoban game = ((Sokoban)p);
        BasicTableTop world = ((BasicTableTop)tb);
        Board b = world.getMiddleground();
        Tile floor = b.get(x,y);
        if (floor instanceof Plate){
            if(!box.isActive()){
                box.setActive(true);
                ((Sokoban) p).updateVictoryProgress(1);
            }
            return true;
        }
        else if (box.isActive()){
            box.setActive(false);
            ((Sokoban) p).updateVictoryProgress(-1);
        }
        return false;
    }
}