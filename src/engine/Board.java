package engine;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Random;

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
        clear(defTile);
    }
    public Board(int width, int height, Tile[] defTile) {
        this(width, height);
        clear(defTile);
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
    public void clear(Tile defTile){
        for(int y=0;y<height;y++)
            for(int x=0;x<width;x++){
                this.set(defTile, x,y);
            }
    }
    public void clear(Tile[] defTile){
        for(int y=0;y<height;y++)
            for(int x=0;x<width;x++){
                Random rand = new Random();
                int randValue = rand.nextInt(defTile.length);
                this.set(defTile[randValue], x,y);
            }
    }

    public int width(){
        return width;
    }
    public int height(){
        return height;
    }
    public Tile get(int x,int y){
        if (OutOfBounds(x,y)) return null;
        return tiles[x][y];
    }
    public boolean OutOfBounds(int x, int y){
        if (x<0 || x>=(width) || y<0 || y>=(height)) return true;
        return false;
    }
    public boolean set(Tile t, int x,int y){
        if (OutOfBounds(x,y)) return false;
        tiles[x][y] = t;
        return true;
    }
    public Tile pickup(int x,int y){
        Tile t = get(x,y);
        set(null, x,y);
        return t;
    }
    public void pickup(Entity e){
        pickup(e.getX(), e.getY());
        e.setPosition(0,0);
    }
    public boolean place(Tile tile, int x, int y){
        if(!set(tile, x, y)) return false;
        if(tile instanceof Entity){
            ((Entity)(tile)).setPosition(x,y);
        }
        return true;
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
