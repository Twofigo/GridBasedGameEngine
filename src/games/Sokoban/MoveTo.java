package games.Sokoban;

import engine.*;

public class MoveTo implements Interaction {
    @Override
    public boolean action(PuppetMaster p, TableTop tb, Entity e, int x, int y) {
        assert(p!=null);
        assert(tb!=null);
        assert(e!=null);
        assert(p instanceof Sokoban);
        assert(tb instanceof Level);

        boolean b;

        Sokoban game = ((Sokoban)p);
        Level world = ((Level)tb);
        Board fg = world.getForeground();
        assert(fg!=null);

        if(fg.OutOfBounds(x,y)) return false;

        assert((Math.abs(e.getX()-x)+Math.abs(e.getY()-y))==1); // only moves 1 step

        Tile t = fg.get(x,y);
        if(t==null){
            int testx=e.getX();
            int testy=e.getY();
            Tile e2 = fg.pickup(e);
            assert(fg.get(testx,testy)==null); // pickup successful
            assert(e2==e); // picked up correct object

            b=fg.place(e,x,y);
            assert(b);
            assert(fg.get(x,y)==e);
            assert(e.getX()==x && e.getY()==y);
            return true;
        }
        if(t instanceof MoveInto){
            if(((MoveInto)t).moveInto(game,e)){
                int testx=e.getX();
                int testy=e.getY();
                Tile e2 = fg.pickup(e);
                assert(fg.get(testx,testy)==null); // pickup successful
                assert(e2==e); // picked up correct object

                b=fg.place(e,x,y);
                assert(b);
                assert(fg.get(x,y)==e);
                assert(e.getX()==x && e.getY()==y);
                return true;
            }
        }
        return false;
    }
}