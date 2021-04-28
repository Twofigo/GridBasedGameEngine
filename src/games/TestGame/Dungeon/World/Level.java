package games.TestGame.Dungeon.World;

import engine.Board;
import engine.TableTop;

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
}
