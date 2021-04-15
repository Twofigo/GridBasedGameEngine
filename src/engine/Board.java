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

    public int width(){
        return width;
    }
    public int height(){
        return height;
    }
    public Tile get(int x,int y){
        return tiles[x][y];
    }
    public Tile pickup(int x,int y){
        return null;
    }
    public boolean place(Tile tile, int x, int y){
        return false;
    }


    @Override
    public Iterator iterator() {
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
        if (current+1<b.width()*b.height())return true;
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
