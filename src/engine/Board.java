package engine;

import java.util.Iterator;

public class Board implements Iterable{
    private final int width;
    private final int height;

    private final Tile[][] tiles;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }
    public Board(int width, int height, Tile defTile) {
        this(width, height);
        for(int y=0;y<height;y++)
            for(int x=0;x<width;x++){
                this.set(defTile, x,y);
            }
    }
    public void update(){
        Iterator<Tile> itr = this.iterator();
        for(Tile t;itr.hasNext();){
            t = itr.next();
            if(t instanceof Entity){
                ((Entity) t).update();
            }
        }
    }

    public int width(){
        return width;
    }
    public int height(){
        return height;
    }
    public Tile get(int x,int y){
        return tiles[x][y];
    }
    public Tile set(Tile t, int x,int y){
        return tiles[x][y] = t;
    }
    public Tile pickup(int x,int y){
        return null;
    }
    public boolean place(Tile tile, int x, int y){
        return false;
    }

    @Override
    public boardIterator iterator() {
        return new boardIterator(this);
    }
}

class boardIterator implements Iterator<Tile>{
    int current;
    Board b;

    public boardIterator(Board b) {
        this.current = 0;
        this.b = b;
    }

    @Override
    public boolean hasNext() {
        if (current<b.width()*b.height())return true;
        return false;
    }

    @Override
    public Tile next() {
        if(!hasNext()) throw new IndexOutOfBoundsException();
        int x =current%b.width();
        int y =current/b.width();
        current++;
        return b.get(x,y);
    }
}
