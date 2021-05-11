package engine;

import games.TestGame.Dungeon.Inventory.Pickup;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Random;

/**
 *2d array of Tiles, height and width of this board is defined in class fields as well
 */
public class Board implements Iterable{
    private final int width;
    private final int height;

    private final Tile[][] tiles;
    private ArrayList<Entity> entities;

    /**
     * Generates an empty array with a width and height
     * @param width
     * @param height
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
        this.entities = new ArrayList<Entity>(4);
    }

    /**
     * Generates a 2d array with specified with and height and specified standard tile
     * @param width
     * @param height
     * @param defTile
     */
    public Board(int width, int height, Tile defTile) {
        this(width, height);
        clear(defTile);
    }

    /**
     * Generates a 2d array with specified with and height and specified standard tiles (tiles will be placed in the array in a gaussian pattern)
     * @param width
     * @param height
     * @param defTile
     */
    public Board(int width, int height, Tile[] defTile) {
        this(width, height);
        clear(defTile);
    }

    /**
     * Updates all entities on a board
     * @param pm
     * @param tt
     */
    public void update(PuppetMaster pm, TableTop tt){
        if (entities.size()==0)return;

        for(Object e: entities.toArray()){
            ((Entity)e).update(pm, tt);
        }
    }

    /**
     * Replaces all the boards tiles with new tiles
     * @param defTile
     */
    public void clear(Tile defTile){
        for(int y=0;y<height;y++)
            for(int x=0;x<width;x++){
                this.set(defTile, x,y);
            }
    }

    /**
     * Creates a gaussian pattern that we can use to place tiles in a nice pattern
     * @param width
     * @param height
     * @param seed
     * @return
     */
    private double[][] gaussian(int width, int height, int seed){
        double[][] matrix = new double[width][height];
        Random rand = new Random();
        //rand.setSeed();
        for(int x=0;x<width;x++){
            matrix[x][0] = rand.nextGaussian();
            if(matrix[x][0]>1)matrix[x][0]=1;
            if(matrix[x][0]<-1)matrix[x][0]=-1;
        }

        double r;
        for(int y=1;y<height;y++){
            r = rand.nextGaussian();
            for(int x=0;x<width;x++){
                matrix[x][y] = matrix[x][0]*r;
                if(matrix[x][y]>1)matrix[x][y]=1;
                if(matrix[x][y]<-1)matrix[x][y]=-1;
            }
        }
        return matrix;
    }

    /**
     * Fills the board with tiles placed in a gaussian pattern
     * @param defTile
     */
    public void clear(Tile[] defTile){

        double[][] matrix = gaussian(width, height, 123);
        for(int y=0;y<height;y++) {
            for (int x = 0; x < width; x++) {
                this.set(defTile[(int)(((Math.abs(matrix[x][y]))*(defTile.length-1)))], x, y);
            }
        }
        /*
        for(int y=0;y<height;y++)
            for(int x=0;x<width;x++){
                Random rand = new Random();
                int randValue = rand.nextInt(defTile.length);
                this.set(defTile[randValue], x,y);
            }
         */
    }

    /**
     * @return int
     */
    public int width(){
        return width;
    }

    /**
     * @return int
     */
    public int height(){
        return height;
    }

    /**
     * @param x
     * @param y
     * @return Tile
     */
    public Tile get(int x,int y){
        if (OutOfBounds(x,y)) return null;
        return tiles[x][y];
    }

    /**
     * Checks if coordinate is outside of the board area
     * @param x
     * @param y
     * @return boolean
     */
    public boolean OutOfBounds(int x, int y){
        if (x<0 || x>=(width) || y<0 || y>=(height)) return true;
        return false;
    }

    /**
     * @param t
     * @param x
     * @param y
     * @return boolean
     */
    public boolean set(Tile t, int x,int y){
        if (OutOfBounds(x,y)) return false;
        tiles[x][y] = t;
        return true;
    }

    /**
     * Removes a tile from the specified location on the board
     * @param x
     * @param y
     * @return Tile
     */
    public Tile pickup(int x,int y){
        Tile t = get(x,y);
        if(t instanceof Entity){
            entities.remove((Entity)t);
        }
        set(null, x,y);
        return t;
    }

    /**
     * Removes a specified tile from the board
     * @param e
     */
    public Tile pickup(Entity e){
        Tile t = pickup(e.getX(), e.getY());
        e.setPosition(0,0);
        return t;
    }

    /**
     * Place a specified tile on the board.
     * @param tile
     * @param x
     * @param y
     * @return
     */
    public boolean place(Tile tile, int x, int y){
        if(get(x,y)!=null) return false; // tile is occupied
        if(!set(tile, x, y)) return false; // tile is out of bounds
        if(tile instanceof Entity){
            ((Entity)tile).setPosition(x,y);
            entities.add((Entity)tile);
        }
        return true;
    }

    /**
     * return a board iterator
     * @return boardIterator
     */
    @Override
    public boardIterator iterator() {
        return new boardIterator(this);
    }
}

/**
 * Iterator for the board
 */
class boardIterator implements Iterator<Tile>{
    int current;
    Board b;

    /**
     * Each iterator needs a board to iterate
     * @param b
     */
    public boardIterator(Board b) {
        this.current = 0;
        this.b = b;
    }

    /**
     * Checks if we're at the end of the board
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        if (current<b.width()*b.height())return true;
        return false;
    }

    /**
     *Increments to next Tile on the board
     * @return Tile
     */
    @Override
    public Tile next() {
        if(!hasNext()) throw new IndexOutOfBoundsException();
        int x =current%b.width();
        int y =current/b.width();
        current++;
        return b.get(x,y);
    }
}
