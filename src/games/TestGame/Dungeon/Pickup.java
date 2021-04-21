package games.TestGame.Dungeon;

import engine.*;


public class Pickup extends Interaction {
    @Override
    public boolean action(PuppetMaster p, TableTop tb, Entity obj1, int x, int y) {
        Dungeon game = ((Dungeon)p);
        Inventory inv = ((Dungeon)p).getInventory();
        Level lvl = ((Dungeon)p).getLevel();

        lvl.getFloor().pickup(x,y);
        Board invB = inv.getForeground();

        for(int ky=0;ky<inv.height();ky++){
            for(int kx=0;kx<inv.width();kx++){
                if (invB.get(kx, ky)!=null)continue;
                invB.place(obj1, kx,ky);
                return true;
            }
        }
        return false;
    }
}
