package games.TestGame.Dungeon.Inventory;

import engine.*;
import games.TestGame.Dungeon.DungeonMaster;
import games.TestGame.Dungeon.World.Level;


public class Pickup implements Interaction {
    @Override
    public boolean action(PuppetMaster p, TableTop tb, Entity obj1, int x, int y) {
        DungeonMaster game = ((DungeonMaster)p);
        Inventory inv = ((DungeonMaster)p).getInventory();
        Level lvl = ((DungeonMaster)p).getLevel();

        lvl.getFloor().pickup(x,y);
        Board invB = inv.getForeground();

        for(int ky=0;ky<inv.height();ky++){
            for(int kx=0;kx<inv.width()-3;kx++){
                if (invB.get(kx, ky)!=null)continue;
                invB.place(obj1, kx,ky);
                return true;
            }
        }
        return false;
    }
}
