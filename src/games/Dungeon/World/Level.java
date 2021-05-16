package games.Dungeon.World;

import engine.Board;
import engine.Entity;
import engine.TableTop;
import engine.Tile;
import games.Dungeon.Inventory.Item;

import java.util.Random;

public class Level extends TableTop {

    public Level(int width, int height){
        super();
        Board[] boards = new Board[4];
        boards[0] = new Board(width, height);
        boards[1] = new Board(width, height);
        boards[2] = new Board(width, height);
        boards[3] = new Board(width, height);
        try{
            setBoards(boards);
        }catch (Exception e){
            // this will never happen;
        }
    }
    public Board getBackground(){
        return getBoard(0);
    }
    public Board getFloor(){
        return getBoard(1);
    }
    public Board getForeground(){
        return getBoard(2);
    }
    public Board getOverlay(){
        return getBoard(3);
    }

    public int[][] getMazeMatrix(){
        int[][] mazeMatrix = new int[width()][height()];
        Board bg = getBackground();
        int v = 0;
        for (int y=0;y<width();y++){
            for (int x=0;x<height();x++){
                v=1;
                if (bg.get(x,y) instanceof Wall){
                   v=-1;
                }
                mazeMatrix[y][x] = v;
            }
        }
        return mazeMatrix;
    }
    public boolean spawn(Tile t){
        Random rand = new Random();
        Board back = getBackground();
        Board board = getForeground();
        Board floor = getFloor();
        int x;
        int y;
        while(true){
            x = rand.nextInt(floor.width());
            y = rand.nextInt(floor.height());
            if(!(back.get(x,y) instanceof Wall)) {
                if (floor.get(x, y) == null) break;
            }
        }
        if(t instanceof Item){
            floor.place(t,x,y);
        }
        else if(t instanceof Entity){
            board.place(t,x,y);
        }
        else {
            back.place(t,x,y);
        }
        return true;
    }
}
