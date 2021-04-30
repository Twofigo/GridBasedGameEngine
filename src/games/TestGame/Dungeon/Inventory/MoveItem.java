package games.TestGame.Dungeon.Inventory;

import engine.*;
import games.TestGame.Dungeon.DungeonMaster;

public class MoveItem implements Interaction {

    @Override
    public boolean action(PuppetMaster p, TableTop tb, Entity e, int x, int y) {
        DungeonMaster game = ((DungeonMaster)p);
        if (!(tb instanceof Inventory)) return false;
        if (!(e instanceof Item)) return false;
        Item item = (Item)e;
        Inventory inv = ((Inventory)tb);
        Board board = inv.getForeground();
        Board fboard = inv.getBackground();

        if(board.OutOfBounds(x,y)) return false;

        Tile ft = fboard.get(e.getX(),e.getY());
        if(ft instanceof PlaceAt) {
            if (!((PlaceAt)ft).moveFrom(game, item)) return false;
        }

        ft = fboard.get(x,y);
        if(ft instanceof PlaceAt) {
            if (!((PlaceAt)ft).placeAt(game, item)) return false;
        }

        Tile t = board.get(x,y);
        if(t!=null) return false;

        board.pickup(item);
        board.place(item,x,y);
        p.getWindow().draw();

        return true;
    }
}
